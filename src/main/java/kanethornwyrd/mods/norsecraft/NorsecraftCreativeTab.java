package kanethornwyrd.mods.norsecraft;

import kanethornwyrd.mods.norsecraft.modules.core.IconFeature;


import net.minecraft.creativetab.CreativeTabs;


import net.minecraft.item.ItemStack;


import net.minecraft.util.NonNullList;


import javax.annotation.Nonnull;

public final class NorsecraftCreativeTab extends CreativeTabs {

public static final NorsecraftCreativeTab INSTANCE = new NorsecraftCreativeTab();
NonNullList<ItemStack> list;

public NorsecraftCreativeTab() {
  super(Misc.MOD_ID);
}

@Nonnull
@Override
public ItemStack getIconItemStack() {
  return new ItemStack(IconFeature.icon);
}

@Override
public ItemStack getTabIconItem() {
  return getIconItemStack();
}

@Override
public boolean hasSearchBar() {
  return false;
}

}
