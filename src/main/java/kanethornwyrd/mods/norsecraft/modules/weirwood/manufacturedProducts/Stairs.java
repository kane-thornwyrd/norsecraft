package kanethornwyrd.mods.norsecraft.modules.weirwood.manufacturedProducts;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModStairs;
import kanethornwyrd.mods.norsecraft.modules.weirwood.lib.BlockNames;

import static kanethornwyrd.mods.norsecraft.modules.weirwood.features.ManufacturedProducts.weirwoodPlank;

public class Stairs extends ModStairs{
  public Stairs() {
    super(BlockNames.WEIRWOODSTAIRS, weirwoodPlank.getDefaultState());
  }
}
