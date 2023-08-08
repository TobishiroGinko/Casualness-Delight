package com.va11halla.casualness_delight.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> FERMENT_ACTIVATORS = modBlockTag("ferment_activators");
    public ModTags() {
    }

    private static TagKey<Item> modItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("farmersdelight", path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("farmersdelight", path));
    }

    private static TagKey<EntityType<?>> modEntityTag(String path) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("farmersdelight", path));
    }
}
