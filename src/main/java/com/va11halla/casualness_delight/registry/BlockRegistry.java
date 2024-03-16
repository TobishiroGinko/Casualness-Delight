package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
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
    public static final RegistryObject<Block> PaperWrappedFish = BLOCKS.register("paper_wrapped_fish",
            () -> new PaperWrappedFish(Block.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> BoboChicken = BLOCKS.register("bobo_chicken",
            () -> new BoboChicken(Block.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> DeepFryingPan = BLOCKS.register("deep_frying_pan",
            () -> new DeepFryingPan(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)));
    public static final RegistryObject<Block> SpringRollMedley = BLOCKS.register("spring_roll_medley",
            () -> new SpringRollMedley(Block.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> PlateOfFriedDumpling = BLOCKS.register("plate_of_fried_dumpling",
            () -> new PlateOfFriedDumpling(Block.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Block> SweetRice = BLOCKS.register("sweet_rice",
            () -> new SweetRice(Block.Properties.copy(Blocks.CAKE)));

}
