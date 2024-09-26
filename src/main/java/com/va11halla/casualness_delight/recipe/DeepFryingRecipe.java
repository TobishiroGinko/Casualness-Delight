package com.va11halla.casualness_delight.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.va11halla.casualness_delight.registry.BlockRegistry;
import com.va11halla.casualness_delight.registry.RecipeSerializerRegistry;
import com.va11halla.casualness_delight.registry.RecipeTypeRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class DeepFryingRecipe extends AbstractCookingRecipe {

    public DeepFryingRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack output, float experience, int cookingTime){
        super(RecipeTypeRegistry.DEEPFRYING.get(),group,category,ingredient,output,experience,cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializerRegistry.DEEPFRYING.get();
    }
    public ItemStack getToastSymbol() {
        return new ItemStack(BlockRegistry.DeepFryingPan.get());
    }

    public ItemStack getResultItem() {
        return result.copy();
    }


    public static class Serializer implements RecipeSerializer<DeepFryingRecipe>{
        @Override
        public MapCodec<DeepFryingRecipe> codec() {
            return CODEC;
        }
        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DeepFryingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static final MapCodec<DeepFryingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(DeepFryingRecipe::getGroup),
                CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.MISC).forGetter(DeepFryingRecipe::category),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((i)-> i.ingredient),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter((r) -> r.result),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(DeepFryingRecipe::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(DeepFryingRecipe::getCookingTime)
        ).apply(inst, DeepFryingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, DeepFryingRecipe> STREAM_CODEC = StreamCodec.of(DeepFryingRecipe.Serializer::toNetwork, DeepFryingRecipe.Serializer::fromNetwork);
        public Serializer() {
        }

        private static DeepFryingRecipe fromNetwork(RegistryFriendlyByteBuf p_44351_) {
            String s = p_44351_.readUtf();
            CookingBookCategory cookingbookcategory = (CookingBookCategory)p_44351_.readEnum(CookingBookCategory.class);
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(p_44351_);
            ItemStack itemstack = (ItemStack)ItemStack.STREAM_CODEC.decode(p_44351_);
            float f = p_44351_.readFloat();
            int i = p_44351_.readVarInt();
            return new DeepFryingRecipe(s, cookingbookcategory, ingredient, itemstack, f, i);
        }

        public static void toNetwork(RegistryFriendlyByteBuf p_44335_, DeepFryingRecipe p_44336_) {
            p_44335_.writeUtf(p_44336_.group);
            p_44335_.writeEnum(p_44336_.category());
            Ingredient.CONTENTS_STREAM_CODEC.encode(p_44335_,p_44336_.ingredient);
            ItemStack.STREAM_CODEC.encode(p_44335_,p_44336_.result);
            p_44335_.writeFloat(p_44336_.experience);
            p_44335_.writeVarInt(p_44336_.cookingTime);
        }
    }
}