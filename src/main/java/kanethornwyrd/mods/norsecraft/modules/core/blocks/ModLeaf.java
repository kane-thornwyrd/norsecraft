package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.minecraft.util.BlockRenderLayer.TRANSLUCENT;

public abstract class ModLeaf extends BlockLeaves implements IModBlock {

public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
private final String[] variants;
private final String bareName;
protected Block sapling;
int[] surroundings;

public ModLeaf( String name, Block sapling, String... variants ) {
  super();
  if (variants.length == 0) {
    variants = new String[]{ name };
  }
  
  this.bareName = name;
  this.variants = variants;
  if (this.registerInConstruction()) {
    this.register(name);
  }
  sapling = sapling;
  setTickRandomly(true);
  setHardness(0.2F);
  setLightOpacity(1);
  setSoundType(SoundType.PLANT);
  GL11.glEnable(GL11.GL_BLEND);
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

@SideOnly(Side.CLIENT)
public int getRenderColor( int p_149741_1_ ) {
  return ColorizerFoliage.getFoliageColorBasic();
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
public List<ItemStack> onSheared( @Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune ) {
  List<ItemStack> sheared = new ArrayList<>();
  sheared.add(new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos)) & 3));
  return sheared;
}

@Override
public int getMetaFromState( IBlockState state ) {
  return state.getValue(DECAYABLE) ? 2 : 1;
}

/**
 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
 * returns the metadata of the dropped item based on the old metadata of the block.
 *
 * @param state
 */
@Override
public int damageDropped( IBlockState state ) {
  return super.damageDropped(state);
}

@Override
public BlockStateContainer createBlockState() {
  return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY);
}

public void updateTick( World worldIn, BlockPos pos, IBlockState state, Random rand ) {
  if (!worldIn.isRemote) {
    if (state.getValue(CHECK_DECAY).booleanValue() && state.getValue(DECAYABLE).booleanValue()) {
      int i = 4;
      int j = 5;
      int k = pos.getX();
      int l = pos.getY();
      int i1 = pos.getZ();
      int j1 = 32;
      int k1 = 1024;
      int l1 = 16;
      
      if (this.surroundings == null)
        this.surroundings = new int[ 32768 ];
      
      if (worldIn.isAreaLoaded(new BlockPos(k - 5, l - 5, i1 - 5), new BlockPos(k + 5, l + 5, i1 + 5))) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        
        for (int i2 = -4; i2 <= 4; ++i2) {
          for (int j2 = -4; j2 <= 4; ++j2) {
            for (int k2 = -4; k2 <= 4; ++k2) {
              IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
              Block block = iblockstate.getBlock();
              if (!block.canSustainLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
                if (block.isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
                  this.surroundings[ (i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16 ] = -2;
                } else {
                  this.surroundings[ (i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16 ] = -1;
                }
              } else {
                this.surroundings[ (i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16 ] = 0;
              }
            }
          }
        }
        
        for (int i3 = 1; i3 <= 4; ++i3) {
          for (int j3 = -4; j3 <= 4; ++j3) {
            for (int k3 = -4; k3 <= 4; ++k3) {
              for (int l3 = -4; l3 <= 4; ++l3) {
                if (this.surroundings[ (j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 ] == i3 - 1) {
                  if (this.surroundings[ (j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16 ] == -2) {
                    this.surroundings[ (j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16 ] = i3;
                  }
                  
                  if (this.surroundings[ (j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16 ] == -2) {
                    this.surroundings[ (j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16 ] = i3;
                  }
                  
                  if (this.surroundings[ (j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16 ] == -2) {
                    this.surroundings[ (j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16 ] = i3;
                  }
                  
                  if (this.surroundings[ (j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16 ] == -2) {
                    this.surroundings[ (j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16 ] = i3;
                  }
                  
                  if (this.surroundings[ (j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1) ] == -2) {
                    this.surroundings[ (j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1) ] = i3;
                  }
                  
                  if (this.surroundings[ (j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1 ] == -2) {
                    this.surroundings[ (j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1 ] = i3;
                  }
                }
              }
            }
          }
        }
      }
      
      int l2 = this.surroundings[ 16912 ];
      
      if (l2 >= 0) {
        worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
      } else {
        this.destroy(worldIn, pos);
      }
    }
  }
}

private void destroy( World worldIn, BlockPos pos ) {
  this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
  worldIn.setBlockToAir(pos);
}

/**
 * Returns the quantity of items to drop on block destruction.
 */
public int quantityDropped( Random p_149745_1_ ) {
  return p_149745_1_.nextInt(20) == 0 ? 1 : 0;
}

@Nullable
public Item getItemDropped( IBlockState state, Random rand, int fortune ) {
  return Item.getItemFromBlock(this.sapling);
}

/**
 * Drops the block items with a specified chance of dropping the specified items
 */
public void dropBlockAsItemWithChance( World worldIn, BlockPos pos, IBlockState state, float chance, int fortune ) {
  super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
}

protected int getSaplingDropChance( IBlockState state ) {
  return 20;
}

@Override
public boolean isOpaqueCube( IBlockState state ) {
  return false;
}

@Override
public BlockRenderLayer getBlockLayer() {
  return TRANSLUCENT;
}

@Override
public boolean causesSuffocation( IBlockState state ) {
  return false;
}

@Override
public EnumType getWoodType( int meta ) {
  return null;
}

@Override
public boolean isShearable( ItemStack item, IBlockAccess world, BlockPos pos ) {
  return true;
}

@Override
public boolean isLeaves( IBlockState state, IBlockAccess world, BlockPos pos ) {
  return true;
}

@Override
public void beginLeavesDecay( IBlockState state, World world, BlockPos pos ) {
  if (!state.getValue(CHECK_DECAY)) {
    world.setBlockState(pos, state.withProperty(CHECK_DECAY, true), 4);
  }
}

/**
 * A randomly called display update to be able to add particles or other items for display
 */
@SideOnly(Side.CLIENT)
public void randomDisplayTick( World worldIn, BlockPos pos, Random rand ) {
  if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isOpaqueCube() && rand.nextInt(15) == 1) {
    double d0 = (double) ((float) pos.getX() + rand.nextFloat());
    double d1 = (double) pos.getY() - 0.05D;
    double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
    worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
  }
}

private void removeLeaves( World p_150126_1_, int p_150126_2_, int p_150126_3_, int p_150126_4_ ) {
  this.dropBlockAsItem(p_150126_1_, new BlockPos(p_150126_2_, p_150126_3_, p_150126_4_), p_150126_1_.getBlockState(new BlockPos(p_150126_2_, p_150126_3_, p_150126_4_)), 0);
  p_150126_1_.setBlockToAir(new BlockPos(p_150126_2_, p_150126_3_, p_150126_4_));
}

@SideOnly(Side.CLIENT)
public int getBlockColor() {
  return 0x000000;
}
}