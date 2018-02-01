package kanethornwyrd.mods.norsecraft.modules.metals.steel;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModPickaxe;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.STEELPICKAXE;
import static kanethornwyrd.mods.norsecraft.modules.metals.steel.SteelFeature.steelIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.steel.SteelFeature.steelMaterial;


public class ItemSteelPickaxe extends ItemModPickaxe {
public ItemSteelPickaxe() {
  super(steelMaterial, STEELPICKAXE);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == steelIngot) {
    return true;
  }
  return false;
  
}
}
