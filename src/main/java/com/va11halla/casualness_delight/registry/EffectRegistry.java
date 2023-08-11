package com.va11halla.casualness_delight.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.va11halla.casualness_delight.effect.RottenEffect;
public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS;
    public static final RegistryObject<MobEffect> RottenEffect;
    public EffectRegistry() {
    }
    static {
        EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "casualness_delight");
        RottenEffect = EFFECTS.register("rotten", RottenEffect::new);
    }
}
