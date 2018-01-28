package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.tile.TileMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;


public abstract class BlockTileEntity<TE extends TileMod> extends ModBlock {
public BlockTileEntity( Material material, String name ) {
  super(name, material);
}

@Override
public Block setUnlocalizedName( String name ) {
  super.setUnlocalizedName(name);
  GameRegistry.registerTileEntity(this.getTileEntityClass(), this.getPrefix() + name);
  return this;
}

public abstract Class<TE> getTileEntityClass();

public TE getTileEntity( IBlockAccess world, BlockPos pos ) {
  return (TE) world.getTileEntity(pos);
}

@Override
public boolean hasTileEntity( IBlockState state ) {
  return true;
}

@Nullable
@Override
public abstract TE createTileEntity( World world, IBlockState state );
}
