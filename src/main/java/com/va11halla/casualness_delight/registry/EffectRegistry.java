package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.va11halla.casualness_delight.effect.RottenEffect;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Casualness_Delight.MODID);
    public static final Holder<MobEffect> ROTTEN = EFFECTS.register("rotten", RottenEffect::new);;
}