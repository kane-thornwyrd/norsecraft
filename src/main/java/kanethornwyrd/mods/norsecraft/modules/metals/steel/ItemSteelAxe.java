package kanethornwyrd.mods.norsecraft.modules.metals.steel;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModAxe;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.STEELAXE;
import static kanethornwyrd.mods.norsecraft.modules.metals.features.Steel.steelIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.features.Steel.steelMaterial;


public class ItemSteelAxe extends ItemModAxe {
public ItemSteelAxe() {
  super(1.0F, -2F, steelMaterial, STEELAXE);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == steelIngot) {
    return true;
  }
  return false;
}
}
