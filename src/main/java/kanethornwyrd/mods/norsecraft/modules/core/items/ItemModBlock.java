package kanethornwyrd.mods.norsecraft.modules.core.items;

import kanethornwyrd.mods.norsecraft.interf.IVariantHolder;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.IModBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;


public class ItemModBlock extends ItemBlock implements IVariantHolder {
private IModBlock modBlock;

public ItemModBlock( Block block, ResourceLocation res ) {
  super(block);
  this.modBlock = (IModBlock) block;
  
  ItemMod.variantHolders.add(this);
  if (this.getVariants().length > 1) {
    this.setHasSubtypes(true);
  }
  
  this.setRegistryName(res);
}

public String[] getVariants() {
  return this.modBlock.getVariants();
}

public ItemMeshDefinition getCustomMeshDefinition() {
  return this.modBlock.getCustomMeshDefinition();
}

public String getModNamespace() {
  return this.modBlock.getModNamespace();
}

public int getMetadata( int damage ) {
  return damage;
}

public ItemBlock setUnlocalizedName( String par1Str ) {
  return (ItemBlock) super.setUnlocalizedName(par1Str);
}

public EnumRarity getRarity( ItemStack stack ) {
  return this.modBlock.getBlockRarity(stack);
}

public String getUnlocalizedName( ItemStack par1ItemStack ) {
  int dmg = par1ItemStack.getItemDamage();
  String[] variants = this.getVariants();
  String name;
  if (dmg >= variants.length) {
    name = this.modBlock.getBareName();
  } else {
    name = variants[ dmg ];
  }
  
  return "tile." + this.getPrefix() + name;
}

public void getSubItems( CreativeTabs tab, NonNullList<ItemStack> subItems ) {
  String[] variants = this.getVariants();
  if (this.isInCreativeTab(tab)) {
    for (int i = 0; i < variants.length; ++i) {
      if (this.modBlock.shouldDisplayVariant(i)) {
        subItems.add(new ItemStack(this, 1, i));
      }
    }
  }
  
}

public boolean isInCreativeTab( CreativeTabs tab ) {
  return Arrays.asList(this.getCreativeTabs()).contains(tab);
}
}