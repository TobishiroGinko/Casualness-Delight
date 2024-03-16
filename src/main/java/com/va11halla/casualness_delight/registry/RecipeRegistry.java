package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.CasualnessDelightFabric;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public enum RecipeRegistry {
    DEEP_FRYING_SERIALIZER("deep_frying", DeepFryingRecipe.class, DeepFryingRecipe.Serializer.INSTANCE);

    private final String pathName;
    private final Class<? extends Recipe<? extends Inventory>> recipeClass;
    private final RecipeSerializer<? extends Recipe<? extends Inventory>> serializer;
    private RecipeType<? extends Recipe<? extends Inventory>> type;

    RecipeRegistry(String pathName, Class<? extends Recipe<? extends Inventory>> recipeClass,
                   RecipeSerializer<? extends Recipe<? extends Inventory>> serializer) {
        this.pathName = pathName;
        this.recipeClass = recipeClass;
        this.serializer = serializer;
    }

    public static void register() {
        for (RecipeRegistry value : values()) {
            Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(CasualnessDelightFabric.MODID, value.pathName), value.serializer());
            value.type();
        }
    }

    public RecipeSerializer<? extends Recipe<? extends Inventory>> serializer() {
        return serializer;
    }

    @SuppressWarnings("unchecked")
    public <T extends Recipe<? extends Inventory>> RecipeType<T> type() {
        return (RecipeType<T>) type(this.recipeClass);
    }

    @SuppressWarnings({"unchecked","unused"})
    private <T extends Recipe<? extends Inventory>> RecipeType<T> type(Class<T> clazz) {
        if (type == null) {
            type = RecipeType.register(new Identifier(CasualnessDelightFabric.MODID, pathName).toString());
        }
        return (RecipeType<T>) type;
    }
}