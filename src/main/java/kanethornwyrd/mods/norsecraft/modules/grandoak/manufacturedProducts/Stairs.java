package kanethornwyrd.mods.norsecraft.modules.grandoak.manufacturedProducts;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModStairs;
import kanethornwyrd.mods.norsecraft.modules.grandoak.lib.BlockNames;

import static kanethornwyrd.mods.norsecraft.modules.grandoak.features.ManufacturedProducts.grandOakPlank;

public class Stairs extends ModStairs{
  public Stairs() {
    super(BlockNames.GRANDOAKSTAIRS, grandOakPlank.getDefaultState());
  }
}
