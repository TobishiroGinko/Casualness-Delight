package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.CasualnessDelightFabric;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public enum RecipeRegistry {
    DEEP_FRYING("deep_frying", DeepFryingRecipe.Type.DEEP_FRYING, DeepFryingRecipe.DEEP_FRYING_SERIALIZER);
    private final String pathName;
    private final RecipeType recipeType;
    private final RecipeSerializer recipeSerializer;

    RecipeRegistry(String pathName, RecipeType recipeType, RecipeSerializer recipeSerializer) {
        this.pathName = pathName;
        this.recipeType = recipeType;
        this.recipeSerializer = recipeSerializer;

    }

    public static void register() {
        RecipeRegistry[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            RecipeRegistry value = var0[var2];
            //Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(CasualnessDelightFabric.MODID, value.pathName),
            //        value.recipeSerializer);

            Registry.register(Registries.RECIPE_TYPE, new Identifier(CasualnessDelightFabric.MODID, value.pathName),
                    value.recipeType);
        }
    }
}