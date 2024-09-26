package com.va11halla.casualness_delight.registry;

import com.google.common.collect.Maps;
import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.item.FoodList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.LinkedHashMap;
import java.util.function.Supplier;
import java.util.Map;

public enum ItemRegistry {
    FISH_AND_CHIPS("fish_and_chips",
            ()-> new ConsumableItem(bowlFoodItem(FoodList.FISH_AND_CHIPS.getFoodProperties()), false)),
    YORKSHIRE_PUDDING("yorkshire_pudding",
            ()-> new ConsumableItem(foodItem(FoodList.YORKSHIRE_PUDDING.getFoodProperties()))),
    BEEF_NOODLES("beef_noodles",
            ()-> new ConsumableItem(bowlFoodItem(FoodList.BEEF_NOODLES.getFoodProperties()), true)),
    PHANTOM_DUMPLINGS("phantom_dumplings",
            ()-> new ConsumableItem(foodItem(FoodList.PHANTOM_DUMPLINGS.getFoodProperties()),true)),
    PHANTOM_PUFF("phantom_puff",
            ()-> new ConsumableItem(foodItem(FoodList.PHANTOM_PUFF.getFoodProperties()),true)),
    QUICHE_LORRAINE("quiche_lorraine",
            () -> new BlockItem(BlockRegistry.QuicheLorraine.get(), basicItem())),
    QUICHE_LORRAINE_SLICE("quiche_lorraine_slice",
            ()-> new ConsumableItem(foodItem(FoodList.QUICHE_LORRAINE_SLICE.getFoodProperties()), true)),
    STARGAZY_PIE("stargazy_pie",
            () -> new BlockItem(BlockRegistry.StargazyPie.get(), basicItem())),
    STARGAZY_PIE_SLICE("stargazy_pie_slice",
            ()-> new ConsumableItem(foodItem(FoodList.STARGAZY_PIE_SLICE.getFoodProperties()), false,true)),
    RAW_CHEESE_WHEEL("raw_cheese_wheel",
            () -> new BlockItem(BlockRegistry.RawCheeseWheel.get(), basicItem())),
    CHEESE_WHEEL("cheese_wheel",
            () -> new BlockItem(BlockRegistry.CheeseWheel.get(), basicItem())),
    CHEESE_WHEEL_SLICE("cheese_wheel_slice",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.CHEESE_WHEEL_SLICE.getFoodProperties()))),
    PAPER_WRAPPED_FISH("paper_wrapped_fish",
            () -> new BlockItem(BlockRegistry.PaperWrappedFish.get(), basicItem())),
    BOBO_CHICKEN("bobo_chicken",
            () -> new BlockItem(BlockRegistry.BoboChicken.get(), basicItem())),
    SPICY_STRIPS("spicy_strips",
            ()-> new ConsumableItem(foodItem(FoodList.SPICY_STRIPS.getFoodProperties()),true)),
    GREEN_TONGUE("green_tongue",
            ()-> new ConsumableItem(foodItem(FoodList.GREEN_TONGUE.getFoodProperties()),true)),
    RAW_GLUTEN("raw_gluten",
            ()-> new Item(basicItem())),
    GLUTEN("gluten",
            ()-> new Item(basicItem())),
    GLUTEN_SKEWER("gluten_skewer",
            ()-> new Item(basicItem())),
    ROAST_GLUTEN("roast_gluten",
            ()-> new ConsumableItem(foodItem(FoodList.ROAST_GLUTEN.getFoodProperties()))),
    RAW_DONKEY_MEAT("raw_donkey_meat",
            ()-> new ConsumableItem(foodItem(FoodList.RAW_DONKEY_MEAT.getFoodProperties()))),
    COOKED_DONKEY_MEAT("cooked_donkey_meat",
            ()-> new ConsumableItem(foodItem(FoodList.COOKED_DONKEY_MEAT.getFoodProperties()))),
    DONKEY_BURGER("donkey_burger",
            ()-> new ConsumableItem(new Item.Properties().food(FoodList.DONKEY_BURGER.getFoodProperties()))),
    BOWL_OF_PAPER_WRAPPED_FISH("bowl_of_paper_wrapped_fish",
            ()-> new ConsumableItem(bowlFoodItem(FoodList.BOWL_OF_PAPER_WRAPPED_FISH.getFoodProperties()),true)),
    RAW_POTATO_BOBO_CHICKEN("raw_potato_bobo_chicken",
            ()-> new ConsumableItem(basicItem())),
    RAW_CABBAGE_BOBO_CHICKEN("raw_cabbage_bobo_chicken",
            ()-> new ConsumableItem(basicItem())),
    RAW_CHICKEN_BOBO_CHICKEN("raw_chicken_bobo_chicken",
            ()-> new ConsumableItem(basicItem())),
    POTATO_BOBO_CHICKEN("potato_bobo_chicken",
            ()-> new ConsumableItem(foodItem(FoodList.POTATO_BOBO_CHICKEN.getFoodProperties()), true)),
    CABBAGE_BOBO_CHICKEN("cabbage_bobo_chicken",
            ()-> new ConsumableItem(foodItem(FoodList.CABBAGE_BOBO_CHICKEN.getFoodProperties()), true)),
    CHICKEN_BOBO_CHICKEN("chicken_bobo_chicken",
            ()-> new ConsumableItem(foodItem(FoodList.CHICKEN_BOBO_CHICKEN.getFoodProperties()), true)),
    POTATO_SLICE("potato_slice",
            ()-> new Item(basicItem())),
    POTATO_CHIP("potato_chip",
            ()-> new ConsumableItem(foodItem(FoodList.POTATO_CHIP.getFoodProperties()))),
    RAW_SPRING_ROLL("raw_spring_roll",
            ()-> new Item(basicItem())),
    SPRING_ROLL("spring_roll",
            ()-> new ConsumableItem(foodItem(FoodList.SPRING_ROLL.getFoodProperties()))),
    SPRING_ROLL_MEDLEY("spring_roll_medley",
            ()->new BlockItem(BlockRegistry.SpringRollMedley.get(), basicItem())),
    FRIED_CHICKEN_CHIP("fried_chicken_chip",
            ()-> new ConsumableItem(foodItem(FoodList.FRIED_CHICKEN_CHIP.getFoodProperties()))),
    FRIED_FISH("fried_fish",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_FISH.getFoodProperties()))),
    TONKATSU("tonkatsu",
            () -> new ConsumableItem(foodItem(FoodList.TONKATSU.getFoodProperties()))),
    DEEP_FRYING_PAN("deep_frying_pan",
            () -> new BlockItem(BlockRegistry.DeepFryingPan.get(), basicItem())),
    RAW_FRIED_DUMPLING("raw_fried_dumpling",
            () -> new ConsumableItem(foodItem(FoodList.RAW_FRIED_DUMPLING.getFoodProperties()))),
    FRIED_DUMPLING("fried_dumpling",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_DUMPLING.getFoodProperties()))),
    PLATE_OF_FRIED_DUMPLING("plate_of_fried_dumpling",
            () -> new BlockItem(BlockRegistry.PlateOfFriedDumpling.get(), basicItem())),
    BOWL_OF_FRIED_DUMPLING("bowl_of_fried_dumpling",
            () -> new ConsumableItem(bowlFoodItem(FoodList.BOWL_OF_FRIED_DUMPLING.getFoodProperties()))),
    SWEET_RICE("sweet_rice",
            () -> new BlockItem(BlockRegistry.SweetRice.get(), basicItem())),
    BOWL_OF_SWEET_RICE("bowl_of_sweet_rice",
            () -> new ConsumableItem(bowlFoodItem(FoodList.BOWL_OF_SWEET_RICE.getFoodProperties()))),
    CAPSICUM("capsicum",
            () -> new ConsumableItem(foodItem(FoodList.CAPSICUM.getFoodProperties()))),
    CAPSICUM_SEEDS("capsicum_seeds",
            () -> new ItemNameBlockItem((Block) BlockRegistry.BuddingCapsicumsCrop.get(), new Item.Properties()){
                public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
                    super.registerBlocks(blockToItemMap, item);
                    blockToItemMap.put((Block) BlockRegistry.CapsicumsCrop.get(), item);
                }

                public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
                    super.removeFromBlockToItemMap(blockToItemMap, itemIn);
                    blockToItemMap.remove(BlockRegistry.CapsicumsCrop.get());
                }
    }),
    CAPSICUM_SAUCE("capsicum_sauce",
            () -> new ConsumableItem(bowlFoodItem(FoodList.CAPSICUM_SAUCE.getFoodProperties()))),
    FRIED_CAKE("fried_cake",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_CAKE.getFoodProperties()))),
    FRIED_MUSHROOM("fried_mushroom",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_MUSHROOM.getFoodProperties()))),
    FRIED_STRANGE_MUSHROOM("fired_strange_mushroom",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_STRANGE_MUSHROOM.getFoodProperties()),true)),
    BOWL_OF_GLUE_PUDDING("bowl_of_glue_pudding",
            () -> new ConsumableItem(foodItem(FoodList.BOWL_OF_GLUE_PUDDING.getFoodProperties()),true)),
    FRIED_GLUE_PUDDING("fried_glue_pudding",
            () -> new ConsumableItem(foodItem(FoodList.FRIED_GLUE_PUDDING.getFoodProperties()),true)),
    RAW_GLUE_PUDDING("raw_glue_pudding",
            () -> new ConsumableItem(foodItem(FoodList.RAW_GLUE_PUDDING.getFoodProperties()))),
    BOWL_OF_FIRED_GLUE_PUDDING("bowl_of_fired_glue_pudding",
            () -> new BlockItem(BlockRegistry.BowlOfFiredGluePudding.get(), basicItem()));

    private final String name;
    private final Supplier<Item> supplier;
    private final Boolean addtab;
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Casualness_Delight.MODID);
    public static final LinkedHashMap<String,Supplier<Item>> REGISTRY_ITEMS = Maps.newLinkedHashMap();

    ItemRegistry(String name, Supplier<Item> supplier) {
        this.name = name;
        this.supplier = supplier;
        this.addtab = true;
    }
    ItemRegistry(String name, Supplier<Item> supplier, Boolean addtab) {
        this.name = name;
        this.supplier = supplier;
        this.addtab = addtab;
    }

    public static void registerItems() {
        for (ItemRegistry item : values()) {
            Supplier<Item> registeredItem = ITEMS.register(item.name, item.supplier);
            if(item.addtab){
                REGISTRY_ITEMS.put(item.name, registeredItem);
            }
        }
    }

    // Helper methods
    public static Item.Properties basicItem() {
        return new Item.Properties();
    }

    public static Item.Properties knifeItem(Tier tier) {
        return new Item.Properties().attributes(KnifeItem.createAttributes(tier, 0.5F, -2.0F));
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return new Item.Properties().food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }

    public static Item.Properties drinkItem() {
        return new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }

    public Item getItem() {
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Casualness_Delight.MODID,name));
    }
    public Supplier<Item> getSupplier() {
        return REGISTRY_ITEMS.get(name);
    }

    static {
        registerItems();
    }

}
