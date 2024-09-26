package com.va11halla.casualness_delight.data;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.data.recipe.DeepFryingRecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Casualness_Delight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();
        gen.addProvider(event.includeServer(), new DeepFryingRecipeProvider(packOutput, lookupProvider));
    }
}
