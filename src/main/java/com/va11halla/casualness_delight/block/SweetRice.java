package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import javax.swing.text.html.BlockView;


public class SweetRice extends FeastBlock {
    protected static final VoxelShape PLATE_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
    protected static final VoxelShape PIE_SHAPE;
    public SweetRice(Properties properties) {
        super(properties, ItemRegistry.BowlOfSweetRice, true);
    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
    }

    static {
        PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0, 1.0, 3.0, 13.0, 5.0, 13.0), BooleanOp.OR);
    }
}
