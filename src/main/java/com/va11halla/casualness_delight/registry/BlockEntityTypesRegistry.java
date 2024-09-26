package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.enity.DeepFryingPanEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityTypesRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Casualness_Delight.MODID);
    public static final Supplier<BlockEntityType<DeepFryingPanEntity>> DeepFryingPan = TILES.register("deep_frying_pan",
            () -> BlockEntityType.Builder.of(DeepFryingPanEntity::new, BlockRegistry.DeepFryingPan.get()).build(null));
}