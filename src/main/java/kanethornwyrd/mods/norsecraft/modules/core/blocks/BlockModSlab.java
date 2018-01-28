package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import java.util.HashMap;
import java.util.Random;


import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlockSlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public abstract class BlockModSlab extends BlockSlab implements IModBlock {
static boolean tempDoubleSlab;
boolean doubleSlab;
private final String[] variants;
private final String bareName;
public static final PropertyEnum prop = PropertyEnum.create("prop", DummyEnum.class);
public static HashMap<BlockModSlab, BlockModSlab> halfSlabs = new HashMap();
public static HashMap<BlockModSlab, BlockModSlab> fullSlabs = new HashMap();

public BlockModSlab( String name, Material materialIn, boolean doubleSlab ) {
  super(hacky(materialIn, doubleSlab));
  this.doubleSlab = doubleSlab;
  if (doubleSlab) {
    name = name + "_double";
  }
  
  this.variants = new String[]{name};
  this.bareName = name;
  this.setUnlocalizedName(name);
  if (!doubleSlab) {
    this.useNeighborBrightness = true;
    this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(prop, BlockModSlab.DummyEnum.BLARG));
  }
  
  this.setCreativeTab(doubleSlab ? null : CreativeTabs.BUILDING_BLOCKS);
}

public static Material hacky(Material m, boolean doubleSlab) {
  tempDoubleSlab = doubleSlab;
  return m;
}

public BlockStateContainer createBlockState() {
  return tempDoubleSlab ? new BlockStateContainer(this, new IProperty[]{this.getVariantProp()}) : new BlockStateContainer(this, new IProperty[]{HALF, this.getVariantProp()});
}

public IBlockState getStateFromMeta( int meta ) {
  return this.doubleSlab ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, meta == 8 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
}

public int getMetaFromState(IBlockState state) {
  if (this.doubleSlab) {
    return 0;
  } else {
    return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
  }
}

public BlockSlab getFullBlock() {
  return (BlockSlab)fullSlabs.get(this);
}

public BlockSlab getSingleBlock() {
  return (BlockSlab)halfSlabs.get(this);
}

public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
  return new ItemStack(this.getSingleBlock());
}

public Item getItemDropped( IBlockState p_149650_1_, Random p_149650_2_, int p_149650_3_ ) {
  return Item.getItemFromBlock(this.getSingleBlock());
}

public int quantityDropped(IBlockState state, int fortune, Random random) {
  return super.quantityDropped(state, fortune, random);
}

public void register() {
  this.setRegistryName(this.getPrefix() + this.bareName);
  ProxyRegistry.register(this);
  if (!this.isDouble()) {
    ProxyRegistry.register(new ItemModBlockSlab(this, new ResourceLocation(this.getPrefix() + this.bareName)));
  }
  
}

public String getBareName() {
  return this.bareName;
}

public String[] getVariants() {
  return this.variants;
}

public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

public EnumRarity getBlockRarity(ItemStack stack) {
  return EnumRarity.COMMON;
}

public IProperty[] getIgnoredProperties() {
  return this.doubleSlab ? new IProperty[]{prop, HALF} : new IProperty[]{prop};
}

public String getUnlocalizedName(int meta) {
  return this.getUnlocalizedName();
}

public boolean isDouble() {
  return this.doubleSlab;
}

public boolean isFullBlock(IBlockState state) {
  return this.isDouble();
}

public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
  IBlockState state = this.getActualState(base_state, world, pos);
  return this.isDouble() || state.getValue(BlockSlab.HALF) == EnumBlockHalf.TOP && side == EnumFacing.UP || state.getValue(BlockSlab.HALF) == EnumBlockHalf.BOTTOM && side == EnumFacing.DOWN;
}

public IProperty<?> getVariantProp() {
  return prop;
}

public IProperty<?> getVariantProperty() {
  return prop;
}

public Class getVariantEnum() {
  return BlockModSlab.DummyEnum.class;
}

public Comparable<?> getTypeForItem(ItemStack stack) {
  return BlockModSlab.DummyEnum.BLARG;
}

public static void initSlab(Block base, int meta, BlockModSlab half, BlockModSlab full) {
  fullSlabs.put(half, full);
  fullSlabs.put(full, full);
  halfSlabs.put(half, half);
  halfSlabs.put(full, half);
  half.register();
  full.register();
}

public static enum DummyEnum implements BlockMetaVariants.EnumBase {
  BLARG;
  
  private DummyEnum() {
  }
}
}
