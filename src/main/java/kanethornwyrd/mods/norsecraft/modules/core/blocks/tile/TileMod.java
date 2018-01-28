package kanethornwyrd.mods.norsecraft.modules.core.blocks.tile;

import net.minecraft.block.state.IBlockState;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TileMod extends TileEntity {
public TileMod() {
}

public boolean shouldRefresh( World world, BlockPos pos, IBlockState oldState, IBlockState newState ) {
  return oldState.getBlock() != newState.getBlock();
}

public NBTTagCompound writeToNBT( NBTTagCompound par1nbtTagCompound ) {
  NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
  this.writeSharedNBT(par1nbtTagCompound);
  return nbt;
}

public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
  super.readFromNBT(par1nbtTagCompound);
  this.readSharedNBT(par1nbtTagCompound);
}

public void writeSharedNBT(NBTTagCompound cmp) {
}

public void readSharedNBT(NBTTagCompound cmp) {
}

public NBTTagCompound getUpdateTag() {
  return this.writeToNBT(new NBTTagCompound());
  
}

public SPacketUpdateTileEntity getUpdatePacket() {
  NBTTagCompound cmp = new NBTTagCompound();
  this.writeSharedNBT(cmp);
  return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), cmp);
}

public void onDataPacket( NetworkManager net, SPacketUpdateTileEntity packet ) {
  super.onDataPacket(net, packet);
  this.readSharedNBT(packet.getNbtCompound());
}
}
