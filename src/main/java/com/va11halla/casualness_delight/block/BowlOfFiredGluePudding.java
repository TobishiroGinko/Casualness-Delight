package com.va11halla.casualness_delight.block;

import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BowlOfFiredGluePudding extends FeastBlock {
    public static final IntegerProperty GLUEPUDDING_SERVINGS = IntegerProperty.create("servings", 0, 6);
    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<Supplier<Item>> GluePuddingServings;

    public BowlOfFiredGluePudding(Properties properties) {
        super(properties, ItemRegistry.FRIED_GLUE_PUDDING.getSupplier(), true);
        this.GluePuddingServings = Arrays.asList(ItemRegistry.FRIED_GLUE_PUDDING.getSupplier(), ItemRegistry.FRIED_GLUE_PUDDING.getSupplier(), ItemRegistry.FRIED_GLUE_PUDDING.getSupplier(), ItemRegistry.FRIED_GLUE_PUDDING.getSupplier(), ItemRegistry.FRIED_GLUE_PUDDING.getSupplier());
    }

    public IntegerProperty getServingsProperty() {
        return GLUEPUDDING_SERVINGS;
    }

    public int getMaxServings() {
        return 5;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.GluePuddingServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, GLUEPUDDING_SERVINGS});
    }

    static {
        FOOD_SHAPE = PLATE_SHAPE;
    }
}