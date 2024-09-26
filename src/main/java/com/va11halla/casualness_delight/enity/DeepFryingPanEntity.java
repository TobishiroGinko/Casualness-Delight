package com.va11halla.casualness_delight.enity;

import com.va11halla.casualness_delight.block.DeepFryingPan;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import com.va11halla.casualness_delight.registry.BlockEntityTypesRegistry;
import com.va11halla.casualness_delight.registry.RecipeTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.Optional;

public class DeepFryingPanEntity extends SyncedBlockEntity implements HeatableBlockEntity {

    private final ItemStackHandler inventory = this.createHandler();
    private int cookingTime;
    private int cookingTimeTotal;
    private ItemStack deepFryingPanStack;
    private int fireAspectLevel;
    private final RecipeManager.CachedCheck<SingleRecipeInput, DeepFryingRecipe> quickCheck;

    public DeepFryingPanEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypesRegistry.DeepFryingPan.get(), pos, state);
        this.deepFryingPanStack = ItemStack.EMPTY;
        this.quickCheck = RecipeManager.createCheck(RecipeTypeRegistry.DEEPFRYING.get());
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
        boolean isHeated = deepFryingPan.isHeated(level, pos);
        if (isHeated) {
            ItemStack cookingStack = deepFryingPan.getStoredStack();
            if (cookingStack.isEmpty()) {
                deepFryingPan.cookingTime = 0;
            } else {
                deepFryingPan.cookAndOutputItems(cookingStack,level);
            }
        } else if (deepFryingPan.cookingTime > 0) {
            deepFryingPan.cookingTime = Mth.clamp(deepFryingPan.cookingTime - 2, 0, deepFryingPan.cookingTimeTotal);
        }
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
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

    private void cookAndOutputItems(ItemStack cookingStack,Level level) {
        ++cookingTime;
        if (cookingTime >= cookingTimeTotal) {
            Optional<RecipeHolder<DeepFryingRecipe>> recipe = this.getMatchingRecipe(cookingStack);
            if (recipe.isPresent()) {
                ItemStack resultStack = ((DeepFryingRecipe)((RecipeHolder)recipe.get()).value()).assemble(new SingleRecipeInput(cookingStack), level.registryAccess());
                Direction direction = ((Direction)getBlockState().getValue(DeepFryingPan.FACING)).getClockWise();
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
    private Optional<RecipeHolder<DeepFryingRecipe>> getMatchingRecipe(ItemStack stack) {
        return this.level == null ? Optional.empty() : this.quickCheck.getRecipeFor(new SingleRecipeInput(stack), this.level);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound,registries);
        inventory.deserializeNBT(registries,compound.getCompound("Inventory"));
        cookingTime = compound.getInt("CookTime");
        cookingTimeTotal = compound.getInt("CookTimeTotal");
        deepFryingPanStack = ItemStack.parseOptional(registries,compound.getCompound("DeepFryingPan"));
        fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel((Holder)registries.holder(Enchantments.FIRE_ASPECT).get(), this.deepFryingPanStack);
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound,registries);
        compound.put("Inventory", inventory.serializeNBT(registries));
        compound.putInt("CookTime", cookingTime);
        compound.putInt("CookTimeTotal", cookingTimeTotal);
        compound.put("DeepFryingPan", this.deepFryingPanStack.saveOptional(registries));
    }

    public ItemStack getDeepFryingPanStack() {
        return this.deepFryingPanStack;
    }

    public void setDeepFryingPanStack(ItemStack stack) {
        deepFryingPanStack = stack.copy();
        fireAspectLevel = EnchantmentHelper.getItemEnchantmentLevel(this.level.registryAccess().holderOrThrow(Enchantments.FIRE_ASPECT), stack);
        inventoryChanged();
    }

    public ItemStack addItemToCook(ItemStack addedStack, @Nullable Player player) {
        Optional<RecipeHolder<DeepFryingRecipe>> recipe = this.getMatchingRecipe(addedStack);
        if (recipe.isPresent()&& this.getStoredStack().isEmpty()) {
            boolean wasEmpty = getStoredStack().isEmpty();
            ItemStack remainderStack = inventory.insertItem(0, addedStack.copy(), false);
            if (!ItemStack.matches(remainderStack, addedStack)) {
                cookingTimeTotal = DeepFryingPan.getDeepFryingCookingTime(((DeepFryingRecipe)((RecipeHolder)recipe.get()).value()).getCookingTime(), this.fireAspectLevel);
                cookingTime = 0;
                if (wasEmpty && level != null && isHeated(level, worldPosition)) {
                    level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F, ModSounds.BLOCK_SKILLET_ADD_FOOD.get(), SoundSource.BLOCKS, 0.8F, 1.0F);
                }
                return remainderStack;
            }
        } else if (player != null) {
            player.displayClientMessage(TextUtils.getTranslation("block.skillet.invalid_item", new Object[0]), true);
        }
        return addedStack;
    }

    public ItemStack removeItem() {
        return this.inventory.extractItem(0, getStoredStack().getMaxStackSize(), false);
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStoredStack() {
        return this.inventory.getStackInSlot(0);
    }

    public boolean hasStoredStack() {
        return !getStoredStack().isEmpty();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler() {
            protected void onContentsChanged(int slot) {
                DeepFryingPanEntity.this.inventoryChanged();
            }
        };
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }
}