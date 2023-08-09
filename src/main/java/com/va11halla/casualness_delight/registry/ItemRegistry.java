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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public enum ItemRegistry {
    FishAndChips("fish_and_chips",
            () -> new ConsumableItem(new FabricItemSettings().food(FoodList.FishAndChips).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP), true)),
    YorkshirePudding("yorkshire_pudding",
            () -> new Item(new FabricItemSettings().food(FoodList.YorkshirePudding).group(ItemTab.ITEM_GROUP))),
    BeefNoodles("beef_noodles",
            ()-> new ConsumableItem(new FabricItemSettings().food(FoodList.BeefNoodles).maxCount(16).recipeRemainder(Items.BOWL).group(ItemTab.ITEM_GROUP), true)),
    //Pie
    QuicheLorraine("quiche_lorraine",
            () -> new BlockItem(BlockRegistry.QuicheLorraine, new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    QuicheLorraineSlice("quiche_lorraine_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.QuicheLorraineSlice).group(ItemTab.ITEM_GROUP), true)),
    StargazyPie("stargazy_pie",
            () -> new BlockItem(BlockRegistry.StargazyPie, new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    StargazyPieSlice("stargazy_pie_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.QuicheLorraineSlice).group(ItemTab.ITEM_GROUP), false,true)),
    RawCheeseWheel("raw_cheese_wheel",
            () -> new BlockItem(BlockRegistry.RawCheeseWheel, new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    CheeseWheel("cheese_wheel",
            () -> new BlockItem(BlockRegistry.CheeseWheel, new FabricItemSettings().group(ItemTab.ITEM_GROUP))),
    CheeseWheelSlice("cheese_wheel_slice",
            ()-> new TooltipItem(new FabricItemSettings().food(FoodList.CheeseWheelSlice).group(ItemTab.ITEM_GROUP)))
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