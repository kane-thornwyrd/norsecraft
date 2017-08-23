package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import kanethornwyrd.mods.norsecraft.common.lib.LibItemNames;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public final class ModItems {
    public static ModItems INSTANCE;
    public static HashMap<String, ItemMod> items = new HashMap<>();

    private ModItems() {
        BaseRunes.init();
        items.put(LibItemNames.ICON, new ItemIcon());
    }

    public static ModItems init() {
        if (null == INSTANCE) {
            INSTANCE = new ModItems();
        }
        return INSTANCE;
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        items.forEach((name, item) -> injectItem(name, item, registry));
    }

    private static final void injectItem(String name, Item item, IForgeRegistry<Item> registry){
        if(item != null) {
            registry.register(item);
        }
    }

    public static final void registerModels() {
        items.forEach(ModItems::injectItemModels);
    }

    private static void injectItemModels(String s, ItemMod itemMod) {
        itemMod.registerItemModel();
    }
}
