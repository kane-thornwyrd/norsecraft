package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public final class ModBlocks {

    public static HashMap<String, BlockMod> blocks = new HashMap<>();

    public static void init() {
    }

    public static void register(IForgeRegistry<Block> registry) {
        blocks.forEach((name, block) -> ModBlocks.injectBlocks(name, block, registry));
    }

    private static final void injectBlocks(String name, BlockMod block, IForgeRegistry<Block> registry){
        if(block.shouldRegister()) {
            block.setRegistryName(new ResourceLocation(LibMisc.MOD_ID, name));
            registry.register(block);
        }
    }
}
