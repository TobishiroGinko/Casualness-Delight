package com.va11halla.casualness_delight.item;

import com.va11halla.casualness_delight.registry.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public enum FoodList {
    /*public static final FoodProperties FishAndChips = new FoodProperties.Builder().nutrition(14).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.getDelegate(),6000,0), 1).build();
    public static final FoodProperties YorkshirePudding = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodProperties BeefNoodles = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6F).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 3600, 0),1).build();
    public static final FoodProperties CheeseWheelSlice = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).build();
    public static final FoodProperties StargazyPieSlice = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION,200,0), 1).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS,300,0), 0.5F).build();
    public static final FoodProperties QuicheLorraineSlice = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.getDelegate(),1800,0), 1).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 1200, 0),1).build();
    public static final FoodProperties PhantomDumplings = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,1200,0), 1).build();
    public static final FoodProperties PhantomPuff = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).alwaysEdible().fast().effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,800,0), 1).build();
    public static final FoodProperties SpicyStrips = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F).alwaysEdible().fast().effect(() -> new MobEffectInstance(EffectRegistry.ROTTEN.getDelegate(),1200,0),0.8F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED,600,1), 1).build();
    public static final FoodProperties GreenTongue = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).alwaysEdible().fast().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,160,0), 1).effect(() -> new MobEffectInstance(MobEffects.JUMP,300,2), 1).build();
    public static final FoodProperties RoastGluten = new FoodProperties.Builder().nutrition(8).saturationModifier(1F).alwaysEdible().fast().build();
    public static final FoodProperties RawDonkeyMeat = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties CookedDonkeyMeat = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build();
    public static final FoodProperties DonkeyBurger = new FoodProperties.Builder().nutrition(14).saturationModifier(0.8F).build();
    public static final FoodProperties BowlOfPaperWrappedFish = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.COMFORT.getDelegate(), 3600, 0),1).build();
    public static final FoodProperties PotatoBoboChicken = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build();
    public static final FoodProperties ChickenBoboChicken = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build();
    public static final FoodProperties CabbageBoboChicken = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build();
    public static final FoodProperties PotatoChip = new FoodProperties.Builder().nutrition(4).saturationModifier(1.0F).build();
    public static final FoodProperties SpringRoll = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build();
    public static final FoodProperties FriedChickenChip = new FoodProperties.Builder().nutrition(3).saturationModifier(0.8F).build();
    public static final FoodProperties FriedFish = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
    public static final FoodProperties Tonkatsu = new FoodProperties.Builder().nutrition(8).saturationModifier(0.85F).build();
    public static final FoodProperties FriedDumpling = new FoodProperties.Builder().nutrition(16).saturationModifier(0.7F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 3600, 0), 1).build();
    public static final FoodProperties BowlOfFriedDumpling = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
    public static final FoodProperties BowlOfSweetRice = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
    public static final FoodProperties Capsicum = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,0), 1).build();
    public static final FoodProperties CapsicumSauce = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,400,0), 1).build();
    public static final FoodProperties FiredCake = new FoodProperties.Builder().nutrition(5).saturationModifier(0.7F).build();
    public static final FoodProperties FiredMushroom = new FoodProperties.Builder().nutrition(3).saturationModifier(0.7F).build();
    public static final FoodProperties FiredStrangeMushroom = new FoodProperties.Builder().nutrition(3).saturationModifier(0.7F).effect(() -> new MobEffectInstance(MobEffects.POISON,100,0),0.3F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,400,0), 0.5f).build();
    public static final FoodProperties BowlOfGluePudding = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 2400, 0), 1).build();
    public static final FoodProperties FiredGluePudding = new FoodProperties.Builder().nutrition(5).saturationModifier(0.7F).effect(()->new MobEffectInstance(ModEffects.COMFORT.getDelegate(), 800, 0), 1).build();*/
    FISH_AND_CHIPS(new FoodProperties.Builder().nutrition(14).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.getDelegate(),6000,0), 1).build()),
    YORKSHIRE_PUDDING(new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build()),
    BEEF_NOODLES(new FoodProperties.Builder().nutrition(10).saturationModifier(0.6F).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 3600, 0),1).build()),
    CHEESE_WHEEL_SLICE(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).build()),
    STARGAZY_PIE_SLICE(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION,200,0), 1).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS,300,0), 0.5F).build()),
    QUICHE_LORRAINE_SLICE(new FoodProperties.Builder().nutrition(5).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT.getDelegate(),1800,0), 1).effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 1200, 0),1).build()),
    PHANTOM_DUMPLINGS(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,1200,0), 1).build()),
    PHANTOM_PUFF(new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).alwaysEdible().fast().effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING,800,0), 1).build()),
    SPICY_STRIPS(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5F).alwaysEdible().fast().effect(() -> new MobEffectInstance(EffectRegistry.ROTTEN.getDelegate(),1200,0),0.8F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED,600,1), 1).build()),
    GREEN_TONGUE(new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).alwaysEdible().fast().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,160,0), 1).effect(() -> new MobEffectInstance(MobEffects.JUMP,300,2), 1).build()),
    ROAST_GLUTEN(new FoodProperties.Builder().nutrition(8).saturationModifier(1F).alwaysEdible().fast().build()),
    RAW_DONKEY_MEAT(new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build()),
    COOKED_DONKEY_MEAT(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build()),
    DONKEY_BURGER(new FoodProperties.Builder().nutrition(14).saturationModifier(0.8F).build()),
    BOWL_OF_PAPER_WRAPPED_FISH(new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.COMFORT.getDelegate(), 3600, 0),1).build()),
    POTATO_BOBO_CHICKEN(new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build()),
    CHICKEN_BOBO_CHICKEN(new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build()),
    CABBAGE_BOBO_CHICKEN(new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()-> new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build()),
    POTATO_CHIP(new FoodProperties.Builder().nutrition(4).saturationModifier(1.0F).build()),
    SPRING_ROLL(new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 600, 0), 1).build()),
    FRIED_CHICKEN_CHIP(new FoodProperties.Builder().nutrition(3).saturationModifier(0.8F).build()),
    FRIED_FISH(new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build()),
    TONKATSU(new FoodProperties.Builder().nutrition(8).saturationModifier(0.85F).build()),
    RAW_FRIED_DUMPLING(new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).build()),
    FRIED_DUMPLING(new FoodProperties.Builder().nutrition(16).saturationModifier(0.7F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 3600, 0), 1).build()),
    BOWL_OF_FRIED_DUMPLING(new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build()),
    BOWL_OF_SWEET_RICE(new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build()),
    CAPSICUM(new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,0), 1).build()),
    CAPSICUM_SAUCE(new FoodProperties.Builder().nutrition(2).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,400,0), 1).build()),
    FRIED_CAKE(new FoodProperties.Builder().nutrition(5).saturationModifier(0.7F).build()),
    FRIED_MUSHROOM(new FoodProperties.Builder().nutrition(3).saturationModifier(0.7F).build()),
    FRIED_STRANGE_MUSHROOM(new FoodProperties.Builder().nutrition(3).saturationModifier(0.7F).effect(() -> new MobEffectInstance(MobEffects.POISON,100,0),0.3F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE,400,0), 0.5f).build()),
    BOWL_OF_GLUE_PUDDING(new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.getDelegate(), 2400, 0), 1).build()),
    RAW_GLUE_PUDDING(new FoodProperties.Builder().nutrition(2).saturationModifier(0.5F).build()),
    FRIED_GLUE_PUDDING(new FoodProperties.Builder().nutrition(5).saturationModifier(0.7F).effect(()->new MobEffectInstance(ModEffects.COMFORT.getDelegate(), 800, 0), 1).build());

    private final FoodProperties foodProperties;

    FoodList(FoodProperties foodProperties) {
        this.foodProperties = foodProperties;
    }

    public FoodProperties getFoodProperties() {
        return foodProperties;
    }
}