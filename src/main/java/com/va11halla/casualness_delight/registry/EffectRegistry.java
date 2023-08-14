package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.effect.RottenEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum EffectRegistry {
    ROTTEN("rotten", new RottenEffect());

    private final String pathName;
    private final StatusEffect effect;

    private EffectRegistry(String pathName, StatusEffect effect) {
        this.pathName = pathName;
        this.effect = effect;
    }

    public static void register() {
        EffectRegistry[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            EffectRegistry value = var0[var2];
            Registry.register(Registry.STATUS_EFFECT, new Identifier("casualness_delight", value.pathName), value.effect);
        }

    }

    public StatusEffect get() {
        return this.effect;
    }
}