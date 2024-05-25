package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class PlateOfFriedDumpling extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
    protected static final VoxelShape PIE_SHAPE;

    public PlateOfFriedDumpling() {
        super(FabricBlockSettings.copyOf(Blocks.CAKE), ()->ItemRegistry.BowlOfFriedDumpling.get(), true);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Integer)state.get(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }

    static {
        PIE_SHAPE = PLATE_SHAPE;
    }
}
