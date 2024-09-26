package com.va11halla.casualness_delight.block;


import javax.annotation.Nullable;

import com.va11halla.casualness_delight.registry.BlockRegistry;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

public class CapsicumsVineBlock extends CropBlock {
    public static final IntegerProperty VINE_AGE;
    public static final BooleanProperty ROPELOGGED;
    private static final VoxelShape SHAPE;

    public CapsicumsVineBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(this.getAgeProperty(), 0)).setValue(ROPELOGGED, false));
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int age = (Integer)state.getValue(this.getAgeProperty());
        boolean isMature = age == this.getMaxAge();
        return !isMature && stack.is(Items.BONE_MEAL) ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        int age = (Integer)state.getValue(this.getAgeProperty());
        boolean isMature = age == this.getMaxAge();
        if (isMature) {
            int quantity = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack((ItemLike) ItemRegistry.CAPSICUM.getItem(), quantity));

            level.playSound((Player)null, pos, (SoundEvent)ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get(), SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, (BlockState)state.setValue(this.getAgeProperty(), 0), 2);
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hit);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isAreaLoaded(pos, 1)) {
            if (level.getRawBrightness(pos, 0) >= 9) {
                int age = this.getAge(state);
                if (age < this.getMaxAge()) {
                    float speed = getGrowthSpeed(state, level, pos);
                    if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / speed) + 1) == 0)) {
                        level.setBlock(pos, (BlockState)state.setValue(this.getAgeProperty(), age + 1), 2);
                        CommonHooks.fireCropGrowPost(level, pos, state);
                    }
                }

                this.attemptRopeClimb(level, pos, random);
            }

        }
    }

    public void attemptRopeClimb(ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.3F) {
            BlockPos posAbove = pos.above();
            BlockState stateAbove = level.getBlockState(posAbove);
            boolean canClimb = (Boolean)Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get() ? stateAbove.is(ModTags.ROPES) : stateAbove.is((Block)ModBlocks.ROPE.get());
            if (canClimb) {
                int vineHeight;
                for(vineHeight = 1; level.getBlockState(pos.below(vineHeight)).is(this); ++vineHeight) {
                }

                if (vineHeight < 3) {
                    level.setBlockAndUpdate(posAbove, (BlockState)this.defaultBlockState().setValue(ROPELOGGED, true));
                }
            }
        }

    }

    public BlockState getStateForAge(int age) {
        return (BlockState)this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    public IntegerProperty getAgeProperty() {
        return VINE_AGE;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public int getMaxAge() {
        return 3;
    }

    protected ItemLike getBaseSeedId() {
        return (ItemLike)ItemRegistry.CAPSICUM_SEEDS.getItem();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{VINE_AGE, ROPELOGGED});
    }

    protected int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level) / 2;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (newAge > maxAge) {
            newAge = maxAge;
        }

        level.setBlockAndUpdate(pos, (BlockState)state.setValue(this.getAgeProperty(), newAge));
        this.attemptRopeClimb(level, pos, random);
    }

    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return (Boolean)state.getValue(ROPELOGGED) && state.is(BlockTags.CLIMBABLE);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (!(Boolean)state.getValue(ROPELOGGED)) {
            return super.canSurvive(state, level, pos);
        } else {
            return belowState.is((Block) BlockRegistry.CapsicumsCrop.get()) && this.hasGoodCropConditions(level, pos);
        }
    }

    public boolean hasGoodCropConditions(LevelReader level, BlockPos pos) {
        return level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos);
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        boolean isRopelogged = (Boolean)state.getValue(ROPELOGGED);
        super.playerDestroy(level, player, pos, state, blockEntity, stack);
        if (isRopelogged) {
            destroyAndPlaceRope(level, pos);
        }

    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }

        return state;
    }

    public static void destroyAndPlaceRope(Level level, BlockPos pos) {
        Block configuredRopeBlock = (Block)BuiltInRegistries.BLOCK.get(ResourceLocation.parse((String)Configuration.DEFAULT_TOMATO_VINE_ROPE.get()));
        Block finalRopeBlock = configuredRopeBlock != null ? configuredRopeBlock : (Block)ModBlocks.ROPE.get();
        level.setBlockAndUpdate(pos, finalRopeBlock.defaultBlockState());
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
            if ((Boolean)state.getValue(ROPELOGGED)) {
                destroyAndPlaceRope(level, pos);
            }
        }

    }

    static {
        VINE_AGE = BlockStateProperties.AGE_3;
        ROPELOGGED = BooleanProperty.create("ropelogged");
        SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    }
}
