package com.va11halla.casualness_delight.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;


public class DeepFryingRecipeProvider extends RecipeProvider {

    public DeepFryingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    protected void buildRecipes(RecipeOutput output) {
        DeepFryingRecipes.register(output);
    }
}
