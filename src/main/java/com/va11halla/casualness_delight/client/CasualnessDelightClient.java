package com.va11halla.casualness_delight.client;

import com.va11halla.casualness_delight.registry.BlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class CasualnessDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.DeepFryingPan.get(), RenderLayer.getCutout());
    }
}
