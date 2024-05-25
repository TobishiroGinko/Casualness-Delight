package com.va11halla.casualness_delight.enity;

import com.va11halla.casualness_delight.block.DeepFryingPan;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import com.va11halla.casualness_delight.registry.BlockEntityTypesRegistry;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import vectorwing.farmersdelight.common.block.entity.HeatableBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import org.jetbrains.annotations.Nullable;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static vectorwing.farmersdelight.common.item.SkilletItem.FLIP_TIME;

public class DeepFryingPanEntity extends SyncedBlockEntity implements HeatableBlockEntity
{
    private final ItemStackHandlerContainer inventory = createHandler();
    private int cookingTime;
    private int cookingTimeTotal;
    private Identifier lastRecipeID;

    private ItemStack DeepFryingPanStack;
    private int fireAspectLevel;

    // client only. can stay public
    public long lastFlippedTime = 0;

    public DeepFryingPanEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypesRegistry.DEEPFRYINGPAN.get(), pos, state);
        DeepFryingPanStack = ItemStack.EMPTY;
    }

    public static void cookingTick(World level, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
        boolean isHeated = deepFryingPan.isHeated(level, pos);
        if (isHeated) {
            ItemStack cookingStack = deepFryingPan.getStoredStack();
            if (cookingStack.isEmpty()) {
                deepFryingPan.cookingTime = 0;
            } else {
                deepFryingPan.cookAndOutputItems(cookingStack, level);
            }
        } else if (deepFryingPan.cookingTime > 0) {
            deepFryingPan.cookingTime = MathHelper.clamp(deepFryingPan.cookingTime - 2, 0, deepFryingPan.cookingTimeTotal);
        }

    }

    public static void animationTick(World level, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
        if (deepFryingPan.isHeated(level, pos) && deepFryingPan.hasStoredStack()) {
            Random random = level.random;
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

            if (level.getTime() - deepFryingPan.lastFlippedTime == FLIP_TIME) {
                double x = (double) pos.getX() + 0.5D;
                double y = (double) pos.getY() + 0.1D;
                double z = (double) pos.getZ() + 0.5D;
                level.playSound(x, y, z, ModSounds.BLOCK_SKILLET_ADD_FOOD.get(), SoundCategory.BLOCKS, 0.4F, level.random.nextFloat() * 0.2F + 0.9F, false);
            }
        }

    }

    private void cookAndOutputItems(ItemStack cookingStack, World level) {
        if (level == null) return;

        ++cookingTime;
        if (cookingTime >= cookingTimeTotal) {
            SimpleInventory wrapper = new SimpleInventory(cookingStack);
            Optional<DeepFryingRecipe> recipe = getMatchingRecipe(wrapper);
            if (recipe.isPresent()) {
                ItemStack resultStack = recipe.get().craft(wrapper, level.getRegistryManager());
                Direction direction = getCachedState().get(DeepFryingPan.FACING).rotateYClockwise();
                ItemUtils.spawnItemEntity(level, resultStack.copy(),
                        pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                        direction.getOffsetX() * 0.08F, 0.25F, direction.getOffsetZ() * 0.08F);

                cookingTime = 0;
                inventory.removeStack(0, 1);
            }
        }
    }

    public boolean isCooking() {
        return isHeated() && hasStoredStack();
    }

    public boolean isHeated() {
        if (world != null) {
            return isHeated(world, pos);
        }
        return false;
    }

    private Optional<DeepFryingRecipe> getMatchingRecipe(Inventory recipeWrapper) {
        if (world == null) return Optional.empty();

        if (lastRecipeID != null) {
            Recipe<Inventory> recipe = ((RecipeManagerAccessor) world.getRecipeManager())
                    .getRecipeMap(DeepFryingRecipe.DEEP_FRYING_TYPE)
                    .get(lastRecipeID);
            if (recipe instanceof DeepFryingRecipe && recipe.matches(recipeWrapper, world)) {
                return Optional.of((DeepFryingRecipe) recipe);
            }
        }

        Optional<DeepFryingRecipe> recipe = world.getRecipeManager().getFirstMatch(DeepFryingRecipe.DEEP_FRYING_TYPE, recipeWrapper, world);
        if (recipe.isPresent()) {
            lastRecipeID = recipe.get().getId();
            return recipe;
        }

        return Optional.empty();
    }

    @Override
    public void readNbt(NbtCompound compound) {
        super.readNbt(compound);
        inventory.deserializeNBT(compound.getCompound("Inventory"));
        cookingTime = compound.getInt("CookTime");
        cookingTimeTotal = compound.getInt("CookTimeTotal");
        DeepFryingPanStack = ItemStack.fromNbt(compound.getCompound("Skillet"));
        fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, DeepFryingPanStack);
    }

    @Override
    public void writeNbt(NbtCompound compound) {
        super.writeNbt(compound);
        compound.put("Inventory", inventory.serializeNBT());
        compound.putInt("CookTime", cookingTime);
        compound.putInt("CookTimeTotal", cookingTimeTotal);
        compound.put("Skillet", DeepFryingPanStack.writeNbt(new NbtCompound()));
    }

    public NbtCompound writeDeepFryingPanItem(NbtCompound compound) {
        compound.put("DeepFryingPan", DeepFryingPanStack.writeNbt(new NbtCompound()));
        return compound;
    }

    public void setSkilletItem(ItemStack stack) {
        DeepFryingPanStack = stack.copy();
        fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, stack);
        inventoryChanged();
    }

    public ItemStack addItemToCook(ItemStack addedStack, @Nullable PlayerEntity player) {
        Optional<DeepFryingRecipe> recipe = getMatchingRecipe(new SimpleInventory(addedStack));
        if (recipe.isPresent()) {
            cookingTimeTotal = DeepFryingPan.getDeepFryingPanCookingTime(recipe.get().getCookTime(), fireAspectLevel);
            boolean wasEmpty = getStoredStack().isEmpty();
            ItemStack remainderStack =  ItemUtils.insertItem(inventory, 0, addedStack.copy(), false);
            if (!ItemStack.areEqual(remainderStack, addedStack)) {
                lastRecipeID = recipe.get().getId();
                cookingTime = 0;
                if (wasEmpty && world != null && isHeated(world, pos)) {
                    world.playSound(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, ModSounds.BLOCK_SKILLET_ADD_FOOD.get(), SoundCategory.BLOCKS, 0.8F, 1.0F);
                }
                return remainderStack;
            }
        } else if (player != null) {
            player.sendMessage(TextUtils.getTranslation("block.skillet.invalid_item"), true);
        }
        return addedStack;
    }

    public ItemStack removeItem() {
        return inventory.removeStack(0, getStoredStack().getMaxCount());
    }

    public ItemStackHandlerContainer getInventory() {
        return inventory;
    }

    public ItemStack getStoredStack() {
        return inventory.getStackInSlot(0);
    }

    public boolean hasStoredStack() {
        return !getStoredStack().isEmpty();
    }

    private ItemStackHandlerContainer createHandler() {
        return new ItemStackHandlerContainer()
        {
            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
    }
}
