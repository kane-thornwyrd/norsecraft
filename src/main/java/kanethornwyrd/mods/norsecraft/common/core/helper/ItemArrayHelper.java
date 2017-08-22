package kanethornwyrd.mods.norsecraft.common.core.helper;

import net.minecraft.item.Item;

public final class ItemArrayHelper {
    public static Item[] push(Item[] array, Item push) {
        Item[] longer = new Item[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = push;
        return longer;
    }
}
