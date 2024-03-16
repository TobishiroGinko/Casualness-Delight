package com.va11halla.casualness_delight.integration.jei;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;
@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static RecipeType<DeepFryingRecipe> INFUSION_TYPE =
            new RecipeType<>(DeepFryingCategory.UID,DeepFryingRecipe.class);
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Casualness_Delight.MODID,"jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration){
        registration.addRecipeCategories(new
                DeepFryingCategory(registration.getJeiHelpers().getGuiHelper()));
    }
    public void registerRecipes(IRecipeRegistration registration){
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<DeepFryingRecipe> recipesInfusing = recipeManager.getAllRecipesFor(DeepFryingRecipe.Type.DeepFrying);
        registration.addRecipes(INFUSION_TYPE,recipesInfusing);
    }
}
