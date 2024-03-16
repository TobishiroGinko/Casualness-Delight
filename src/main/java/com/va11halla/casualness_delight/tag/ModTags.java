package com.va11halla.casualness_delight.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> FERMENT_ACTIVATORS = modBlockTag("ferment_activators");
    public ModTags() {
    }

    private static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(new ResourceLocation("casualness_delight", path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("casualness_delight", path));
    }

    private static TagKey<EntityType<?>> modEntityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("casualness_delight", path));
    }
}
