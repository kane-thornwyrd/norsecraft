package kanethornwyrd.mods.norsecraft.interf;

import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public interface IStateMapperProvider {
@SideOnly(Side.CLIENT)
IStateMapper getStateMapper();
}
