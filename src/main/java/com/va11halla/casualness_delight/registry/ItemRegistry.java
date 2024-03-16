package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.item.FoodList;
import com.va11halla.casualness_delight.item.SliceItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class  ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Casualness_Delight.MODID);
    public static final RegistryObject<Item> FishAndChips = ITEMS.register("fish_and_chips",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.FishAndChips).craftRemainder(Items.BOWL), false));
    public static final RegistryObject<Item> YorkshirePudding = ITEMS.register("yorkshire_pudding",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.YorkshirePudding)));
    public static final RegistryObject<Item> BeefNoodles = ITEMS.register("beef_noodles",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.BeefNoodles).craftRemainder(Items.BOWL), true));
    //Pie
    public static final RegistryObject<Item> QuicheLorraine = ITEMS.register("quiche_lorraine",
            () -> new BlockItem(BlockRegistry.QuicheLorraine.get(), new Item.Properties()));
    public static final RegistryObject<Item> StargazyPie = ITEMS.register("stargazy_pie",
            () -> new BlockItem(BlockRegistry.StargazyPie.get(), new Item.Properties()));
    public static final RegistryObject<Item> StargazyPieSlice = ITEMS.register("stargazy_pie_slice",
            ()-> new SliceItem(new Item.Properties().food(FoodList.StargazyPieSlice), false,true));
    public static final RegistryObject<Item> QuicheLorraineSlice = ITEMS.register("quiche_lorraine_slice",
            ()-> new SliceItem(new Item.Properties().food(FoodList.QuicheLorraineSlice), true));
    public static final RegistryObject<Item> RawCheeseWheel = ITEMS.register("raw_cheese_wheel",
            () -> new BlockItem(BlockRegistry.RawCheeseWheel.get(), new Item.Properties()));
    public static final RegistryObject<Item> CheeseWheel = ITEMS.register("cheese_wheel",
            () -> new BlockItem(BlockRegistry.CheeseWheel.get(), new Item.Properties()));
    public static final RegistryObject<Item> PaperWrappedFish = ITEMS.register("paper_wrapped_fish",
            () -> new BlockItem(BlockRegistry.PaperWrappedFish.get(), new Item.Properties()));
    public static final RegistryObject<Item> BoboChicken = ITEMS.register("bobo_chicken",
            () -> new BlockItem(BlockRegistry.BoboChicken.get(), new Item.Properties()));
    public static final RegistryObject<Item> CheeseWheelSlice = ITEMS.register("cheese_wheel_slice",
            ()-> new SliceItem(new Item.Properties().food(FoodList.CheeseWheelSlice)));
    public static final RegistryObject<Item> PhantomDumplings = ITEMS.register("phantom_dumplings",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.PhantomDumplings)));
    public static final RegistryObject<Item> PhantomPuff = ITEMS.register("phantom_puff",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.PhantomPuff)));
    public static final RegistryObject<Item> SpicyStrips = ITEMS.register("spicy_strips",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.SpicyStrips),true));
    public static final RegistryObject<Item> GreenTongue = ITEMS.register("green_tongue",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.GreenTongue),true));
    //Gluten
    public static final RegistryObject<Item> RawGluten = ITEMS.register("raw_gluten",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> Gluten = ITEMS.register("gluten",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> GlutenSkewer = ITEMS.register("gluten_skewer",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> RoastGluten = ITEMS.register("roast_gluten",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.RoastGluten)));
    public static final RegistryObject<Item> RawDonkeyMeat = ITEMS.register("raw_donkey_meat",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.RawDonkeyMeat)));
    public static final RegistryObject<Item> CookedDonkeyMeat = ITEMS.register("cooked_donkey_meat",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.CookedDonkeyMeat)));
    public static final RegistryObject<Item> DonkeyBurger = ITEMS.register("donkey_burger",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.DonkeyBurger)));
    public static final RegistryObject<Item> BowlOfPaperWrappedFish = ITEMS.register("bowl_of_paper_wrapped_fish",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.BowlOfPaperWrappedFish).craftRemainder(Items.BOWL), true));
    public static final RegistryObject<Item> RawPotatoBoboChicken = ITEMS.register("raw_potato_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> RawCabbageBoboChicken = ITEMS.register("raw_cabbage_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> RawChickenBoboChicken = ITEMS.register("raw_chicken_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> PotatoBoboChicken = ITEMS.register("potato_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.PotatoBoboChicken), true));
    public static final RegistryObject<Item> CabbageBoboChicken = ITEMS.register("cabbage_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.CabbageBoboChicken), true));
    public static final RegistryObject<Item> ChickenBoboChicken = ITEMS.register("chicken_bobo_chicken",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.ChickenBoboChicken), true));
    public static final RegistryObject<Item> PotatoSlice = ITEMS.register("potato_slice",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> PotatoChip = ITEMS.register("potato_chip",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.PotatoChip)));
    public static final RegistryObject<Item> RawSpringRoll = ITEMS.register("raw_spring_roll",
            ()-> new ConsumableItem(new Item.Properties()));
    public static final RegistryObject<Item> SpringRoll = ITEMS.register("spring_roll",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.SpringRoll)));
    public static final RegistryObject<Item> SpringRollMedley = ITEMS.register("spring_roll_medley",
            ()->new BlockItem(BlockRegistry.SpringRollMedley.get(), new Item.Properties()));
    public static final RegistryObject<Item> FriedChickenChip = ITEMS.register("fried_chicken_chip",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.FriedChickenChip)));
    public static final RegistryObject<Item> FriedFish = ITEMS.register("fried_fish",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.FriedFish)));
    public static final RegistryObject<Item> Tonkatsu = ITEMS.register("tonkatsu",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.Tonkatsu)));
    public static final RegistryObject<Item> DeepFryingPan = ITEMS.register("deep_frying_pan",
            () -> new BlockItem(BlockRegistry.DeepFryingPan.get(), new Item.Properties()));
    public static final RegistryObject<Item> RawFriedDumpling = ITEMS.register("raw_fried_dumpling",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.FriedDumpling)));
    public static final RegistryObject<Item> FriedDumpling = ITEMS.register("fried_dumpling",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.FriedDumpling)));
    public static final RegistryObject<Item> PlateOfFriedDumpling = ITEMS.register("plate_of_fried_dumpling",
            ()-> new BlockItem(BlockRegistry.PlateOfFriedDumpling.get(), new Item.Properties()));
    public static final RegistryObject<Item> BowlOfFriedDumpling = ITEMS.register("bowl_of_fried_dumpling",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.BowlOfFriedDumpling).craftRemainder(Items.BOWL)));
    public static final RegistryObject<Item> SweetRice = ITEMS.register("sweet_rice",
            ()-> new BlockItem(BlockRegistry.SweetRice.get(), new Item.Properties()));
    public static final RegistryObject<Item> BowlOfSweetRice = ITEMS.register("bowl_of_sweet_rice",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.BowlOfSweetRice).craftRemainder(Items.BOWL)));

}
//