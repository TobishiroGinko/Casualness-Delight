package com.va11halla.casualness_delight.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.va11halla.casualness_delight.registry.BlockRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class DeepFryingRecipe extends AbstractCookingRecipe {
    public DeepFryingRecipe(ResourceLocation id,String group,CookingBookCategory category,Ingredient ingredient,ItemStack output,float experience,int cookingTime){
        super(Type.DeepFrying,id,group,category,ingredient,output,experience,cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockRegistry.DeepFryingPan.get());
    }

    public ItemStack getResultItem() {
        return result.copy();
    }


    public static class Type implements RecipeType<DeepFryingRecipe>{
        private Type(){}
        public static final Type DeepFrying = new Type();
        public static final String ID = "deep_frying";
    }
    public static class Serializer implements RecipeSerializer<DeepFryingRecipe>{
        private final int defaultCookingTime;
        private final CookieBaker<DeepFryingRecipe> factory;

        public Serializer(CookieBaker<DeepFryingRecipe> p_44330_, int p_44331_) {
            this.defaultCookingTime = p_44331_;
            this.factory = p_44330_;
        }

        public DeepFryingRecipe fromJson(ResourceLocation p_44347_, JsonObject p_44348_) {
            String s = GsonHelper.getAsString(p_44348_, "group", "");
            CookingBookCategory cookingbookcategory = (CookingBookCategory)CookingBookCategory.CODEC.byName(GsonHelper.getAsString(p_44348_, "category", (String)null), CookingBookCategory.MISC);
            JsonElement jsonelement = GsonHelper.isArrayNode(p_44348_, "ingredient") ? GsonHelper.getAsJsonArray(p_44348_, "ingredient") : GsonHelper.getAsJsonObject(p_44348_, "ingredient");
            Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonelement, false);
            if (!p_44348_.has("result")) {
                throw new JsonSyntaxException("Missing result, expected to find a string or object");
            } else {
                ItemStack itemstack;
                if (p_44348_.get("result").isJsonObject()) {
                    itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44348_, "result"));
                } else {
                    String s1 = GsonHelper.getAsString(p_44348_, "result");
                    ResourceLocation resourcelocation = new ResourceLocation(s1);
                    itemstack = new ItemStack((ItemLike) BuiltInRegistries.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                        return new IllegalStateException("Item: " + s1 + " does not exist");
                    }));
                }

                float f = GsonHelper.getAsFloat(p_44348_, "experience", 0.0F);
                int i = GsonHelper.getAsInt(p_44348_, "cookingtime", this.defaultCookingTime);
                return this.factory.create(p_44347_, s, cookingbookcategory, ingredient, itemstack, f, i);
            }
        }

        public DeepFryingRecipe fromNetwork(ResourceLocation p_44350_, FriendlyByteBuf p_44351_) {
            String s = p_44351_.readUtf();
            CookingBookCategory cookingbookcategory = (CookingBookCategory)p_44351_.readEnum(CookingBookCategory.class);
            Ingredient ingredient = Ingredient.fromNetwork(p_44351_);
            ItemStack itemstack = p_44351_.readItem();
            float f = p_44351_.readFloat();
            int i = p_44351_.readVarInt();
            return this.factory.create(p_44350_, s, cookingbookcategory, ingredient, itemstack, f, i);
        }

        public void toNetwork(FriendlyByteBuf p_44335_, DeepFryingRecipe p_44336_) {
            p_44335_.writeUtf(p_44336_.group);
            p_44335_.writeEnum(p_44336_.category());
            p_44336_.ingredient.toNetwork(p_44335_);
            p_44335_.writeItem(p_44336_.result);
            p_44335_.writeFloat(p_44336_.experience);
            p_44335_.writeVarInt(p_44336_.cookingTime);
        }

        interface CookieBaker<T extends AbstractCookingRecipe> {
            T create(ResourceLocation var1, String var2, CookingBookCategory var3, Ingredient var4, ItemStack var5, float var6, int var7);
        }
        public static final Serializer INSTANCE = new Serializer(DeepFryingRecipe::new,100);
    }


       /* @Override
        public DeepFryingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject,"result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject,"ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1,Ingredient.EMPTY);
            for(int i =0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }
            return new DeepFryingRecipe(resourceLocation,output,inputs);
        }


        @Override
        public @Nullable DeepFryingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(),Ingredient.EMPTY);
            for(int i = 0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromNetwork(buf));
            }
            ItemStack output = buf.readItem();
            return new DeepFryingRecipe(resourceLocation,output,inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, DeepFryingRecipe deepFryingRecipe) {
            buf.writeInt(deepFryingRecipe.getIngredients().size());
            for(Ingredient ing:deepFryingRecipe.getIngredients()){
                ing.toNetwork(buf);
            }
            buf.writeItemStack(deepFryingRecipe.output.copy(),false);
        }
    }*/
}