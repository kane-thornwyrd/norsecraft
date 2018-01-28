package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;


import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class ModSapling extends BlockBush implements IGrowable, IModBlock {

public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
                                                                      0.09999999403953552D, 0.8999999761581421D,
                                                                      0.800000011920929D, 0.8999999761581421D);
private final String[] variants;
private final String bareName;

public ModSapling( String name, String... variants ) {
  super();
  if (variants.length == 0) {
    variants = new String[]{ name };
  }

  this.bareName = name;
  this.variants = variants;
  if (this.registerInConstruction()) {
    this.register(name);
  }
  setTickRandomly(true);
  setHardness(0.2F);
  setLightOpacity(1);
  setSoundType(SoundType.PLANT);
//    GL11.glEnable(GL11.GL_BLEND);
}

public boolean registerInConstruction() {
  return true;
}

public Block register( String name ) {
  this.setRegistryName(this.getPrefix() + name);
  ProxyRegistry.register(this);
  ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(this.getPrefix() + name)));
  return this;
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

/**
 * Convert the given metadata into a BlockState for this Block
 */
public IBlockState getStateFromMeta( int meta ) {
  return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
}

/**
 * Convert the BlockState into the correct metadata value
 */
public int getMetaFromState( IBlockState state ) {
  int i = 0;
  i = i | ((Integer) state.getValue(STAGE)).intValue() << 3;
  return i;
}

protected BlockStateContainer createBlockState() {
  return new BlockStateContainer(this, new IProperty[]{ STAGE });
}

/**
 * Called when a plant grows on this block, only implemented for saplings using the WorldGen*Trees classes right now.
 * Modder may implement this for custom plants.
 * This does not use ForgeDirection, because large/huge trees can be located in non-representable direction,
 * so the source location is specified.
 * Currently this just changes the block to dirt if it was grass.
 * <p>
 * Note: This happens DURING the generation, the generation may not be complete when this is called.
 *
 * @param state  The current state
 * @param world  Current world
 * @param pos    Block position in world
 * @param source Source plant's position in world
 */
@Override
public void onPlantGrow( IBlockState state, World world, BlockPos pos, BlockPos source ) {
  super.onPlantGrow(state, world, pos, source);
}

/**
 * Whether this IGrowable can grow
 *
 * @param worldIn
 * @param pos
 * @param state
 * @param isClient
 */
@Override
public boolean canGrow( World worldIn, BlockPos pos, IBlockState state, boolean isClient ) {
  return true;
}

@Override
public boolean canUseBonemeal( World worldIn, Random rand, BlockPos pos, IBlockState state ) {
  return true;
}

@Override
public void grow( World worldIn, Random rand, BlockPos pos, IBlockState state ) {
  if (((Integer) state.getValue(STAGE)).intValue() == 0) {
    worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
  } else {
    this.generateTree(worldIn, pos, state, rand);
  }
}

