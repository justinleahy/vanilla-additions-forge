package org.jjleahy.VanillaAdditions;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Priority;
import org.jjleahy.VanillaAdditions.block.BlockRegistry;
import org.jjleahy.VanillaAdditions.client.ClientStartup;
import org.jjleahy.VanillaAdditions.item.ItemRegistry;
import org.jjleahy.VanillaAdditions.util.Reference;
import org.jjleahy.VanillaAdditions.worldgen.ModFeatures;

import java.util.Set;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class VanillaAdditions {
    public static final Logger LOGGER = LogManager.getLogger();

    public VanillaAdditions()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events
        MinecraftForge.EVENT_BUS.register(this);

        // Client setup, currently only used for RenderLayer setting
        FMLJavaModLoadingContext.get().getModEventBus().register(ClientStartup.class);

        // World Generation and feature registration
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onBiomeLoading);

        BlockRegistry.init();
        ItemRegistry.init();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // Preinitialization Event
        LOGGER.info("----- Preinitialization -----");
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @SubscribeEvent
    public void onBiomeLoading(final BiomeLoadingEvent event) {
        VanillaAdditions.LOGGER.info("----- Biome loading event -----");
        Set<ResourceKey<Biome>> mountain_biomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN);
        VanillaAdditions.LOGGER.info(mountain_biomes);

        // Only mountain biomes registered to the ForgeRegistry will be counted
        for(Biome biome : ForgeRegistries.BIOMES) {
            for(ResourceKey key : mountain_biomes)
            {
                if(key.location().equals(biome.getRegistryName())) {
                    VanillaAdditions.LOGGER.info("biome found " + biome);
                }
            }
        }
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        LOGGER.info("----- Server Starting -----");
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegisterEvent) {
            LOGGER.info("----- Block Registry -----");
        }
        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegisterEvent) {
            LOGGER.info("----- Item Registry -----");
        }
    }
}
