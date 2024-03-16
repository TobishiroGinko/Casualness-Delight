package com.va11halla.casualness_delight.integration.emi.deepfrying;

import com.va11halla.casualness_delight.integration.emi.CasualnessDelightEMIPlugin;
import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;

import java.util.List;

public class DeepFryingEMIRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public DeepFryingEMIRecipe(DeepFryingRecipe recipe) {
        this.id = recipe.getId();
        this.input = List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
        this.output = List.of(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return CasualnessDelightEMIPlugin.DeepFryingCategory;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 117;
    }

    @Override
    public int getDisplayHeight() {
        return 57;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        // Add an arrow texture to indicate processing
        widgets.addTexture(new EmiTexture(new Identifier("casualness_delight", "textures/gui/emi/deep_frying.png"),0,0,117,57), 0, 0);
        // Adds an input slot on the left
        widgets.addSlot(input.get(0), 16, 8);

        // Adds an output slot on the right
        // Note that output slots need to call `recipeContext` to inform EMI about their recipe context
        // This includes being able to resolve recipe trees, favorite stacks with recipe context, and more
        widgets.addSlot(output.get(0), 79, 21).recipeContext(this);
    }
}
