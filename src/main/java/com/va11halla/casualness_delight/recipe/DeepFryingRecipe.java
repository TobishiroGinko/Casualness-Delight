package com.va11halla.casualness_delight.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class DeepFryingRecipe extends AbstractCookingRecipe {
    public static final String ID  = "deep_frying";
    public static class Type implements RecipeType<DeepFryingRecipe>{
        private Type() {}
        public static final Type DEEP_FRYING = new Type();
        public static final String ID  = "deep_frying";
    }
    public ItemStack getOutput() {
        return this.output;
    }
    public static final RecipeType<DeepFryingRecipe> DEEP_FRYING_TYPE = Type.DEEP_FRYING;

    public DeepFryingRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(DeepFryingRecipe.DEEP_FRYING_TYPE, id, group, category, input, output, experience, cookTime);
    }
    @Override
    public RecipeType<?> getType() {
        return Type.DEEP_FRYING;
    }
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.CAMPFIRE);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public static class Serializer implements RecipeSerializer<DeepFryingRecipe> {
        private final int cookingTime;
        private final RecipeFactory<DeepFryingRecipe> recipeFactory;

        public Serializer(RecipeFactory<DeepFryingRecipe> recipeFactory, int cookingTime) {
            this.cookingTime = cookingTime;
            this.recipeFactory = recipeFactory;
        }

        public DeepFryingRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            CookingRecipeCategory cookingRecipeCategory = (CookingRecipeCategory)CookingRecipeCategory.CODEC.byId(JsonHelper.getString(jsonObject, "category", (String)null), CookingRecipeCategory.MISC);
            JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
            Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonElement, false);
            String string2 = JsonHelper.getString(jsonObject, "result");
            Identifier identifier2 = new Identifier(string2);
            ItemStack itemStack = new ItemStack((ItemConvertible)Registries.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
                return new IllegalStateException("Item: " + string2 + " does not exist");
            }));
            float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
            int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
            return this.recipeFactory.create(identifier, string, cookingRecipeCategory, ingredient, itemStack, f, i);
        }

        public DeepFryingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String string = packetByteBuf.readString();
            CookingRecipeCategory cookingRecipeCategory = (CookingRecipeCategory)packetByteBuf.readEnumConstant(CookingRecipeCategory.class);
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            float f = packetByteBuf.readFloat();
            int i = packetByteBuf.readVarInt();
            return this.recipeFactory.create(identifier, string, cookingRecipeCategory, ingredient, itemStack, f, i);
        }

        public void write(PacketByteBuf packetByteBuf, DeepFryingRecipe abstractCookingRecipe) {
            packetByteBuf.writeString(abstractCookingRecipe.group);
            packetByteBuf.writeEnumConstant(abstractCookingRecipe.getCategory());
            abstractCookingRecipe.input.write(packetByteBuf);
            packetByteBuf.writeItemStack(abstractCookingRecipe.output);
            packetByteBuf.writeFloat(abstractCookingRecipe.experience);
            packetByteBuf.writeVarInt(abstractCookingRecipe.cookTime);
        }

        public interface RecipeFactory<T extends AbstractCookingRecipe> {
            T create(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime);
        }
        public static final Serializer INSTANCE = new Serializer(DeepFryingRecipe::new,100);
    }
}
