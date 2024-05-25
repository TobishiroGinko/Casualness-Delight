package com.va11halla.casualness_delight;

import com.va11halla.casualness_delight.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasualnessDelight implements ModInitializer {
    public static final String MODID = "casualness_delight";
    public static final Logger LOGGER = LoggerFactory.getLogger("casualness_delight");
    @Override
    public void onInitialize() {
        ItemTab.register();
        ItemRegistry.register();
        BlocksRegistry.register();
        TagsRegistry.register();
        EffectRegistry.register();
        BlockEntityTypesRegistry.register();
        RecipeRegistry.register();
    }
}
