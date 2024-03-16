package com.va11halla.casualness_delight.integration.emi;

import com.va11halla.casualness_delight.integration.emi.deepfrying.DeepFryingEMIRecipe;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import com.va11halla.casualness_delight.registry.BlocksRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class CasualnessDelightEMIPlugin implements EmiPlugin {
    public static final EmiStack DEEP_FRYING_PAN = EmiStack.of(BlocksRegistry.DeepFryingPan.get());
    public static final EmiRecipeCategory DeepFryingCategory
            = new EmiRecipeCategory(new Identifier("casualness_delight", "deep_frying"), DEEP_FRYING_PAN);

    @Override
    public void register(EmiRegistry registry) {
        // Tell EMI to add a tab for your category
        registry.addCategory(DeepFryingCategory);

        // Add all the workstations your category uses
        registry.addWorkstation(DeepFryingCategory, DEEP_FRYING_PAN);
        RecipeManager manager = registry.getRecipeManager();
        for (DeepFryingRecipe recipe : manager.listAllOfType(DeepFryingRecipe.Type.DEEP_FRYING)) {
            registry.addRecipe(new DeepFryingEMIRecipe(recipe));
        }

    }
}
