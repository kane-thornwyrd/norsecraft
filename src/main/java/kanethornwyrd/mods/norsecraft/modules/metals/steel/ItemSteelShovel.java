package kanethornwyrd.mods.norsecraft.modules.metals.steel;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModSpade;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.STEELSHOVEL;
import static kanethornwyrd.mods.norsecraft.modules.metals.steel.SteelFeature.steelIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.steel.SteelFeature.steelMaterial;


public class ItemSteelShovel extends ItemModSpade {
public ItemSteelShovel() {
  super(steelMaterial, STEELSHOVEL);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == steelIngot) {
    return true;
  }
  return false;
}
}
