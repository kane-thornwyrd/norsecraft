package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;


import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockMod extends Block implements IModBlock {
private final String[] variants;
private final String bareName;

public BlockMod( String name, Material materialIn, String... variants ) {
  super(materialIn);
  if (variants.length == 0) {
    variants = new String[]{ name };
  }
  
  
  this.bareName = name;
  this.variants = variants;
  if (registerInConstruction()) {
    setUnlocalizedName(name);
  }
  
}

public boolean registerInConstruction() {
  return true;
}

public Block setUnlocalizedName( String name ) {
  super.setUnlocalizedName(name);
  this.setRegistryName(this.getPrefix() + name);
  ProxyRegistry.register(this);
  ProxyRegistry.register(this.createItemBlock(new ResourceLocation(this.getPrefix() + name)));
  return this;
}

public ItemBlock createItemBlock( ResourceLocation res ) {
  return new ItemModBlock(this, res);
}

public String getBareName() {
  return this.bareName;
}

public IProperty getVariantProp() {
  return null;
}

public IProperty[] getIgnoredProperties() {
  return new IProperty[ 0 ];
}

public EnumRarity getBlockRarity( ItemStack stack ) {
  return EnumRarity.COMMON;
}

public String[] getVariants() {
  return this.variants;
}

@SideOnly(Side.CLIENT)
public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

public Class getVariantEnum() {
  return null;
  
}

@Override
public BlockMod setCreativeTab( CreativeTabs tab ) {
  super.setCreativeTab(tab);
  return this;
}
}
