package kanethornwyrd.mods.norsecraft.modules.metals.dragonglass;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModAxe;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.DRAGONGLASSAXE;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassMaterial;


public class ItemDragonglassAxe extends ItemModAxe {
public ItemDragonglassAxe() {
  super(1F, -2F, dragonglassMaterial, DRAGONGLASSAXE);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == dragonglassIngot) {
    return true;
  }
  return false;
}
}
