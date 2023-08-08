package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.block.RawCheeseWheel;
import com.va11halla.casualness_delight.block.CheeseWheel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.PieBlock;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Casualness_Delight.MODID);
    public static final RegistryObject<Block> StargazyPie = BLOCKS.register("stargazy_pie",
            () -> new PieBlock(Block.Properties.copy(Blocks.CAKE), ItemRegistry.StargazyPieSlice));
    public static final RegistryObject<Block> QuicheLorraine = BLOCKS.register("quiche_lorraine",
            () -> new PieBlock(Block.Properties.copy(Blocks.CAKE), ItemRegistry.QuicheLorraineSlice));
    //cheese_wheel
    public static final RegistryObject<Block> RawCheeseWheel = BLOCKS.register("raw_cheese_wheel",
            () -> new RawCheeseWheel(Block.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> CheeseWheel = BLOCKS.register("cheese_wheel",
            () -> new CheeseWheel(Block.Properties.copy(Blocks.CAKE),ItemRegistry.CheeseWheelSlice));
}
