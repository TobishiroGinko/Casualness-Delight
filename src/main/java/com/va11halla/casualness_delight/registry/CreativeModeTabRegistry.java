package com.va11halla.casualness_delight.registry;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.va11halla.casualness_delight.Casualness_Delight.MODID;


public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> EndsDelightTab = CREATIVE_MODE_TABS.register("casualness_delight", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.casualness_delight"))
            .icon(() -> ItemRegistry.StargazyPie.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemRegistry.FishAndChips.get());
                output.accept(ItemRegistry.YorkshirePudding.get());
                output.accept(ItemRegistry.BeefNoodles.get());
                output.accept(ItemRegistry.QuicheLorraine.get());
                output.accept(ItemRegistry.StargazyPie.get());
                output.accept(ItemRegistry.StargazyPieSlice.get());
                output.accept(ItemRegistry.QuicheLorraineSlice.get());
                output.accept(ItemRegistry.RawCheeseWheel.get());
                output.accept(ItemRegistry.CheeseWheel.get());
                output.accept(ItemRegistry.CheeseWheelSlice.get());
                output.accept(ItemRegistry.PhantomDumplings.get());
                output.accept(ItemRegistry.PhantomPuff.get());
                output.accept(ItemRegistry.SpicyStrips.get());
                output.accept(ItemRegistry.GreenTongue.get());
                output.accept(ItemRegistry.RawGluten.get());
                output.accept(ItemRegistry.Gluten.get());
                output.accept(ItemRegistry.GlutenSkewer.get());
                output.accept(ItemRegistry.RoastGluten.get());
            }).build());
}
