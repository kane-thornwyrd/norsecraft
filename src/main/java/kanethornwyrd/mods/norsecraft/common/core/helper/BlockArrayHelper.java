package kanethornwyrd.mods.norsecraft.common.core.helper;


import net.minecraft.block.Block;

public final class BlockArrayHelper {
    public static Block[] push(Block[] array, Block push) {
        Block[] longer = new Block[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }
}

