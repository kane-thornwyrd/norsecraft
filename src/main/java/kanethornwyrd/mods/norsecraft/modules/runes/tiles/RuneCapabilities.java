package kanethornwyrd.mods.norsecraft.modules.runes.tiles;

import net.minecraft.nbt.NBTBase;

import net.minecraftforge.common.util.INBTSerializable;



import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;


public class RuneCapabilities implements INBTSerializable, IFluidHandler {

@Override
public NBTBase serializeNBT() {
  return null;
}

@Override
public void deserializeNBT( NBTBase nbtBase ) {
}

@Override
public IFluidTankProperties[] getTankProperties() {
  return new IFluidTankProperties[ 0 ];
}

@Override
public int fill( FluidStack fluidStack, boolean b ) {
  return 0;
}

@Nullable
@Override
public FluidStack drain( FluidStack fluidStack, boolean b ) {
  return null;
}

@Nullable
@Override
public FluidStack drain( int i, boolean b ) {
  return null;
}
}
