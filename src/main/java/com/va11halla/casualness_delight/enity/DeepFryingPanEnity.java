package com.va11halla.casualness_delight.enity;

import com.va11halla.casualness_delight.block.DeepFryingPan;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import com.va11halla.casualness_delight.registry.BlockEntityTypesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.Optional;

public class DeepFryingPanEnity extends SyncedBlockEntity implements HeatableBlockEntity {

    private final ItemStackHandler inventory = createHandler();
    private int cookingTime;
    private int cookingTimeTotal;
    private ResourceLocation lastRecipeID;

    private ItemStack deepFryingPanStack;
    private int fireAspectLevel;

    public DeepFryingPanEnity(BlockPos pos, BlockState state) {
        super(BlockEntityTypesRegistry.DeepFryingPan.get(), pos, state);
        deepFryingPanStack = ItemStack.EMPTY;
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, DeepFryingPanEnity deepFryingPan) {
        boolean isHeated = deepFryingPan.isHeated(level, pos);
        if (isHeated) {
            ItemStack cookingStack = deepFryingPan.getStoredStack();
            if (cookingStack.isEmpty()) {
                deepFryingPan.cookingTime = 0;
            } else {
                deepFryingPan.cookAndOutputItems(cookingStack);
            }
        } else if (deepFryingPan.cookingTime > 0) {
            deepFryingPan.cookingTime = Mth.clamp(deepFryingPan.cookingTime - 2, 0, deepFryingPan.cookingTimeTotal);
        }
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, DeepFryingPanEnity deepFryingPan) {
        if (deepFryingPan.isHeated(level, pos) && deepFryingPan.hasStoredStack()) {
            RandomSource random = level.random;
            if (random.nextFloat() < 0.2F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double y = (double) pos.getY() + 0.1D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double motionY = random.nextBoolean() ? 0.015D : 0.005D;
                level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0D, motionY, 0.0D);
            }
            if (deepFryingPan.fireAspectLevel > 0 && random.nextFloat() < deepFryingPan.fireAspectLevel * 0.05F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double y = (double) pos.getY() + 0.1D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() * 0.4D - 0.2D);
                double motionX = level.random.nextFloat() - 0.5F;
                double motionY = level.random.nextFloat() * 0.5F + 0.2f;
                double motionZ = level.random.nextFloat() - 0.5F;
                level.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, motionX, motionY, motionZ);
            }
        }

    }

    private void cookAndOutputItems(ItemStack cookingStack) {
        if (level == null) return;

        ++cookingTime;
        if (cookingTime >= cookingTimeTotal) {
            SimpleContainer wrapper = new SimpleContainer(cookingStack);
            Optional<DeepFryingRecipe> recipe = this.getMatchingRecipe(wrapper);
            if (recipe.isPresent()) {
                ItemStack resultStack = recipe.get().assemble(wrapper,this.level.registryAccess());
                Direction direction = getBlockState().getValue(DeepFryingPan.FACING).getClockWise();
                ItemUtils.spawnItemEntity(level, resultStack.copy(),
                        worldPosition.getX() + 0.5, worldPosition.getY() + 0.3, worldPosition.getZ() + 0.5,
                        direction.getStepX() * 0.08F, 0.25F, direction.getStepZ() * 0.08F);

                cookingTime = 0;
                inventory.extractItem(0, 1, false);
            }
        }
    }

    public boolean isCooking() {
        return isHeated() && hasStoredStack();
    }

    public boolean isHeated() {
        if (level != null) {
            return isHeated(level, worldPosition);
        }
        return false;
    }
    RecipeType<DeepFryingRecipe> Deep_Frying = DeepFryingRecipe.Type.DeepFrying;
    private Optional<DeepFryingRecipe> getMatchingRecipe(Container recipeWrapper) {
        if (level == null) return Optional.empty();

        if (lastRecipeID != null) {
            Recipe<Container> recipe = ((RecipeManagerAccessor) level.getRecipeManager())
                    .getRecipeMap(Deep_Frying)
                    .get(lastRecipeID);
            if (recipe instanceof DeepFryingRecipe && recipe.matches(recipeWrapper, level)) {
                return Optional.of((DeepFryingRecipe) recipe);
            }
        }

        Optional<DeepFryingRecipe> recipe = level.getRecipeManager().getRecipeFor(Deep_Frying, recipeWrapper, level);
        if (recipe.isPresent()) {
            lastRecipeID = recipe.get().getId();
            return recipe;
        }

        return Optional.empty();
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        cookingTime = compound.getInt("CookTime");
        cookingTimeTotal = compound.getInt("CookTimeTotal");
        deepFryingPanStack = ItemStack.of(compound.getCompound("DeepFryingPan"));
        fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, deepFryingPanStack);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", inventory.serializeNBT());
        compound.putInt("CookTime", cookingTime);
        compound.putInt("CookTimeTotal", cookingTimeTotal);
        compound.put("DeepFryingPan", deepFryingPanStack.save(new CompoundTag()));
    }

    public CompoundTag writeSkilletItem(CompoundTag compound) {
        compound.put("DeepFryingPan", deepFryingPanStack.save(new CompoundTag()));
        return compound;
    }

    public void setSkilletItem(ItemStack stack) {
        deepFryingPanStack = stack.copy();
        fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
        inventoryChanged();
    }

    public ItemStack addItemToCook(ItemStack addedStack, @Nullable Player player) {
        Optional<DeepFryingRecipe> recipe = getMatchingRecipe(new SimpleContainer(addedStack));
        if (recipe.isPresent()) {
            cookingTimeTotal = DeepFryingPan.getDeepFryingCookingTime(recipe.get().getCookingTime(), fireAspectLevel);
            boolean wasEmpty = getStoredStack().isEmpty();
            ItemStack remainderStack = inventory.insertItem(0, addedStack.copy(), false);
            if (!ItemStack.matches(remainderStack, addedStack)) {
                lastRecipeID = recipe.get().getId();
                cookingTime = 0;
                if (wasEmpty && level != null && isHeated(level, worldPosition)) {
                    level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F, ModSounds.BLOCK_SKILLET_ADD_FOOD.get(), SoundSource.BLOCKS, 0.8F, 1.0F);
                }
                return remainderStack;
            }
        } else if (player != null) {
            player.displayClientMessage(TextUtils.getTranslation("block.skillet.invalid_item"), true);
        }
        return addedStack;
    }

    public ItemStack removeItem() {
        return inventory.extractItem(0, getStoredStack().getMaxStackSize(), false);
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public ItemStack getStoredStack() {
        return inventory.getStackInSlot(0);
    }

    public boolean hasStoredStack() {
        return !getStoredStack().isEmpty();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler()
        {
            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }
}
