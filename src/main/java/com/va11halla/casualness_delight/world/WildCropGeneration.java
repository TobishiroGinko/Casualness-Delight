package com.va11halla.casualness_delight.world;

import com.va11halla.casualness_delight.Casualness_Delight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WildCropGeneration {
    //虽然妹使（但我也写上（
    public static ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_CAPSICUMS;
    public static ResourceKey<PlacedFeature> PATCH_WILD_CAPSICUMS;

    public static void load() {
    }

    static {
        FEATURE_PATCH_WILD_CAPSICUMS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Casualness_Delight.MODID, "patch_wild_capsicums"));
        PATCH_WILD_CAPSICUMS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Casualness_Delight.MODID, "patch_wild_capsicums"));
    }
}
