package org.jjleahy.VanillaAdditions.worldgen;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import org.jjleahy.VanillaAdditions.registry.BlockRegistry;
import org.jjleahy.VanillaAdditions.block.HuckleBerryBushBlock;
import org.jjleahy.VanillaAdditions.util.Reference;


public class ModFeatures {
    public static final class States {
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS.defaultBlockState();
        protected static final BlockState HUCKLE_BERRY_BUSH = BlockRegistry.HUCKLE_BERRY_BUSH.get().defaultBlockState().setValue(HuckleBerryBushBlock.AGE, Integer.valueOf(3));
    }

    public static final class Configs {
        public static final RandomPatchConfiguration HUCKLE_BERRY_PATCH_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(States.HUCKLE_BERRY_BUSH), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
    }

    public static ConfiguredFeature<?, ?> PATCH_HUCKLE_BERRY_BUSH;
    public static ConfiguredFeature<?, ?> PATCH_HUCKLE_BERRY_BUSH_SPARSE;
    public static ConfiguredFeature<?, ?> PATCH_HUCKLE_BERRY_BUSH_DECORATED;

    public static void configuredInit() {
        PATCH_HUCKLE_BERRY_BUSH = register(Reference.getReferenceName(Reference.HUCKLE_BERRY_BUSH_FEATURE), Feature.RANDOM_PATCH.configured(Configs.HUCKLE_BERRY_PATCH_CONFIG));
        PATCH_HUCKLE_BERRY_BUSH_SPARSE = register(Reference.getReferenceName(Reference.HUCKLE_BERRY_BUSH_FEATURE_SPARSE), PATCH_HUCKLE_BERRY_BUSH.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));
        PATCH_HUCKLE_BERRY_BUSH_DECORATED = register(Reference.getReferenceName(Reference.HUCKLE_BERRY_BUSH_FEATURE_DECORATED), PATCH_HUCKLE_BERRY_BUSH.decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).rarity(12));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
