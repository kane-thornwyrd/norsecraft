package kanethornwyrd.mods.norsecraft.modules.grandoak.manufacturedProducts;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModSlab;
import kanethornwyrd.mods.norsecraft.modules.grandoak.lib.BlockNames;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Slab extends ModSlab {
  public Slab(boolean doubleSlab){
    super(BlockNames.GRANDOAKSLAB, Material.WOOD, doubleSlab);
    setHardness(2F);
    setSoundType(SoundType.WOOD);
  }


}
