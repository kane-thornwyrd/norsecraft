package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemMeshDefinition {
    @SideOnly(Side.CLIENT)
    ItemMeshDefinition getMeshDefinition ();
}