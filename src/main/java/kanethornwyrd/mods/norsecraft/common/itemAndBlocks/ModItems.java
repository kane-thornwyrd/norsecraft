package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.core.NorsecraftCreativeTab;
import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public final class ModItems {
    public static HashMap<String, Item> items = new HashMap<>();

    public static void init() {
        items.put("icon", new ItemIcon());
    }

    public static void register(IForgeRegistry<Item> registry) {
        items.forEach((name, item) -> injectItem(name, item, registry));
    }

    private static final void injectItem(String name, Item item, IForgeRegistry<Item> registry){
        if(item != null) {
            item.setRegistryName(new ResourceLocation(LibMisc.MOD_ID, name));
            item.setCreativeTab(NorsecraftCreativeTab.INSTANCE);
            registry.register(item);
        }
    }
}
