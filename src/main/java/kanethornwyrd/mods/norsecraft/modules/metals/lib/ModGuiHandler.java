package kanethornwyrd.mods.norsecraft.modules.metals.lib;

import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.ContainerBlastFurnace;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.GUIBlastFurnace;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class ModGuiHandler implements IGuiHandler {
public static final int BLASTFURNACE = 0;

@Override
public Container getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
  switch (ID) {
    case BLASTFURNACE:
      return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
    default:
      return null;
  }
}

@Override
public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z ) {
  switch (ID) {
    case BLASTFURNACE:
      return new GUIBlastFurnace(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
    default:
      return null;
  }
}
}
