package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.CasualnessDelightFabric;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemTab {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(CasualnessDelightFabric.MODID, "main"),
            () -> new ItemStack(ItemRegistry.FishAndChips.get()));
}
