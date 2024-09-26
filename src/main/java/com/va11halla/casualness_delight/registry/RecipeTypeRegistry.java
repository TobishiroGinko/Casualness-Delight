package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeTypeRegistry {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Casualness_Delight.MODID);
    public static final Supplier<RecipeType<DeepFryingRecipe>> DEEPFRYING = RECIPE_TYPES.register("deep_frying", () -> registerRecipeType("deep_frying"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>()
        {
            public String toString() {
                return Casualness_Delight.MODID + ":" + identifier;
            }
        };
    }
}