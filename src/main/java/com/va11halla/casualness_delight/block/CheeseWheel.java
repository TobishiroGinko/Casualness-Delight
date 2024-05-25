package com.va11halla.casualness_delight.block;

import com.mojang.datafixers.util.Pair;
import vectorwing.farmersdelight.common.tag.ModTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class CheeseWheel extends Block {
    public static final DirectionProperty FACING;
    public static final IntProperty BITES;
    public static final int MAX_BITES = 7;
    protected static final VoxelShape[] SHAPE;
    public final Item pieSlice;

    public CheeseWheel(Item pieSlice) {
        super(FabricBlockSettings.copyOf(Blocks.CAKE));
        this.pieSlice = pieSlice;
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(FACING, Direction.NORTH)).with(BITES, 0));
    }
    public @Nullable BlockState getPlacementState(ItemPlacementContext context) {
        return (BlockState)this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(new Property[]{FACING, BITES});
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (player.getMainHandStack().isIn(ModTags.KNIVES)) {
            vectorwing.farmersdelight.common.block.PieBlock pieBlock = (vectorwing.farmersdelight.common.block.PieBlock)state.getBlock();
            ItemStack pieSlices = pieBlock.getPieSliceItem();
            pieSlices.setCount(6 - (Integer)state.get(BITES));
            ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, pieSlices));
        }

    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (world.isClient()) {
            if (itemstack.isIn(ModTags.KNIVES)) {
                return this.cutSlice(world, pos, state);
            }

            if (this.consumeBite(world, pos, state, player) == ActionResult.SUCCESS) {
                return ActionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return itemstack.isIn(ModTags.KNIVES) ? this.cutSlice(world, pos, state) : this.consumeBite(world, pos, state, player);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE[state.get(BITES)];
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 6 - (Integer)state.get(BITES);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public ItemStack getPieSliceStack() {
        return new ItemStack(this.pieSlice);
    }

    protected ActionResult consumeBite(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            ItemStack slice = this.getPieSliceStack();
            FoodComponent sliceFood = slice.getItem().getFoodComponent();
            player.getHungerManager().eat(slice.getItem(), slice);
            if (this.getPieSliceStack().getItem().isFood() && sliceFood != null) {
                Iterator var7 = sliceFood.getStatusEffects().iterator();

                while(var7.hasNext()) {
                    Pair<StatusEffectInstance, Float> pair = (Pair)var7.next();
                    if (!world.isClient() && pair.getFirst() != null && world.getRandom().nextFloat() < (Float)pair.getSecond()) {
                        player.addStatusEffect(new StatusEffectInstance((StatusEffectInstance)pair.getFirst()));
                    }
                }
            }

            int bites = (Integer)state.get(BITES);
            if (bites < 6) {
                world.setBlockState(pos, (BlockState)state.with(BITES, bites + 1), 6);
            } else {
                world.removeBlock(pos, false);
            }

            world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 0.8F, 0.8F);
            return ActionResult.SUCCESS;
        }
    }

    protected ActionResult cutSlice(World world, BlockPos pos, BlockState state) {
        int bites = (Integer)state.get(BITES);
        if (bites < 6) {
            world.setBlockState(pos, (BlockState)state.with(BITES, bites + 1), 6);
        } else {
            world.removeBlock(pos, false);
        }

        ItemScatterer.spawn(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), this.getPieSliceStack());
        world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);
        return ActionResult.SUCCESS;
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        BITES = IntProperty.of("bites", 0, 6);
        SHAPE = new VoxelShape[]{Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0), Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)};
    }
}
