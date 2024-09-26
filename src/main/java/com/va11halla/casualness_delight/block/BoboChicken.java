package com.va11halla.casualness_delight.block;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class BoboChicken extends FeastBlock {
    public static final IntegerProperty BOBO_SERVINGS = IntegerProperty.create("servings", 0, 6);
    protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 5.0, 15.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<Supplier<Item>> BoboServings;

    public BoboChicken(BlockBehaviour.Properties properties) {
        super(properties, ItemRegistry.POTATO_BOBO_CHICKEN.getSupplier(), true);
        this.BoboServings = Arrays.asList(ItemRegistry.POTATO_BOBO_CHICKEN.getSupplier(), ItemRegistry.CABBAGE_BOBO_CHICKEN.getSupplier(), ItemRegistry.POTATO_BOBO_CHICKEN.getSupplier(), ItemRegistry.CHICKEN_BOBO_CHICKEN.getSupplier(), ItemRegistry.CABBAGE_BOBO_CHICKEN.getSupplier(), ItemRegistry.POTATO_BOBO_CHICKEN.getSupplier());
    }

    public IntegerProperty getServingsProperty() {
        return BOBO_SERVINGS;
    }

    public int getMaxServings() {
        return 6;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.BoboServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, BOBO_SERVINGS});
    }

    static {
        FOOD_SHAPE = PLATE_SHAPE;
    }
}