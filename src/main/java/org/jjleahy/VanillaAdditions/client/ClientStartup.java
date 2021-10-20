package org.jjleahy.VanillaAdditions.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjleahy.VanillaAdditions.block.BlockRegistry;

public class ClientStartup {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        LOGGER.info("----- Client Setup -----");
        RenderType cutout = RenderType.cutout();

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.HUCKLE_BERRY_BUSH.get(), cutout);
    }

}
