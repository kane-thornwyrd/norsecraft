package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.BlockTileEntity;



import kanethornwyrd.mods.norsecraft.modules.metals.lib.ModGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

import static kanethornwyrd.mods.norsecraft.modules.metals.BlockNames.BLASTFURNACE;


public class BlastFurnace extends BlockTileEntity<TileEntityBlastFurnace> {

public BlastFurnace() {
  super(Material.IRON, BLASTFURNACE);
}


@Override
public boolean onBlockActivated( World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ ) {
  if (!world.isRemote) {
    TileEntityBlastFurnace tile = getTileEntity(world, pos);
    IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
    ItemStack heldItem = player.getHeldItem(hand);
    if (!player.isSneaking()) {
      if (heldItem.isEmpty()) {
        player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
      } else {
        player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));
      }
      tile.markDirty();
    } else {
      player.openGui(Norsecraft.INSTANCE, ModGuiHandler.BLASTFURNACE, world, pos.getX(), pos.getY(), pos.getZ());
    }
  }
  return true;
}

@Override
public void breakBlock(World world, BlockPos pos, IBlockState state) {
  TileEntityBlastFurnace tile = getTileEntity(world, pos);
  IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
  ItemStack stack = itemHandler.getStackInSlot(0);
  if (!stack.isEmpty()) {
    EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    world.spawnEntity(item);
  }
  super.breakBlock(world, pos, state);
}

@Override
public Class<TileEntityBlastFurnace> getTileEntityClass() {
  return TileEntityBlastFurnace.class;
}

@Nullable
@Override
public TileEntityBlastFurnace createTileEntity( World world, IBlockState state ) {
  return new TileEntityBlastFurnace();
}
}
