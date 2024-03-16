package com.va11halla.casualness_delight.item;

import com.va11halla.casualness_delight.registry.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FoodList {
    public static final FoodProperties FishAndChips = new FoodProperties.Builder().nutrition(14).saturationMod(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(),6000,0), 1).build();
    public static final FoodProperties YorkshirePudding = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties BeefNoodles = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0),1).build();
    public static final FoodProperties CheeseWheelSlice = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).build();
    public static final FoodProperties StargazyPieSlice = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION,200,0), 1).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS,300,0), 0.5F).build();
    public static final FoodProperties QuicheLorraineSlice = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(),1800,0), 1).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 1200, 0),1).build();
    public static final FoodProperties PhantomDumplings = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,1200,0), 1).build();
    public static final FoodProperties PhantomPuff = new FoodProperties.Builder().nutrition(4).saturationMod(0.8F).alwaysEat().fast().effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,800,0), 1).build();
    public static final FoodProperties SpicyStrips = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).alwaysEat().fast().effect(() -> new MobEffectInstance(EffectRegistry.RottenEffect.get(),1200,0),0.8F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED,600,1), 1).build();
    public static final FoodProperties GreenTongue = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).alwaysEat().fast().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,160,0), 1).effect(() -> new MobEffectInstance(MobEffects.JUMP,300,2), 1).build();
    public static final FoodProperties RoastGluten = new FoodProperties.Builder().nutrition(8).saturationMod(1F).alwaysEat().fast().build();
    public static final FoodProperties RawDonkeyMeat = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties CookedDonkeyMeat = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build();
    public static final FoodProperties DonkeyBurger = new FoodProperties.Builder().nutrition(14).saturationMod(0.8F).build();
    public static final FoodProperties BowlOfPaperWrappedFish = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F).effect(()-> new MobEffectInstance(ModEffects.COMFORT.get(), 3600, 0),1).build();
    public static final FoodProperties PotatoBoboChicken = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1).build();
    public static final FoodProperties ChickenBoboChicken = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1).build();
    public static final FoodProperties CabbageBoboChicken = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1).build();
    public static final FoodProperties PotatoChip = new FoodProperties.Builder().nutrition(4).saturationMod(1.0F).build();
    public static final FoodProperties SpringRoll = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.get(), 600, 0), 1).build();
    public static final FoodProperties FriedChickenChip = new FoodProperties.Builder().nutrition(3).saturationMod(0.8F).build();
    public static final FoodProperties FriedFish = new FoodProperties.Builder().nutrition(4).saturationMod(0.7F).build();
    public static final FoodProperties Tonkatsu = new FoodProperties.Builder().nutrition(8).saturationMod(0.85F).build();
    public static final FoodProperties FriedDumpling = new FoodProperties.Builder().nutrition(16).saturationMod(0.7F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1).build();
    public static final FoodProperties BowlOfFriedDumpling = new FoodProperties.Builder().nutrition(4).saturationMod(0.7F).build();
    public static final FoodProperties BowlOfSweetRice = new FoodProperties.Builder().nutrition(4).saturationMod(0.7F).build();
}
//RawGluten