public void generateTree( World worldIn, BlockPos pos, IBlockState state, Random rand ) {
  if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
  int i = 0;
  int j = 0;
  String flag = "";

  Boolean notify = worldIn.isRemote;

  WorldGenerator worldGenerator = null;

  for (i = 0; i >= -2; --i) {
    for (j = 0; j >= -2; --j) {
      if (this.isThreeByThreeOfType(worldIn, pos, i, j)) {
        worldGenerator = this.generateThreeByThreeTree(notify);
        flag = "3x3";
      } else if (this.isTwoByTwoOfType(worldIn, pos, i, j)) {
        worldGenerator = this.generateTwoByTwoTree(notify);
        flag = "2x2";
      } else {
        worldGenerator = this.generateSingleTree(notify);
      }
    }
  }


  IBlockState airBlockstate = Blocks.AIR.getDefaultState();

  switch (flag) {
    case "3x3":
      BlockPos threeThreepos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
      worldIn.setBlockState(threeThreepos.add(i, 0, j), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i, 0, j + 1), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i, 0, j + 2), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 1, 0, j), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 1, 0, j + 1), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 1, 0, j + 2), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 2, 0, j), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 2, 0, j + 1), airBlockstate, 4);
      worldIn.setBlockState(threeThreepos.add(i + 2, 0, j + 2), airBlockstate, 4);
      break;
    case "2x2":
      BlockPos twoTwopos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
      worldIn.setBlockState(twoTwopos.add(i, 0, j), airBlockstate, 4);
      worldIn.setBlockState(twoTwopos.add(i + 1, 0, j), airBlockstate, 4);
      worldIn.setBlockState(twoTwopos.add(i, 0, j + 1), airBlockstate, 4);
      worldIn.setBlockState(twoTwopos.add(i + 1, 0, j + 1), airBlockstate, 4);
      break;
    case "":
      worldIn.setBlockState(pos, airBlockstate, 4);
  }

  if (!worldGenerator.generate(worldIn, rand, pos)) {
    switch (flag) {
      case "3x3":
        BlockPos threeThreepos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        worldIn.setBlockState(threeThreepos.add(i, 0, j), state, 4);
        worldIn.setBlockState(threeThreepos.add(i, 0, j + 1), state, 4);
        worldIn.setBlockState(threeThreepos.add(i, 0, j + 2), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 1, 0, j), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 1, 0, j + 1), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 1, 0, j + 2), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 2, 0, j), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 2, 0, j + 1), state, 4);
        worldIn.setBlockState(threeThreepos.add(i + 2, 0, j + 2), state, 4);
        break;
      case "2x2":
        BlockPos twoTwopos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        worldIn.setBlockState(twoTwopos.add(i, 0, j), state, 4);
        worldIn.setBlockState(twoTwopos.add(i + 1, 0, j), state, 4);
        worldIn.setBlockState(twoTwopos.add(i, 0, j + 1), state, 4);
        worldIn.setBlockState(twoTwopos.add(i + 1, 0, j + 1), state, 4);
        break;
      case "":
        worldIn.setBlockState(pos, state, 4);
    }
  }
}

protected boolean isThreeByThreeOfType( World worldIn, BlockPos pos, int x, int z ) {
  return this.isTypeAt(worldIn, pos.add(x, 0, z)) &&
                 this.isTypeAt(worldIn, pos.add(x, 0, z + 1)) &&
                 this.isTypeAt(worldIn, pos.add(x, 0, z + 2)) &&
                 this.isTypeAt(worldIn, pos.add(x + 1, 0, z)) &&
                 this.isTypeAt(worldIn, pos.add(x + 1, 0, z + 1)) &&
                 this.isTypeAt(worldIn, pos.add(x + 1, 0, z + 2)) &&
                 this.isTypeAt(worldIn, pos.add(x + 2, 0, z)) &&
                 this.isTypeAt(worldIn, pos.add(x + 2, 0, z + 1)) &&
                 this.isTypeAt(worldIn, pos.add(x + 2, 0, z + 2))
          ;
}

protected abstract WorldGenAbstractTree generateThreeByThreeTree( Boolean notify );

protected boolean isTwoByTwoOfType( World worldIn, BlockPos pos, int x, int z ) {
  return this.isTypeAt(worldIn, pos.add(x, 0, z)) &&
                 this.isTypeAt(worldIn, pos.add(x + 1, 0, z)) &&
                 this.isTypeAt(worldIn, pos.add(x, 0, z + 1)) &&
                 this.isTypeAt(worldIn, pos.add(x + 1, 0, z + 1))
          ;
}

protected abstract WorldGenAbstractTree generateTwoByTwoTree( Boolean notify );

protected abstract WorldGenAbstractTree generateSingleTree( Boolean notify );

public boolean isTypeAt( World worldIn, BlockPos pos ) {
  IBlockState iblockstate = worldIn.getBlockState(pos);
  return iblockstate.getBlock() == this;
}

public AxisAlignedBB getBoundingBox( IBlockState state, IBlockAccess source, BlockPos pos ) {
  return SAPLING_AABB;
}
}