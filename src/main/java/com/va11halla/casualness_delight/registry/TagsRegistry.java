package com.va11halla.casualness_delight.registry;

import com.va11halla.casualness_delight.CasualnessDelightFabric;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class TagsRegistry {
    public static final TagKey<Block> FERMENT_ACTIVATORS;


    private static <E> TagKey<E> create(String pathName, RegistryKey<Registry<E>> registry) {
        return TagKey.of(registry, new Identifier("casualness_delight", pathName));
    }

    private TagsRegistry() throws InstantiationException {
        throw new InstantiationException("Constant class cannot be instantiate");
    }

    static {
        FERMENT_ACTIVATORS = create("ferment_activators", Registry.BLOCK_KEY);
    }
    public static void register(){
        CasualnessDelightFabric.LOGGER.info("Registering Mod Tags for " + CasualnessDelightFabric.MODID);
    }
}