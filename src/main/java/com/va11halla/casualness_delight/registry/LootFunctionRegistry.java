package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.loot.function.CopyDeepFryingPanFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;

import java.util.function.Supplier;

public class LootFunctionRegistry {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS;
    public static final Supplier<LootItemFunctionType<CopySkilletFunction>> COPY_DEEP_FRYING_PAN;

    public LootFunctionRegistry() {
    }
    static {
        LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, "casualness_delight");
        COPY_DEEP_FRYING_PAN = LOOT_FUNCTIONS.register("copy_deep_frying_pan", () -> {
            return new LootItemFunctionType(CopyDeepFryingPanFunction.CODEC);
        });
    }
}
