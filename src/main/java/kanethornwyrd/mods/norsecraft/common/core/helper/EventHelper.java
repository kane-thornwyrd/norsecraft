package kanethornwyrd.mods.norsecraft.common.core.helper;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.BlockMod;
import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ItemMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public final class EventHelper {

    public static void insertBlockIntoRegistry(RegistryEvent.Register<Block> event, BlockMod block){
        event.getRegistry().register(block);
    }

    public static void insertItemIntoRegistry(RegistryEvent.Register<Item> event, ItemMod item){
        event.getRegistry().register(item);
    }
}
