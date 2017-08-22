package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.client.lib.LibResources;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public abstract class ItemBlockMod extends ItemBlock {

    public ItemBlockMod(Block block) {
        super(block);
    }

    public CreativeTabs getCreativeTab() {
        return this.block.getCreativeTabToDisplayOn();
    }

    @Nonnull
    @Override
    public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack) {
        return getUnlocalizedNameInefficiently_(par1ItemStack).replaceAll("tile.", "tile." + LibResources.PREFIX_MOD);
    }

    public String getUnlocalizedNameInefficiently_(ItemStack stack) {
        return super.getUnlocalizedNameInefficiently(stack);
    }

    @Override
    public void getSubItems(CreativeTabs ct, NonNullList<ItemStack> is) {}
}
