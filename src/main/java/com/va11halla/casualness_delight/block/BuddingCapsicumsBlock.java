package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.BuddingBushBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class BuddingCapsicumsBlock extends BuddingBushBlock implements BonemealableBlock {
    public BuddingCapsicumsBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is((Block)ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.is(Blocks.FARMLAND);
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if ((Integer)state.getValue(BuddingBushBlock.AGE) == 4) {
            level.setBlock(currentPos, ((Block) BlockRegistry.CapsicumsCrop.get()).defaultBlockState(), 3);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public boolean canGrowPastMaxAge() {
        return true;
    }

    public void growPastMaxAge(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, ((Block)BlockRegistry.CapsicumsCrop.get()).defaultBlockState());
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 4);
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int maxAge = this.getMaxAge();
        int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(level), 7);
        if (ageGrowth <= maxAge) {
            level.setBlockAndUpdate(pos, (BlockState)state.setValue(AGE, ageGrowth));
        } else {
            int remainingGrowth = ageGrowth - maxAge - 1;
            level.setBlockAndUpdate(pos, (BlockState)((Block)BlockRegistry.CapsicumsCrop.get()).defaultBlockState().setValue(CapsicumsVineBlock.VINE_AGE, remainingGrowth));
        }

    }
}
