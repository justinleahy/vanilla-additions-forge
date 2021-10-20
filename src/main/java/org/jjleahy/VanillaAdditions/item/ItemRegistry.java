package org.jjleahy.VanillaAdditions.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jjleahy.VanillaAdditions.block.BlockRegistry;
import org.jjleahy.VanillaAdditions.util.Reference;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ItemNameBlockItem> HUCKLE_BERRY = ITEMS.register(Reference.HUCKLE_BERRY_NAME, () ->
            new ItemNameBlockItem(
                    BlockRegistry.HUCKLE_BERRY_BUSH.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.HUCKLE_BERRY)
            ));

}
