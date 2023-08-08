package com.va11halla.casualness_delight;

import com.mojang.logging.LogUtils;
import com.va11halla.casualness_delight.registry.BlockRegistry;
import com.va11halla.casualness_delight.registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

@Mod(Casualness_Delight.MODID)
public class Casualness_Delight {
    public static final String MODID = "casualness_delight";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Casualness_Delight() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(eventBus);
        BlockRegistry.BLOCKS.register(eventBus);
        eventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static final CreativeModeTab CasualnessDelightTab = new CreativeModeTab(Casualness_Delight.MODID)
    {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.StargazyPie.get());
        }
    };
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
