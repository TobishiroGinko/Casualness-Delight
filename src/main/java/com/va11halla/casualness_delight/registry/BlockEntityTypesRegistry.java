package com.va11halla.casualness_delight.registry;

import com.mojang.datafixers.types.Type;
import com.va11halla.casualness_delight.CasualnessDelightFabric;
import com.va11halla.casualness_delight.enity.DeepFryingPanEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Supplier;

public enum BlockEntityTypesRegistry {
    DEEPFRYINGPAN("deep_frying_pan", DeepFryingPanEntity.class, DeepFryingPanEntity::new, new BlocksRegistry[]{BlocksRegistry.DeepFryingPan});
    private final String pathName;
    private final Class<? extends BlockEntity> blockEntityClass;
    private final Supplier<BlockEntityType<? extends BlockEntity>> blockEntityTypeSupplier;
    private BlockEntityType<? extends BlockEntity> blockEntityType;
    private BlockEntityTypesRegistry(String pathName, Class blockEntityClass, FabricBlockEntityTypeBuilder.Factory blockEntitySupplier, BlocksRegistry... blockRegistryArray) {
        this.pathName = pathName;
        this.blockEntityClass = blockEntityClass;
        this.blockEntityTypeSupplier = () -> {
            return FabricBlockEntityTypeBuilder.create(blockEntitySupplier, (Block[]) Arrays.stream(blockRegistryArray).map(BlocksRegistry::get).toArray((x$0) -> {
                return new Block[x$0];
            })).build((Type)null);
        };
    }
    public static void register() {
        BlockEntityTypesRegistry[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            BlockEntityTypesRegistry value = var0[var2];
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(CasualnessDelightFabric.MODID, value.pathName), value.get());
        }

    }

    public <T extends BlockEntity> BlockEntityType<T> get() {
        return (BlockEntityType<T>) this.get(this.blockEntityClass);
    }

    private <T extends BlockEntity> BlockEntityType<T> get(Class<T> clazz) {
        if (this.blockEntityType == null) {
            this.blockEntityType = (BlockEntityType)this.blockEntityTypeSupplier.get();
        }

        return (BlockEntityType<T>) this.blockEntityType;
    }

    public String getId() {
        return Registry.BLOCK_ENTITY_TYPE.getId(this.get()).toString();
    }
}
