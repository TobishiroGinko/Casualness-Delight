package com.va11halla.casualness_delight.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.va11halla.casualness_delight.enity.DeepFryingPanEntity;
import com.va11halla.casualness_delight.registry.LootFunctionRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.registry.ModLootFunctions;

import java.util.List;

public class CopyDeepFryingPanFunction extends LootItemConditionalFunction{
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("casualness_delight", "copy_deep_frying_pan");
    public static final MapCodec<CopyDeepFryingPanFunction> CODEC = RecordCodecBuilder.mapCodec((p_298131_) -> {
        return commonFields(p_298131_).apply(p_298131_, CopyDeepFryingPanFunction::new);
    });

    private CopyDeepFryingPanFunction(List<LootItemCondition> conditions) {
        super(conditions);
    }

    public static LootItemConditionalFunction.Builder<?> builder() {
        return simpleBuilder(CopyDeepFryingPanFunction::new);
    }

    protected ItemStack run(ItemStack stack, LootContext context) {
        BlockEntity tile = (BlockEntity)context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
        if (tile instanceof DeepFryingPanEntity blockEntity) {
            stack = blockEntity.getDeepFryingPanStack();
        }

        return stack;
    }

    public LootItemFunctionType<CopySkilletFunction> getType() {
        return (LootItemFunctionType) LootFunctionRegistry.COPY_DEEP_FRYING_PAN.get();
    }
}
