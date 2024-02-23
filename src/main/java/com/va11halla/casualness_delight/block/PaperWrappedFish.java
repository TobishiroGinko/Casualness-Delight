package com.va11halla.casualness_delight.block;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class PaperWrappedFish extends HorizontalFacingBlock {

    public static final EnumProperty<BedPart> PART = Properties.BED_PART;

    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, 4);

    protected static final VoxelShape[] SHAPES_NORTH_HEAD = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_NORTH_FOOT = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_SOUTH_HEAD = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_SOUTH_FOOT = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_WEST_HEAD = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_WEST_FOOT = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_EAST_HEAD = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    protected static final VoxelShape[] SHAPES_EAST_FOOT = new VoxelShape[]{
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D)
    };

    public PaperWrappedFish(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(SERVINGS, 4).with(PART, BedPart.HEAD))));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(PART) == BedPart.HEAD) {
            switch ((Direction) state.get(FACING)) {
                case NORTH:
                    return SHAPES_NORTH_HEAD[state.get(SERVINGS)];
                case SOUTH:
                    return SHAPES_SOUTH_HEAD[state.get(SERVINGS)];
                case WEST:
                    return SHAPES_WEST_HEAD[state.get(SERVINGS)];
                case EAST:
                    return SHAPES_EAST_HEAD[state.get(SERVINGS)];
            }
        }
        if (state.get(PART) == BedPart.FOOT) {
            switch ((Direction) state.get(FACING)) {
                case NORTH:
                    return SHAPES_NORTH_FOOT[state.get(SERVINGS)];
                case SOUTH:
                    return SHAPES_SOUTH_FOOT[state.get(SERVINGS)];
                case WEST:
                    return SHAPES_WEST_FOOT[state.get(SERVINGS)];
                case EAST:
                    return SHAPES_EAST_FOOT[state.get(SERVINGS)];
            }
        }
        return SHAPES_NORTH_HEAD[state.get(SERVINGS)];
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, SERVINGS);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private static Direction getDirectionTowardsOtherPart(BedPart part, Direction direction) {
        return part == BedPart.HEAD ? direction : direction.getOpposite();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
            return state.canPlaceAt(world, pos) && neighborState.isOf(this) && neighborState.get(PART) != state.get(PART) ? state : Blocks.AIR.getDefaultState();
        } else {
            return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            BedPart bedpart = state.get(PART);
            if (bedpart == BedPart.FOOT) {
                BlockPos blockpos = pos.offset(getDirectionTowardsOtherPart(bedpart, state.get(FACING)));
                BlockState blockstate = world.getBlockState(blockpos);
                if (blockstate.isOf(this) && blockstate.get(PART) == BedPart.HEAD) {
                    world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, blockpos, Block.getRawIdFromState(blockstate));
                }
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getHorizontalPlayerFacing();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(direction);
        World world = ctx.getWorld();
        return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? (BlockState)this.getDefaultState().with(FACING, direction) : null;
    }

    public PistonBehavior method_9527(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos blockPos = pos.offset((Direction)state.get(FACING));
            world.setBlockState(blockPos, (BlockState)state.with(PART, BedPart.FOOT), 3);
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, 3);
        }

    }

    public static DoubleBlockProperties.Type getBedPart(BlockState state) {
        BedPart bedPart = (BedPart)state.get(PART);
        return bedPart == BedPart.FOOT ? DoubleBlockProperties.Type.FIRST : DoubleBlockProperties.Type.SECOND;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int servings = state.get(SERVINGS);
        ItemStack heldStack = player.getStackInHand(hand);

        if (!(servings == 0)) {
            if (heldStack.isOf(Items.BOWL)) {
                return takeServing(world, pos, state, player, hand, ItemRegistry.BowlOfPaperWrappedFish.get());
            } else {
                player.sendMessage(FarmersDelightMod.i18n("block.feast.use_container", (new ItemStack(Items.BOWL)).getName()), true);
            }
        }
        if (servings == 0) {
            world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.PLAYERS, 0.8F, 0.8F);
            world.breakBlock(pos, true);
        }
        return ActionResult.SUCCESS;
    }

    protected ActionResult takeServing(World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, Item serving) {
        int servings = state.get(SERVINGS);
        BedPart part = state.get(PART);
        BlockPos pairPos = pos.offset(getDirectionTowardsOtherPart(part, state.get(FACING)));
        BlockState pairState = world.getBlockState(pairPos);
        ItemStack heldItem = player.getStackInHand(hand);

        world.setBlockState(pairPos, pairState.with(SERVINGS, servings - 1), 3);
        world.setBlockState(pos, state.with(SERVINGS, servings - 1), 3);

        if (!player.isCreative()) {
            heldItem.decrement(1);
        }
        if (!player.getInventory().insertStack(new ItemStack(serving))) {
            player.dropItem(new ItemStack(serving), false);
        }
        world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }

}