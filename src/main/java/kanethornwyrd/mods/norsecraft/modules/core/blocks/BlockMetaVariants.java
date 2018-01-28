package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Locale;

public abstract class BlockMetaVariants<T extends Enum<T> & IStringSerializable> extends ModBlock {
public static Class temporaryVariantsEnum;
public static PropertyEnum temporaryVariantProp;
public final Class<T> variantsEnum;
public final PropertyEnum<T> variantProp;

public BlockMetaVariants( String name, Material materialIn, Class<T> variantsEnum ) {
  super(name, materialIn, asVariantArray(variantsEnum));
  this.variantsEnum = variantsEnum;
  this.variantProp = temporaryVariantProp;
  this.setDefaultState(this.blockState.getBaseState().withProperty(this.variantProp, (T) ((Enum[]) variantsEnum.getEnumConstants())[ 0 ]));
  this.setUnlocalizedName(name);
}

public static String[] asVariantArray( Class e ) {
  temporaryVariantsEnum = e;
  temporaryVariantProp = PropertyEnum.create("variant", e);
  Enum[] values = (Enum[]) ((Enum[]) e.getEnumConstants());
  String[] variants = new String[ values.length ];
  
  for (int i = 0; i < values.length; ++i) {
    variants[ i ] = values[ i ].name().toLowerCase(Locale.ENGLISH);
  }
  
  return variants;
}

public boolean registerInConstruction() {
  return false;
}

public BlockStateContainer createBlockState() {
  return new BlockStateContainer(this, new IProperty[]{ temporaryVariantProp });
}

public IBlockState getStateFromMeta( int meta ) {
  if (meta >= ((Enum[]) this.variantsEnum.getEnumConstants()).length) {
    meta = 0;
  }
  
  return this.getDefaultState().withProperty(this.variantProp, (T) ((Enum[]) this.variantsEnum.getEnumConstants())[ meta ]);
}

public int damageDropped( IBlockState state ) {
  return this.getMetaFromState(state);
}

public int getMetaFromState( IBlockState state ) {
  return ((Enum) state.getValue(this.variantProp == null ? temporaryVariantProp : this.variantProp)).ordinal();
}

public ItemStack getPickBlock( IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player ) {
  return new ItemStack(this, 1, this.getMetaFromState(world.getBlockState(pos)));
}

public Class<T> getVariantEnum() {
  return this.variantsEnum;
}

public IProperty getVariantProp() {
  return this.variantProp;
}

public interface EnumBase extends IStringSerializable {
  default String getName() {
    return ((Enum) this).name().toLowerCase(Locale.ENGLISH);
  }
}
}
