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

public class SpringRollMedley extends FeastBlock {
    public static final int MAX_SERVING = 6;
    public static final IntProperty ROLL_SERVINGS = IntProperty.of("servings", 0, 6);
    protected static final VoxelShape PLATE_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<ItemRegistry> springRollServings;

    public SpringRollMedley() {
        super(Settings.copy(Blocks.CAKE), ItemRegistry.PotatoBoboChicken.get(), true);
        this.springRollServings = List.of(ItemRegistry.SpringRoll, ItemRegistry.SpringRoll, ItemRegistry.SpringRoll, ItemRegistry.SpringRoll, ItemRegistry.SpringRoll,ItemRegistry.SpringRoll);
    }

    public IntProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    public int getMaxServings() {
        return 6;
    }

    public ItemStack getServingStack(BlockState state) {
        return new ItemStack(((ItemRegistry)this.springRollServings.get((Integer)state.get(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Integer)state.get(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, ROLL_SERVINGS});
    }

    static {
        FOOD_SHAPE = PLATE_SHAPE;
    }
}
