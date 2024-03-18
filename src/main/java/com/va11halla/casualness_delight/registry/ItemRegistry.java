package com.va11halla.casualness_delight.registry;

import com.nhoryzon.mc.farmersdelight.item.ConsumableItem;
import com.va11halla.casualness_delight.CasualnessDelightFabric;
import com.va11halla.casualness_delight.item.FoodList;
import com.va11halla.casualness_delight.item.TooltipItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum ItemRegistry {
    FishAndChips("fish_and_chips",
            () -> new ConsumableItem(new FabricItemSettings().food(FoodList.FishAndChips).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP), true)),
    YorkshirePudding("yorkshire_pudding",
            () -> new ConsumableItem(new FabricItemSettings().food(FoodList.YorkshirePudding).group(ItemTab.ITEM_GROUP))),
    BeefNoodles("beef_noodles",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.BeefNoodles).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP), true)),
    //Pie
    QuicheLorraine("quiche_lorraine",
            () -> new BlockItem(BlocksRegistry.QuicheLorraine.get(), new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    QuicheLorraineSlice("quiche_lorraine_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.QuicheLorraineSlice).group(ItemTab.ITEM_GROUP), true)),
    StargazyPie("stargazy_pie",
            () -> new BlockItem(BlocksRegistry.StargazyPie.get(), new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    StargazyPieSlice("stargazy_pie_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.StargazyPieSlice).group(ItemTab.ITEM_GROUP), false,true)),
    RawCheeseWheel("raw_cheese_wheel",
            () -> new BlockItem(BlocksRegistry.RawCheeseWheel.get(), new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    CheeseWheel("cheese_wheel",
            () -> new BlockItem(BlocksRegistry.CheeseWheel.get(), new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    CheeseWheelSlice("cheese_wheel_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.CheeseWheelSlice).group(ItemTab.ITEM_GROUP))),
    PhantomDumplings("phantom_dumplings",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PhantomDumplings).group(ItemTab.ITEM_GROUP),true)),
    PhantomPuff("phantom_puff",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PhantomPuff).group(ItemTab.ITEM_GROUP),true)),
    SpicyStrips("spicy_strips",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.SpicyStrips).group(ItemTab.ITEM_GROUP),true)),
    GreenTongue("green_tongue",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.GreenTongue).group(ItemTab.ITEM_GROUP),true)),
    //Gluten
    RawGluten("raw_gluten",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    Gluten("gluten",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    GlutenSkewer("gluten_skewer",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    RoastGluten("roast_gluten",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.RoastGluten).group(ItemTab.ITEM_GROUP))),
    RawDonkeyMeat("raw_donkey_meat",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.RawDonkeyMeat).group(ItemTab.ITEM_GROUP))),
    CookedDonkeyMeat("cooked_donkey_meat",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.CookedDonkeyMeat).group(ItemTab.ITEM_GROUP))),
    DonkeyBurger("donkey_burger",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.DonkeyBurger).group(ItemTab.ITEM_GROUP))),
    BowlOfPaperWrappedFish ("bowl_of_paper_wrapped_fish",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfPaperWrappedFish).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP),true)),
    RawPotatoBoboChicken ("raw_potato_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP),true)),
    RawChickenBoboChicken("raw_chicken_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP),true)),
    RawCabbageBoboChicken("raw_cabbage_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP),true)),
    PotatoBoboChicken ("potato_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PotatoBoboChicken).group(ItemTab.ITEM_GROUP),true)),
    ChickenBoboChicken("chicken_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.ChickenBoboChicken).group(ItemTab.ITEM_GROUP),true)),
    CabbageBoboChicken("cabbage_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.CabbageBoboChicken).group(ItemTab.ITEM_GROUP),true)),
    PaperWrappedFish ("paper_wrapped_fish",
            ()-> new BlockItem(BlocksRegistry.PaperWrappedFish.get(), new FabricItemSettings().group(ItemTab.ITEM_GROUP).maxCount(1))),
    BoboChicken("bobo_chicken",
            ()-> new BlockItem(BlocksRegistry.BoboChicken.get(),new FabricItemSettings().group(ItemTab.ITEM_GROUP).maxCount(1))),
    PotatoSlice("potato_slice",
            ()-> new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    PotatoChip("potato_chip",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PotatoChip).group(ItemTab.ITEM_GROUP))),
    RawSpringRoll("raw_spring_roll",
            ()->new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    SpringRoll("spring_roll",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.SpringRoll).group(ItemTab.ITEM_GROUP))),
    SpringRollMedley("spring_roll_medley",
            ()->new BlockItem(BlocksRegistry.SpringRollMedley.get(), new FabricItemSettings().maxCount(16).group(ItemTab.ITEM_GROUP))),
    FriedChickenChip("fried_chicken_chip",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedChickenChip).group(ItemTab.ITEM_GROUP))),
    FriedFish("fried_fish",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedFish).group(ItemTab.ITEM_GROUP))),
    Tonkatsu("tonkatsu",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.Tonkatsu).group(ItemTab.ITEM_GROUP))),
    SweetRice("sweet_rice",
            ()-> new BlockItem(BlocksRegistry.SweetRice.get(),new FabricItemSettings().maxCount(1).group(ItemTab.ITEM_GROUP))),
    BowlOfSweetRice("bowl_of_sweet_rice",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfSweetRice).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP))),
    DeepFryingPan("deep_frying_pan",
            ()->new BlockItem(BlocksRegistry.DeepFryingPan.get(),new FabricItemSettings().maxCount(1).group(ItemTab.ITEM_GROUP))),

    RawFriedDumpling("raw_fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    FriedDumpling("fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedDumpling).maxCount(1).group(ItemTab.ITEM_GROUP))),
    PlateOFFriedDumpling("plate_of_fried_dumpling",
            ()->new BlockItem(BlocksRegistry.PlateOfFriedDumpling.get(), new FabricItemSettings().maxCount(1).group(ItemTab.ITEM_GROUP))),
    BowlOfFriedDumpling("bowl_of_fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfFriedDumpling).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP)))
    ;
    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    private ItemRegistry(String pathName, Supplier itemSupplier) {
        this(pathName, itemSupplier, (Integer)null);
    }

    private ItemRegistry(String pathName, Supplier itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
        this.burnTime = burnTime;
    }

    public static void register() {
        ItemRegistry[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            ItemRegistry value = var0[var2];
            Registry.register(Registry.ITEM, new Identifier(CasualnessDelightFabric.MODID, value.pathName), value.get());
            if (value.burnTime != null && value.burnTime > 0) {
                FuelRegistry.INSTANCE.add(value.get(), value.burnTime);
            }
        }
    }

    public Item get() {
        if (this.item == null) {
            this.item = (Item)this.itemSupplier.get();
        }

        return this.item;
    }

    public String getId() {
        return Registry.ITEM.getId(this.get()).toString();
    }
}