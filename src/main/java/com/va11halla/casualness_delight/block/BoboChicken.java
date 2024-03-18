package com.va11halla.casualness_delight.block;

import com.nhoryzon.mc.farmersdelight.block.FeastBlock;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.List;

public class BoboChicken extends FeastBlock {
    public static final int MAX_SERVING = 6;
    public static final IntProperty BOBO_SERVINGS = IntProperty.of("servings", 0, 6);
    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 5.0, 15.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<ItemRegistry> boboChickenServings;

    public BoboChicken() {
        super(Settings.copy(Blocks.CAKE), ItemRegistry.PotatoBoboChicken.get(), true);
        this.boboChickenServings = List.of(ItemRegistry.ChickenBoboChicken, ItemRegistry.CabbageBoboChicken, ItemRegistry.PotatoBoboChicken, ItemRegistry.ChickenBoboChicken, ItemRegistry.CabbageBoboChicken, ItemRegistry.PotatoBoboChicken);
    }

    public IntProperty getServingsProperty() {
        return BOBO_SERVINGS;
    }

    public int getMaxServings() {
        return 6;
    }

    public ItemStack getServingStack(BlockState state) {
        return new ItemStack(((ItemRegistry)this.boboChickenServings.get((Integer)state.get(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Integer)state.get(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, BOBO_SERVINGS});
    }

    static {
        FOOD_SHAPE = PLATE_SHAPE;
    }
}
