package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public abstract class ItemBlockMod extends ItemBlock {

    public ItemBlockMod(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }
}
