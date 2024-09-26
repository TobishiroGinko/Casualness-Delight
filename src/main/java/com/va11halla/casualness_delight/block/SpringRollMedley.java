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

public class SpringRollMedley extends FeastBlock {
    public static final IntegerProperty SPRING_SERVINGS = IntegerProperty.create("servings", 0, 6);
    protected static final VoxelShape PLATE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<Supplier<Item>> SpringRollServings;

    public SpringRollMedley(Properties properties) {
        super(properties, ItemRegistry.SPRING_ROLL.getSupplier(), true);
        this.SpringRollServings = Arrays.asList(ItemRegistry.SPRING_ROLL.getSupplier(), ItemRegistry.SPRING_ROLL.getSupplier(), ItemRegistry.SPRING_ROLL.getSupplier(), ItemRegistry.SPRING_ROLL.getSupplier(), ItemRegistry.SPRING_ROLL.getSupplier(), ItemRegistry.SPRING_ROLL.getSupplier());
    }

    public IntegerProperty getServingsProperty() {
        return SPRING_SERVINGS;
    }

    public int getMaxServings() {
        return 6;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.SpringRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, SPRING_SERVINGS});
    }

    static {
        FOOD_SHAPE = PLATE_SHAPE;
    }
}