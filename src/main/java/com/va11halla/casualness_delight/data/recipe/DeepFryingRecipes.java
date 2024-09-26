package com.va11halla.casualness_delight.data.recipe;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.data.recipe.builder.DeepFryingRecipeBuilder;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class DeepFryingRecipes {
    public static void register(RecipeOutput output) {
        foodFryingRecipes("tonkatsu", Items.PORKCHOP, ItemRegistry.TONKATSU.getItem(),1.0f,output);
    }
    private static void foodFryingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, RecipeOutput output) {
        DeepFryingRecipeBuilder.deepFrying(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                .save(output);
    }
}
