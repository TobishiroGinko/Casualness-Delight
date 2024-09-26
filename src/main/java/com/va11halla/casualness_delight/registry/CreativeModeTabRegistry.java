package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Casualness_Delight.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CasualnessDelightTab = CREATIVE_MODE_TABS.register("casualness_delight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.casualness_delight"))
                    .icon(() -> ItemRegistry.STARGAZY_PIE.getItem().getDefaultInstance())
                    .displayItems((parameters, output) -> ItemRegistry.REGISTRY_ITEMS.forEach((string,item) -> output.accept(item.get())) )
                    .build());
}
