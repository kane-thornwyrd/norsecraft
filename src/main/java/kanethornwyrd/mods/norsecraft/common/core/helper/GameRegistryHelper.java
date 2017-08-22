package kanethornwyrd.mods.norsecraft.common.core.helper;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class GameRegistryHelper {
    public static void registerBlocks(Block block){
        GameRegistry.findRegistry(Block.class).register(block);
    }
    public static void registerItem(Item item){
        GameRegistry.findRegistry(Item.class).register(item);
    }

}
