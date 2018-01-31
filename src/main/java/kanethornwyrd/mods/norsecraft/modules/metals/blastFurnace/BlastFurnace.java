package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.BlockFacing;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.tile.TileSimpleInventory;
import kanethornwyrd.mods.norsecraft.modules.metals.lib.ModGuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;
import static kanethornwyrd.mods.norsecraft.modules.metals.BlockNames.BLASTFURNACE;


public class BlastFurnace extends BlockFacing {
Random random;


public BlastFurnace() {
  super(BLASTFURNACE, Material.IRON, BLASTFURNACE);
  setHardness(5.0F);
  setResistance(10.0F);
  setSoundType(SoundType.METAL);
  setCreativeTab(Norsecraft.creativeTab.INSTANCE);
  setLightLevel(0.5F);
  GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, MOD_ID + ":" + BLASTFURNACE);
  random = new Random();
}

public static void setState( boolean active, World worldIn, BlockPos pos ) {
  IBlockState iblockstate = worldIn.getBlockState(pos);
  TileEntity tileentity = worldIn.getTileEntity(pos);
  if (active) {
    worldIn.setBlockState(pos, BlastFurnaceFeature.blastFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
  } else {
    worldIn.setBlockState(pos, BlastFurnaceFeature.blastFurnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
  }
  
  if (tileentity != null) {
    tileentity.validate();
    worldIn.setTileEntity(pos, tileentity);
  }
  
}

@Override
public boolean isFullBlock( IBlockState state ) {
  return false;
}

@Override
public boolean isOpaqueCube( IBlockState state ) {
  return false;
}

@Override
public boolean onBlockActivated( World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ ) {
  if (worldIn.isRemote) {
    return true;
  } else {
    TileEntity tileentity = worldIn.getTileEntity(pos);
    if (tileentity instanceof TileEntityBlastFurnace) {
      playerIn.openGui(Norsecraft.INSTANCE, ModGuiHandler.BLASTFURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
  }
  return true;
}

@Override
public EnumBlockRenderType getRenderType( IBlockState state ) {
  return EnumBlockRenderType.MODEL;
}

@Override
public void onBlockPlacedBy( World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack ) {
  worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
  if (stack.hasDisplayName()) {
    TileEntity tileentity = worldIn.getTileEntity(pos);
    
    if (tileentity instanceof TileEntityBlastFurnace) {
      ((TileEntityBlastFurnace) tileentity).setCustomName(stack.getDisplayName());
    }
  }
}


public void breakBlock( World par1World, BlockPos pos, IBlockState state ) {
  TileSimpleInventory inv = (TileSimpleInventory) par1World.getTileEntity(pos);
  TileEntity te = par1World.getTileEntity(pos);
  
  if (inv != null && te instanceof TileEntityBlastFurnace && !inv.isEmpty()) {
    InventoryHelper.dropInventoryItems(par1World, pos, inv);
  }
  
  super.breakBlock(par1World, pos, state);
}

@Override
public TileEntity createNewTileEntity( World worldIn, int meta ) {
  return new TileEntityBlastFurnace();
}

@Override
public EnumRarity getBlockRarity( ItemStack stack ) {
  return EnumRarity.UNCOMMON;
}

}
