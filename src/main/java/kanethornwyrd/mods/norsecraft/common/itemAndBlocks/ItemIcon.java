package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.core.NorsecraftCreativeTab;
import kanethornwyrd.mods.norsecraft.common.lib.LibItemNames;

public class ItemIcon extends ItemMod  {
    public ItemIcon() {
        super(LibItemNames.ICON);
        setMaxStackSize(1);
        setCreativeTab(NorsecraftCreativeTab.INSTANCE);
    }
}
