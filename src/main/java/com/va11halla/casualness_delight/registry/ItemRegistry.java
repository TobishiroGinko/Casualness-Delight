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
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.RoastGluten).craftRemainder(Items.STICK)));
}
//