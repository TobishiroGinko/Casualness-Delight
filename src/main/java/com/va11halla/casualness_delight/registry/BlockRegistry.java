package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.block.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.BuddingTomatoBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Casualness_Delight.MODID);
    public static final DeferredBlock<Block> StargazyPie = BLOCKS.register("stargazy_pie",
            () -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.STARGAZY_PIE_SLICE.getSupplier()));
    public static final DeferredBlock<Block> QuicheLorraine = BLOCKS.register("quiche_lorraine",
            () -> new PieBlock(Block.Properties.ofFullCopy(Blocks.CAKE), ItemRegistry.QUICHE_LORRAINE_SLICE.getSupplier()));
    //cheese_wheel
    public static final DeferredBlock<Block> RawCheeseWheel = BLOCKS.register("raw_cheese_wheel",
            () -> new RawCheeseWheel(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> CheeseWheel = BLOCKS.register("cheese_wheel",
            () -> new CheeseWheel(Block.Properties.ofFullCopy(Blocks.CAKE),ItemRegistry.CHEESE_WHEEL.getSupplier()));
    //Large Meal
    public static final DeferredBlock<Block> PaperWrappedFish = BLOCKS.register("paper_wrapped_fish",
            () -> new PaperWrappedFish(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> BoboChicken = BLOCKS.register("bobo_chicken",
            () -> new BoboChicken(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> DeepFryingPan = BLOCKS.register("deep_frying_pan",
            () -> new DeepFryingPan(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SpringRollMedley = BLOCKS.register("spring_roll_medley",
            () -> new SpringRollMedley(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> PlateOfFriedDumpling = BLOCKS.register("plate_of_fried_dumpling",
            () -> new PlateOfFriedDumpling(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> SweetRice = BLOCKS.register("sweet_rice",
            () -> new SweetRice(Block.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> BowlOfFiredGluePudding = BLOCKS.register("bowl_of_fired_glue_pudding",
            () -> new BowlOfFiredGluePudding(Block.Properties.ofFullCopy(Blocks.CAKE)));
    //Wild Crops
    public static final DeferredBlock<Block> WildCapsicums = BLOCKS.register("wild_capsicums",
            () -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    //Crops
    public static final DeferredBlock<Block> BuddingCapsicumsCrop = BLOCKS.register("budding_capsicums_crop",
            () -> new BuddingCapsicumsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> CapsicumsCrop = BLOCKS.register("capsicums_crop",
            () -> new CapsicumsVineBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
}