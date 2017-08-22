package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.core.NorsecraftCreativeTab;
import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemRune extends ItemBlockMod {
    public static final String TAG_RUNE_TYPE = "runeType";

    public ItemRune(BlockRune b, BaseRunes.BaseRune infos) {
        super(b);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString(TAG_RUNE_TYPE, infos.getName());
        ItemStack tmp = new ItemStack(this);
        tmp.setTagCompound(nbt);
        setHasSubtypes(true);
        this.setCreativeTab(NorsecraftCreativeTab.INSTANCE);
    }
}
