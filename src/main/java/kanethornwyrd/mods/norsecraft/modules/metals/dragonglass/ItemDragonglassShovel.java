package kanethornwyrd.mods.norsecraft.modules.metals.dragonglass;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModSpade;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.DRAGONGLASSSHOVEL;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassMaterial;


public class ItemDragonglassShovel extends ItemModSpade {
public ItemDragonglassShovel() {
  super(dragonglassMaterial, DRAGONGLASSSHOVEL);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == dragonglassIngot) {
    return true;
  }
  return false;
}
}
