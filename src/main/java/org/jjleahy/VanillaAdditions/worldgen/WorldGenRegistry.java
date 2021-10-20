package org.jjleahy.VanillaAdditions.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjleahy.VanillaAdditions.VanillaAdditions;
import org.jjleahy.VanillaAdditions.util.Reference;

import java.util.Set;

public class WorldGenRegistry {

    public static ConfiguredFeature<?, ?> OVERWORLD_CONFIGURED_FEATURES;

    //public static Configure

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

    private static void applyToLists()
    {
        //OVERWORLD_CONFIGURED_FEATURES = new ConfiguredFeature<?, ?>[] {};
    }

}
