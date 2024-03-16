package com.va11halla.casualness_delight.block;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.va11halla.casualness_delight.enity.DeepFryingPanEntity;
import com.va11halla.casualness_delight.registry.BlockEntityTypesRegistry;
import com.nhoryzon.mc.farmersdelight.registry.SoundsRegistry;
import com.nhoryzon.mc.farmersdelight.registry.TagsRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.MapColor;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class DeepFryingPan extends BlockWithEntity {
    public static final int MINIMUM_COOKING_TIME = 60;
    public static final DirectionProperty FACING;
    public static final BooleanProperty SUPPORT;
    protected static final VoxelShape SHAPE;
    protected static final VoxelShape SHAPE_WITH_TRAY;

    public DeepFryingPan() {
        super(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F, 6.0F).sounds(BlockSoundGroup.LANTERN));
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(FACING, Direction.NORTH)).with(SUPPORT, false));
    }
//看到这
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypesRegistry.DEEPFRYINGPAN.get().instantiate(pos, state);
    }

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient() ? checkType(type, BlockEntityTypesRegistry.DEEPFRYINGPAN.get(), DeepFryingPanEntity::animationTick) : checkType(type, BlockEntityTypesRegistry.DEEPFRYINGPAN.get(), DeepFryingPanEntity::cookingTick);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DeepFryingPanEntity deepFryingPanEntity) {
            if (!world.isClient()) {
                ItemStack heldStack = player.getStackInHand(hand);
                EquipmentSlot heldSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                ItemStack remainderStack;
                if (heldStack.isEmpty()) {
                    remainderStack = deepFryingPanEntity.removeItem();
                    if (!player.isCreative()) {
                        player.equipStack(heldSlot, remainderStack);
                    }

                    return ActionResult.SUCCESS;
                }

                remainderStack = deepFryingPanEntity.addItemToCook(heldStack, player);
                if (remainderStack.getCount() != heldStack.getCount()) {
                    if (!player.isCreative()) {
                        player.equipStack(heldSlot, remainderStack);
                    }

                    world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_LANTERN_PLACE, SoundCategory.BLOCKS, 0.7F, 1.0F);
                    return ActionResult.SUCCESS;
                }
            }

            return ActionResult.CONSUME;
        } else {
            return ActionResult.PASS;
        }
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity var7 = world.getBlockEntity(pos);
            if (var7 instanceof DeepFryingPanEntity) {
                DeepFryingPanEntity deepFryingPanEntity = (DeepFryingPanEntity)var7;
                ItemScatterer.spawn(world, pos, deepFryingPanEntity);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }

    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(new Property[]{FACING, SUPPORT});
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext context) {
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing())).with(SUPPORT, this.getTrayState(context.getWorld(), context.getBlockPos()));
    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = super.getPickStack(world, pos, state);
        DeepFryingPanEntity deepFryingPanEntity = (DeepFryingPanEntity)world.getBlockEntity(pos);
        NbtCompound nbt = new NbtCompound();
        if (deepFryingPanEntity != null) {
            deepFryingPanEntity.writeDeepFryingPanItem(nbt);
        }

        if (!nbt.isEmpty()) {
            stack = ItemStack.fromNbt(nbt.getCompound("DeepFryingPan"));
        }

        return stack;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DeepFryingPanEntity deepFryingPanEntity) {
            if (deepFryingPanEntity.isCooking()) {
                double x = (double)pos.getX() + 0.5;
                double y = (double)pos.getY();
                double z = (double)pos.getZ() + 0.5;
                if (random.nextInt(10) == 0) {
                    world.playSound(x, y, z, SoundsRegistry.BLOCK_SKILLET_SIZZLE.get(), SoundCategory.BLOCKS, 0.4F, random.nextFloat() * 0.2F + 0.9F, false);
                }
            }
        }

    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Boolean.TRUE.equals(state.get(SUPPORT)) ? SHAPE_WITH_TRAY : SHAPE;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    private boolean getTrayState(WorldAccess worldAccess, BlockPos pos) {
        return worldAccess.getBlockState(pos.down()).isIn(TagsRegistry.TRAY_HEAT_SOURCES);
    }

    public static int getDeepFryingPanCookingTime(int originalCookingTime, int fireAspectLevel) {
        int cookingTime = originalCookingTime > 0 ? originalCookingTime : 600;
        int cookingSeconds = cookingTime / 20;
        float cookingTimeReduction = 1.0F;
        int result = (int)((float)cookingSeconds * cookingTimeReduction) * 20;
        return MathHelper.clamp(result, 60, originalCookingTime);
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        SUPPORT = BooleanProperty.of("support");
        SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
        SHAPE_WITH_TRAY = VoxelShapes.union(SHAPE, Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));
    }
}
//*/