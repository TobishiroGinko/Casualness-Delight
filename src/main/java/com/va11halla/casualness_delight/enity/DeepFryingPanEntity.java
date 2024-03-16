package com.va11halla.casualness_delight.enity;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.entity.block.HeatableBlockEntity;
import com.nhoryzon.mc.farmersdelight.entity.block.SyncedBlockEntity;
import com.nhoryzon.mc.farmersdelight.entity.block.inventory.ItemStackInventory;
import com.nhoryzon.mc.farmersdelight.mixin.accessors.RecipeManagerAccessorMixin;
import com.nhoryzon.mc.farmersdelight.registry.ParticleTypesRegistry;
import com.nhoryzon.mc.farmersdelight.registry.SoundsRegistry;
import java.util.Objects;
import java.util.Optional;

import com.va11halla.casualness_delight.block.DeepFryingPan;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import com.va11halla.casualness_delight.registry.BlockEntityTypesRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DeepFryingPanEntity extends SyncedBlockEntity implements ItemStackInventory, HeatableBlockEntity {
    public static final String TAG_KEY_SKILLET_STACK = "DeepFryingPan";
    private int cookTime;
    private int cookTimeTotal;
    private final DefaultedList<ItemStack> inventory;
    private Identifier lastRecipeID;
    private ItemStack deepFryingPanStack;
    private int fireAspectLevel;

    public DeepFryingPanEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypesRegistry.DEEPFRYINGPAN.get(), blockPos, blockState);
        this.deepFryingPanStack = ItemStack.EMPTY;
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    public static void cookingTick(World world, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
        boolean isHeated = deepFryingPan.isHeated(world, pos);
        if (isHeated) {
            ItemStack cookingStack = deepFryingPan.getStoredStack();
            if (cookingStack.isEmpty()) {
                deepFryingPan.cookTime = 0;
            } else {
                deepFryingPan.cookAndOutputItems(cookingStack);
            }
        } else if (deepFryingPan.cookTime > 0) {
            deepFryingPan.cookTime = MathHelper.clamp(deepFryingPan.cookTime - 2, 0, deepFryingPan.cookTimeTotal);
        }

    }

    public static void animationTick(World world, BlockPos pos, BlockState state, DeepFryingPanEntity deepFryingPan) {
        if (deepFryingPan.isHeated(world, pos) && deepFryingPan.hasStoredStack()) {
            Random random = world.getRandom();
            double x;
            double y;
            double z;
            double motionX;
            if (random.nextFloat() < 0.2F) {
                x = (double)pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                y = (double)pos.getY() + 0.1;
                z = (double)pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                motionX = random.nextBoolean() ? 0.015 : 0.005;
                world.addParticle(ParticleTypesRegistry.STEAM.get(), x, y, z, 0.0, motionX, 0.0);
            }

            if (deepFryingPan.fireAspectLevel > 0 && random.nextFloat() < (float)deepFryingPan.fireAspectLevel * 0.05F) {
                x = (double)pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                y = (double)pos.getY() + 0.1;
                z = (double)pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                motionX = (double)(world.random.nextFloat() - 0.5F);
                double motionY = (double)(world.random.nextFloat() * 0.5F + 0.2F);
                double motionZ = (double)(world.random.nextFloat() - 0.5F);
                world.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, motionX, motionY, motionZ);
            }
        }

    }

    private void cookAndOutputItems(ItemStack cookingStack) {
        if (this.world != null) {
            ++this.cookTime;
            if (this.cookTime >= this.cookTimeTotal) {
                SimpleInventory wrapper = new SimpleInventory(new ItemStack[]{cookingStack});
                Optional<DeepFryingRecipe> recipe = this.getMatchingRecipe(wrapper);
                if (recipe.isPresent()) {
                    ItemStack resultStack = ((DeepFryingRecipe)recipe.get()).craft(wrapper, this.world.getRegistryManager());
                    Direction direction = ((Direction)this.getCachedState().get(DeepFryingPan.FACING)).rotateYClockwise();
                    ItemEntity entity = new ItemEntity(this.world, (double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.3, (double)this.pos.getZ() + 0.5, resultStack.copy());
                    entity.setVelocity((double)((float)direction.getOffsetX() * 0.08F), 0.25, (double)((float)direction.getOffsetZ() * 0.08F));
                    this.world.spawnEntity(entity);
                    this.cookTime = 0;
                    this.removeStack(0, 1);
                }
            }

        }
    }

    public boolean isCooking() {
        return this.isHeated() && this.hasStoredStack();
    }

    public boolean isHeated() {
        return this.world != null ? this.isHeated(this.world, this.pos) : false;
    }

    private Optional<DeepFryingRecipe> getMatchingRecipe(Inventory inventory) {
        if (this.world == null) {
            return Optional.empty();
        } else {
            if (this.lastRecipeID != null) {
                Recipe<Inventory> recipe = (Recipe)((RecipeManagerAccessorMixin)this.world.getRecipeManager()).getAllForType(DeepFryingRecipe.DEEP_FRYING_TYPE).get(this.lastRecipeID);
                if (recipe instanceof DeepFryingRecipe) {
                    DeepFryingRecipe deepFryingRecipe = (DeepFryingRecipe)recipe;
                    if (recipe.matches(inventory, this.world)) {
                        return Optional.of(deepFryingRecipe);
                    }
                }
            }

            Optional<DeepFryingRecipe> recipe = this.world.getRecipeManager().getFirstMatch(DeepFryingRecipe.DEEP_FRYING_TYPE, inventory, this.world);
            if (recipe.isPresent()) {
                this.lastRecipeID = ((DeepFryingRecipe)recipe.get()).getId();
                return recipe;
            } else {
                return Optional.empty();
            }
        }
    }

    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.fromTag(nbt);
    }

    private void fromTag(NbtCompound nbt) {
        this.readInventoryNbt(nbt);
        this.cookTime = nbt.getInt("CookTime");
        this.cookTimeTotal = nbt.getInt("CookTimeTotal");
        this.deepFryingPanStack = ItemStack.fromNbt(nbt.getCompound("DeepFryingPan"));
        this.fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, this.deepFryingPanStack);
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.writeInventoryNbt(nbt);
        nbt.putInt("CookTime", this.cookTime);
        nbt.putInt("CookTimeTotal", this.cookTimeTotal);
        nbt.put("DeepFryingPan", this.deepFryingPanStack.writeNbt(new NbtCompound()));
    }

    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        this.writeNbt(nbtCompound);
        return nbtCompound;
    }

    public NbtCompound writeDeepFryingPanItem(NbtCompound nbt) {
        nbt.put("DeepFryingPan", this.deepFryingPanStack.writeNbt(new NbtCompound()));
        return nbt;
    }

    public ItemStack addItemToCook(ItemStack addedStack, PlayerEntity player) {
        Optional<DeepFryingRecipe> recipe = this.getMatchingRecipe(new SimpleInventory(new ItemStack[]{addedStack}));
        if (recipe.isPresent()) {
            this.cookTimeTotal = DeepFryingPan.getDeepFryingPanCookingTime(((DeepFryingRecipe)recipe.get()).getCookTime(), this.fireAspectLevel);
            boolean wasEmpty = this.getStoredStack().isEmpty();
            ItemStack remainderStack = this.insertStack(0, addedStack.copy(), false);
            if (!ItemStack.areEqual(remainderStack, addedStack)) {
                this.lastRecipeID = ((DeepFryingRecipe)recipe.get()).getId();
                this.cookTime = 0;
                if (wasEmpty && this.world != null && this.isHeated(this.world, this.pos)) {
                    this.world.playSound((PlayerEntity)null, (double)((float)this.pos.getX() + 0.5F), (double)((float)this.pos.getY() + 0.5F), (double)((float)this.pos.getZ() + 0.5F), SoundsRegistry.BLOCK_SKILLET_ADD_FOOD.get(), SoundCategory.BLOCKS, 0.8F, 1.0F);
                }
//煎锅音效
                return remainderStack;
            }
        } else if (player != null) {
            player.sendMessage(FarmersDelightMod.i18n("block.skillet.invalid_item", new Object[0]), true);
        }

        return addedStack;
    }

    public ItemStack removeItem() {
        return this.removeStack(0, this.getStoredStack().getMaxCount());
    }

    public ItemStack getStoredStack() {
        return this.getStack(0);
    }

    public boolean hasStoredStack() {
        return !this.getStoredStack().isEmpty();
    }

    public void inventoryChanged() {
        this.markDirty();
        ((World)Objects.requireNonNull(this.world)).updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
    }
}
