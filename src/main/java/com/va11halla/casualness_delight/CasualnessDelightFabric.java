package com.va11halla.casualness_delight;

import com.va11halla.casualness_delight.registry.BlockRegistry;
import com.va11halla.casualness_delight.registry.EffectRegistry;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import com.va11halla.casualness_delight.registry.TagsRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasualnessDelightFabric implements ModInitializer {
    public static final String MODID = "casualness_delight";
    public static final Logger LOGGER = LoggerFactory.getLogger("casualness_delight");
    @Override
    public void onInitialize() {
        ItemRegistry.register();
        BlockRegistry.register();
        TagsRegistry.register();
        EffectRegistry.register();
    }
}