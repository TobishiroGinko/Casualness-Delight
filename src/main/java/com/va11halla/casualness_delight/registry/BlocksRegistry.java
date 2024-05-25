package com.va11halla.casualness_delight.registry;

import vectorwing.farmersdelight.common.block.PieBlock;
import com.va11halla.casualness_delight.CasualnessDelight;
import com.va11halla.casualness_delight.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum BlocksRegistry {
    QuicheLorraine("quiche_lorraine",
            ()-> new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ItemRegistry.QuicheLorraineSlice::get)),
    StargazyPie("stargazy_pie",
            ()-> new PieBlock(FabricBlockSettings.copyOf(Blocks.CAKE),ItemRegistry.StargazyPieSlice::get)),
    RawCheeseWheel("raw_cheese_wheel", RawCheeseWheel::new),
    CheeseWheel("cheese_wheel",
            ()->new CheeseWheel(ItemRegistry.CheeseWheelSlice.get())),
    PaperWrappedFish("paper_wrapped_fish", ()->
            new PaperWrappedFish(FabricBlockSettings.copy(Blocks.CAKE))),
    BoboChicken("bobo_chicken", BoboChicken::new),
    DeepFryingPan("deep_frying_pan",DeepFryingPan::new),
    SweetRice("sweet_rice",SweetRice::new),
    SpringRollMedley("spring_roll_medley",SpringRollMedley::new),
    PlateOfFriedDumpling("plate_of_fried_dumpling",PlateOfFriedDumpling::new);
    private final String pathName;
    private final Supplier<Block> blockSupplier;
    private Block block;
    private BlocksRegistry(String pathName, Supplier blockSupplier){
        this.pathName=pathName;
        this.blockSupplier=blockSupplier;
    }
    public static void register() {
        BlocksRegistry[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            BlocksRegistry value = var0[var2];
            Registry.register(Registries.BLOCK, new Identifier(CasualnessDelight.MODID, value.pathName),value.get());
        }
    }
    public Block get() {
        if (block == null) {
            block = blockSupplier.get();
        }

        return block;
    }

    public String getId() {
        return Registries.BLOCK.getId(get()).toString();
    }
}

