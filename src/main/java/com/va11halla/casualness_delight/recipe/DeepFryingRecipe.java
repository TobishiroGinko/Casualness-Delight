package com.va11halla.casualness_delight.recipe;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;


public class DeepFryingRecipe extends AbstractCookingRecipe {
    public static final String ID  = "deep_frying";
    public static class Type implements RecipeType<DeepFryingRecipe>{
        private Type() {}
        public static final Type DEEP_FRYING = new Type();
        public static final String ID  = "deep_frying";
    }
    public static final RecipeType<DeepFryingRecipe> DEEP_FRYING_TYPE = Type.DEEP_FRYING;
    public static final RecipeSerializer<DeepFryingRecipe> DEEP_FRYING_SERIALIZER = RecipeSerializer.register("deep_frying", new CookingRecipeSerializer<DeepFryingRecipe>(DeepFryingRecipe::new, 100));

    public DeepFryingRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(DeepFryingRecipe.DEEP_FRYING_TYPE, id, group, category, input, output, experience, cookTime);
    }
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.CAMPFIRE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DEEP_FRYING_SERIALIZER;
    }
}
