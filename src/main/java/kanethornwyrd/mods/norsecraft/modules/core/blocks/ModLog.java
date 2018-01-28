package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModLog extends BlockRotatedPillar implements IModBlock {
private final String[] variants;
private final String bareName;

protected ModLog( String name, String... variants ) {
  super(Material.WOOD);
  if (variants.length == 0)
    variants = new String[]{ name };
  bareName = name;
  this.variants = variants;
  setHardness(2.0F);
  setSoundType(SoundType.WOOD);
  if (registerInConstruction())
    setUnlocalizedName(name);
}

public boolean registerInConstruction() {
  return true;
}

@Override
public Block setUnlocalizedName( String name ) {
  super.setUnlocalizedName(name);
  setRegistryName(getPrefix() + name);
  ProxyRegistry.register(this);
  ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(getPrefix() + name)));
  return this;
}

@Override
public ModLog setCreativeTab( CreativeTabs tab ) {
  super.setCreativeTab(tab);
  return this;
}

@Override
public boolean isFireSource( World world, BlockPos pos, EnumFacing side ) {
  return true;
}

@Override
public boolean canSustainLeaves( IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos ) {
  return true;
}

@Override
public boolean isWood( net.minecraft.world.IBlockAccess world, BlockPos pos ) {
  return true;
}

@Override
public String getBareName() {
  return bareName;
}

@Override
public IProperty getVariantProp() {
  return null;
}

@Override
public IProperty[] getIgnoredProperties() {
  return new IProperty[ 0 ];
}

@Override
public EnumRarity getBlockRarity( ItemStack stack ) {
  return EnumRarity.COMMON;
}

@Override
public String[] getVariants() {
  return variants;
}

@Override
@SideOnly(Side.CLIENT)
public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

@Override
public Class getVariantEnum() {
  return null;
}
}
