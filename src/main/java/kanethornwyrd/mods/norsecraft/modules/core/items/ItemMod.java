package kanethornwyrd.mods.norsecraft.modules.core.items;

import kanethornwyrd.mods.norsecraft.interf.IVariantHolder;
import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;
import kanethornwyrd.mods.norsecraft.lib.TooltipHandler;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ItemMod extends Item implements IVariantHolder {
public static final List<IVariantHolder> variantHolders = new ArrayList();
private final String[] variants;
private final String bareName;

public ItemMod( String name, String... variants ) {
  this.setUnlocalizedName(name);
  if (variants.length > 1) {
    this.setHasSubtypes(true);
  }
  
  if (variants.length == 0) {
    variants = new String[]{ name };
  }
  
  this.bareName = name;
  this.variants = variants;
  variantHolders.add(this);
}

public Item setUnlocalizedName( String name ) {
  super.setUnlocalizedName(name);
  this.setRegistryName(new ResourceLocation(this.getPrefix() + name));
  ProxyRegistry.register(this);
  return this;
}

public String getUnlocalizedName( ItemStack par1ItemStack ) {
  int dmg = par1ItemStack.getItemDamage();
  String[] variants = this.getVariants();
  String name;
  if (dmg >= variants.length) {
    name = this.bareName;
  } else {
    name = variants[ dmg ];
  }
  
  return "item." + this.getPrefix() + name;
}

public void getSubItems( CreativeTabs tab, NonNullList<ItemStack> subItems ) {
  if (this.isInCreativeTab(tab)) {
    for (int i = 0; i < this.getVariants().length; ++i) {
      subItems.add(new ItemStack(this, 1, i));
    }
  }
  
}

public boolean isInCreativeTab( CreativeTabs tab ) {
  return Arrays.asList(this.getCreativeTabs()).contains(tab);
}

public String[] getVariants() {
  return this.variants;
}

public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

@SideOnly(Side.CLIENT)
public static void tooltipIfShift( List<String> tooltip, Runnable r ) {
  TooltipHandler.tooltipIfShift(tooltip, r);
}

@SideOnly(Side.CLIENT)
public static void addToTooltip( List<String> tooltip, String s, Object... format ) {
  TooltipHandler.addToTooltip(tooltip, s, format);
}

@SideOnly(Side.CLIENT)
public static String local( String s ) {
  return TooltipHandler.local(s);
}
}
