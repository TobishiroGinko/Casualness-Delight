package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.Casualness_Delight;
import com.va11halla.casualness_delight.enity.DeepFryingPanEnity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypesRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Casualness_Delight.MODID);
    public static final RegistryObject<BlockEntityType<DeepFryingPanEnity>> DeepFryingPan = TILES.register("deep_frying_pan",
            () -> BlockEntityType.Builder.of(DeepFryingPanEnity::new, BlockRegistry.DeepFryingPan.get()).build(null));
}
