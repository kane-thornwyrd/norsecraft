package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public final class ModBlocks {
    public static ModBlocks INSTANCE;
    public static HashMap<String, BlockMod> blocks = new HashMap<>();

    private ModBlocks() {
        addBlock(new BlockRune().setCreativeTab(Norsecraft.creativeTab));
    }

    public static ModBlocks init() {
        if (null == INSTANCE) INSTANCE = new ModBlocks();
        return INSTANCE;
    }

    public static void addBlock(BlockMod block) {
        blocks.put(block.getUnlocalizedName().toUpperCase(), block);
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        blocks.forEach((name, block) -> {
            if (null != block) registry.register(block);
            if (block.hasTileEntity()) {
                GameRegistry.registerTileEntity(block.getTileEntityClass(), block.getRegistryName().toString());
            }
        });
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        blocks.forEach((name, block) -> {
            if (null != block) registry.register(block.createItemBlock());
        });
    }

    public static void registerItemModels() {
        blocks.forEach((name, block) -> {
            if (null != block) block.registerItemModel(Item.getItemFromBlock(block));
        });
    }
}
