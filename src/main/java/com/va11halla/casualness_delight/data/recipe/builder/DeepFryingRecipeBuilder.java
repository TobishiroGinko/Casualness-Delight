package com.va11halla.casualness_delight.data.recipe.builder;

import com.va11halla.casualness_delight.recipe.DeepFryingRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeepFryingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final CookingBookCategory bookCategory;
    private final Item result;
    private final ItemStack stackResult; // Neo: add stack result support
    private final Ingredient ingredient;
    private final float experience;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @javax.annotation.Nullable
    private String group;
    private final AbstractCookingRecipe.Factory<?> factory;

    private DeepFryingRecipeBuilder(
            RecipeCategory p_251345_,
            CookingBookCategory p_251607_,
            ItemLike p_252112_,
            Ingredient p_250362_,
            float p_251204_,
            int p_250189_,
            AbstractCookingRecipe.Factory<?> p_311960_
    ) {
        this(p_251345_, p_251607_, new ItemStack(p_252112_), p_250362_, p_251204_, p_250189_, p_311960_);
    }

    private DeepFryingRecipeBuilder(
            RecipeCategory p_251345_,
            CookingBookCategory p_251607_,
            ItemStack result,
            Ingredient p_250362_,
            float p_251204_,
            int p_250189_,
            AbstractCookingRecipe.Factory<?> p_311960_
    ) {
        this.category = p_251345_;
        this.bookCategory = p_251607_;
        this.result = result.getItem();
        this.stackResult = result;
        this.ingredient = p_250362_;
        this.experience = p_251204_;
        this.cookingTime = p_250189_;
        this.factory = p_311960_;
    }

    public static <T extends AbstractCookingRecipe> DeepFryingRecipeBuilder generic(
            Ingredient p_250999_,
            RecipeCategory p_248815_,
            ItemLike p_249766_,
            float p_251320_,
            int p_248693_,
            RecipeSerializer<T> p_250921_,
            AbstractCookingRecipe.Factory<T> p_312657_
    ) {
        return new DeepFryingRecipeBuilder(p_248815_, determineRecipeCategory(p_250921_, p_249766_), p_249766_, p_250999_, p_251320_, p_248693_, p_312657_);
    }

    public static DeepFryingRecipeBuilder deepFrying(Ingredient p_249393_, RecipeCategory p_249372_, ItemLike p_251516_, float p_252321_, int p_251916_) {
        return new DeepFryingRecipeBuilder(p_249372_, CookingBookCategory.FOOD, p_251516_, p_249393_, p_252321_, p_251916_, DeepFryingRecipe::new);
    }


    public static DeepFryingRecipeBuilder deepFrying(Ingredient p_249393_, RecipeCategory p_249372_, ItemStack result, float p_252321_, int p_251916_) {
        return new DeepFryingRecipeBuilder(p_249372_, CookingBookCategory.FOOD, result, p_249393_, p_252321_, p_251916_, DeepFryingRecipe::new);
    }



    public DeepFryingRecipeBuilder unlockedBy(String p_176792_, Criterion<?> p_300970_) {
        this.criteria.put(p_176792_, p_300970_);
        return this;
    }

    public DeepFryingRecipeBuilder group(@Nullable String p_176795_) {
        this.group = p_176795_;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput p_301266_, ResourceLocation p_126264_) {
        this.ensureValid(p_126264_);
        Advancement.Builder advancement$builder = p_301266_.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_126264_))
                .rewards(AdvancementRewards.Builder.recipe(p_126264_))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        AbstractCookingRecipe abstractcookingrecipe = this.factory
                .create(
                        Objects.requireNonNullElse(this.group, ""), this.bookCategory, this.ingredient, this.stackResult, this.experience, this.cookingTime
                );
        p_301266_.accept(p_126264_, abstractcookingrecipe, advancement$builder.build(p_126264_.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }


    private static CookingBookCategory determineRecipeCategory(RecipeSerializer<? extends AbstractCookingRecipe> p_251261_, ItemLike p_249582_) {
            return CookingBookCategory.FOOD;
    }

    private void ensureValid(ResourceLocation p_126266_) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
        }
    }
}
