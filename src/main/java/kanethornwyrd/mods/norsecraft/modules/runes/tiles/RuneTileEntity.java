package kanethornwyrd.mods.norsecraft.modules.runes.tiles;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.tile.TileMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;


public class RuneTileEntity extends TileMod implements ICapabilitySerializable<NBTTagCompound> {
static Capability<IFluidHandler> FLUID_HANDLER_CAPABILITY = null;

private final RuneCapabilities cap = new RuneCapabilities();

@Override
public boolean hasCapability( Capability<?> capability, @Nullable EnumFacing facing ) {
  if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
    return true;
  }
  return super.hasCapability(capability, facing);
  
}

@Nullable
@Override
public <T> T getCapability( Capability<T> capability, @Nullable EnumFacing facing ) {
  if (capability == FLUID_HANDLER_CAPABILITY) {
    return FLUID_HANDLER_CAPABILITY.cast(cap);
  }
  return super.getCapability(capability, facing);
}

@Override
public void deserializeNBT( NBTTagCompound nbt ) {
  cap.deserializeNBT(nbt);
}

@Override
public NBTTagCompound serializeNBT() {
  return (NBTTagCompound) cap.serializeNBT();
}

}
