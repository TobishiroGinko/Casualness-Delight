package com.va11halla.casualness_delight.registry;

import com.nhoryzon.mc.farmersdelight.block.PieBlock;
import com.va11halla.casualness_delight.CasualnessDelightFabric;
import com.va11halla.casualness_delight.block.RawCheeseWheel;
import com.va11halla.casualness_delight.block.CheeseWheel;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    public static final Block QuicheLorraine = registerBlock("quiche_lorraine", new PieBlock(ItemRegistry.QuicheLorraineSlice.get()));
    public static final Block StargazyPie = registerBlock("stargazy_pie", new PieBlock(ItemRegistry.StargazyPieSlice.get()));
    public static final Block RawCheeseWheel = registerBlock("raw_cheese_wheel", new RawCheeseWheel());
    public static final Block CheeseWheel = registerBlock("cheese_wheel", new CheeseWheel(ItemRegistry.CheeseWheelSlice.get()));
    private static Block registerBlock(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(CasualnessDelightFabric.MODID, name), block);
    }
    public static void register(){
        CasualnessDelightFabric.LOGGER.info("Registering Mod Blocks for " + CasualnessDelightFabric.MODID);
    }
}

