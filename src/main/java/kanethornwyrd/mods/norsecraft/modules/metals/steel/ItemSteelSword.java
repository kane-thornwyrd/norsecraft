package kanethornwyrd.mods.norsecraft.modules.metals.steel;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModSword;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.STEELSWORD;
import static kanethornwyrd.mods.norsecraft.modules.metals.features.Steel.steelIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.features.Steel.steelMaterial;


public class ItemSteelSword extends ItemModSword {
public ItemSteelSword() {
  super(steelMaterial, STEELSWORD);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == steelIngot) {
    return true;
  }
  return false;
  
}
}
