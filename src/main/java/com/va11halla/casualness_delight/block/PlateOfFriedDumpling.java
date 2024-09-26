package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class PlateOfFriedDumpling extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
    protected static final VoxelShape PIE_SHAPE;
    public PlateOfFriedDumpling(Properties properties) {
        super(Block.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.BOWL_OF_FRIED_DUMPLING.getSupplier(), true);
    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }

    static {
        PIE_SHAPE = PLATE_SHAPE;
    }
}