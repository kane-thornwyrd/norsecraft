package kanethornwyrd.mods.norsecraft.common.core;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModItems;
import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public final class NorsecraftCreativeTab extends CreativeTabs {

    public static final NorsecraftCreativeTab INSTANCE = new NorsecraftCreativeTab();
    NonNullList<ItemStack> list;

    public NorsecraftCreativeTab() {
        super(LibMisc.MOD_ID);
    }

    @Nonnull
    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(ModItems.items.get("icon"));
    }

    @Override
    public ItemStack getTabIconItem() {
        return getIconItemStack();
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }

    @Override
    public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> list) {
        this.list = list;
    }
}
