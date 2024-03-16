package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Casualness_Delight.MODID);
    public static final RegistryObject<RecipeSerializer<DeepFryingRecipe>> DEEP_FRYING_SERIALIZER =
            SERIALIZER.register("deep_frying",()->DeepFryingRecipe.Serializer.INSTANCE);
}
