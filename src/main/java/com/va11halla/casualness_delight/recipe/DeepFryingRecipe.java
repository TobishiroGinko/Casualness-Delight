package com.va11halla.casualness_delight.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.va11halla.casualness_delight.registry.BlocksRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;


public class DeepFryingRecipe extends AbstractCookingRecipe {
    public static final String ID = "deep_frying";

    public static class Type implements RecipeType<DeepFryingRecipe> {
        private Type() {
        }

        public static final Type DEEP_FRYING = new Type();
        public static final String ID = "deep_frying";
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public static final RecipeType<DeepFryingRecipe> DEEP_FRYING_TYPE = Type.DEEP_FRYING;

    public DeepFryingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(DeepFryingRecipe.DEEP_FRYING_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlocksRegistry.DeepFryingPan.get());
    }
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
    public RecipeType<?> getType() {
        return this.type;
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
            JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
            Ingredient ingredient = Ingredient.fromJson((JsonElement) jsonElement);
            String string2 = JsonHelper.getString(jsonObject, "result");
            Identifier identifier2 = new Identifier(string2);
            ItemStack itemStack = new ItemStack((ItemConvertible) Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
                return new IllegalStateException("Item: " + string2 + " does not exist");
            }));
            float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
            int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
            return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i);
        }

        public DeepFryingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String string = packetByteBuf.readString();
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            float f = packetByteBuf.readFloat();
            int i = packetByteBuf.readVarInt();
            return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i);
        }

        public void write(PacketByteBuf packetByteBuf, DeepFryingRecipe abstractCookingRecipe) {
            packetByteBuf.writeString(abstractCookingRecipe.group);
            abstractCookingRecipe.input.write(packetByteBuf);
            packetByteBuf.writeItemStack(abstractCookingRecipe.output);
            packetByteBuf.writeFloat(abstractCookingRecipe.experience);
            packetByteBuf.writeVarInt(abstractCookingRecipe.cookTime);
        }

        public interface RecipeFactory<DeepFryingRecipe> {
            DeepFryingRecipe create(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime);
        }
        public static final Serializer INSTANCE = new Serializer(DeepFryingRecipe::new,100);
    }
}
