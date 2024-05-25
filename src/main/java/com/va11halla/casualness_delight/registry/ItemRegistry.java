package com.va11halla.casualness_delight.registry;

import vectorwing.farmersdelight.common.item.ConsumableItem;
import com.va11halla.casualness_delight.CasualnessDelight;
import com.va11halla.casualness_delight.item.FoodList;
import com.va11halla.casualness_delight.item.TooltipItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public enum ItemRegistry {
    FishAndChips("fish_and_chips",
            () -> new ConsumableItem(new FabricItemSettings().food(FoodList.FishAndChips).maxCount(16).recipeRemainder(Items.BOWL), true)),
    YorkshirePudding("yorkshire_pudding",
            () -> new ConsumableItem(new FabricItemSettings().food(FoodList.YorkshirePudding))),
    BeefNoodles("beef_noodles",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.BeefNoodles).maxCount(16).recipeRemainder(Items.BOWL), true)),
    //Pie
    QuicheLorraine("quiche_lorraine",
            () -> new BlockItem(BlocksRegistry.QuicheLorraine.get(), new FabricItemSettings())),
    QuicheLorraineSlice("quiche_lorraine_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.QuicheLorraineSlice), true)),
    StargazyPie("stargazy_pie",
            () -> new BlockItem(BlocksRegistry.StargazyPie.get(), new FabricItemSettings())),
    StargazyPieSlice("stargazy_pie_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.StargazyPieSlice), false,true)),
    RawCheeseWheel("raw_cheese_wheel",
            () -> new BlockItem(BlocksRegistry.RawCheeseWheel.get(), new FabricItemSettings())),
    CheeseWheel("cheese_wheel",
            () -> new BlockItem(BlocksRegistry.CheeseWheel.get(), new FabricItemSettings())),
    CheeseWheelSlice("cheese_wheel_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.CheeseWheelSlice))),
    PhantomDumplings("phantom_dumplings",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PhantomDumplings),true)),
    PhantomPuff("phantom_puff",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PhantomPuff),true)),
    SpicyStrips("spicy_strips",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.SpicyStrips),true)),
    GreenTongue("green_tongue",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.GreenTongue),true)),
    //Gluten
    RawGluten("raw_gluten",
            ()-> new ConsumableItem(new FabricItemSettings())),
    Gluten("gluten",
            ()-> new ConsumableItem(new FabricItemSettings())),
    GlutenSkewer("gluten_skewer",
            ()-> new ConsumableItem(new FabricItemSettings())),
    RoastGluten("roast_gluten",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.RoastGluten))),
    RawDonkeyMeat("raw_donkey_meat",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.RawDonkeyMeat))),
    CookedDonkeyMeat("cooked_donkey_meat",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.CookedDonkeyMeat))),
    DonkeyBurger("donkey_burger",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.DonkeyBurger))),
    BowlOfPaperWrappedFish ("bowl_of_paper_wrapped_fish",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfPaperWrappedFish).maxCount(16).recipeRemainder(Items.BOWL),true)),
    RawPotatoBoboChicken ("raw_potato_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings(),true)),
    RawChickenBoboChicken("raw_chicken_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings(),true)),
    RawCabbageBoboChicken("raw_cabbage_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings(),true)),
    PotatoBoboChicken ("potato_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PotatoBoboChicken),true)),
    ChickenBoboChicken("chicken_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.ChickenBoboChicken),true)),
    CabbageBoboChicken("cabbage_bobo_chicken",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.CabbageBoboChicken),true)),
    PaperWrappedFish ("paper_wrapped_fish",
            ()-> new BlockItem(BlocksRegistry.PaperWrappedFish.get(), new FabricItemSettings().maxCount(1))),
    BoboChicken("bobo_chicken",
            ()-> new BlockItem(BlocksRegistry.BoboChicken.get(),new FabricItemSettings().maxCount(1))),
    PotatoSlice("potato_slice",
            ()-> new ConsumableItem(new FabricItemSettings())),
    PotatoChip("potato_chip",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.PotatoChip))),
    RawSpringRoll("raw_spring_roll",
            ()->new ConsumableItem(new FabricItemSettings())),
    SpringRoll("spring_roll",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.SpringRoll))),
    SpringRollMedley("spring_roll_medley",
            ()->new BlockItem(BlocksRegistry.SpringRollMedley.get(), new FabricItemSettings().maxCount(16))),
    FriedChickenChip("fried_chicken_chip",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedChickenChip))),
    FriedFish("fried_fish",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedFish))),
    Tonkatsu("tonkatsu",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.Tonkatsu))),
    SweetRice("sweet_rice",
            ()-> new BlockItem(BlocksRegistry.SweetRice.get(),new FabricItemSettings().maxCount(1))),
    BowlOfSweetRice("bowl_of_sweet_rice",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfSweetRice).maxCount(16).recipeRemainder(Items.BOWL))),
    DeepFryingPan("deep_frying_pan",
            ()->new BlockItem(BlocksRegistry.DeepFryingPan.get(),new FabricItemSettings().maxCount(1))),

    RawFriedDumpling("raw_fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings())),
    FriedDumpling("fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.FriedDumpling).maxCount(1))),
    PlateOFFriedDumpling("plate_of_fried_dumpling",
            ()->new BlockItem(BlocksRegistry.PlateOfFriedDumpling.get(), new FabricItemSettings().maxCount(1))),
    BowlOfFriedDumpling("bowl_of_fried_dumpling",
            ()->new ConsumableItem(new FabricItemSettings().food(FoodList.BowlOfFriedDumpling).maxCount(16).recipeRemainder(Items.BOWL)))
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
            Registry.register(Registries.ITEM, new Identifier(CasualnessDelight.MODID, value.pathName), value.get());
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
        return Registries.ITEM.getId(this.get()).toString();
    }
}