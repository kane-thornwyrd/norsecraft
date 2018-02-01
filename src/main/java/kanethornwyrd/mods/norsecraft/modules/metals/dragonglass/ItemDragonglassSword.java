package kanethornwyrd.mods.norsecraft.modules.metals.dragonglass;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModSword;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.DRAGONGLASSSWORD;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassMaterial;


public class ItemDragonglassSword extends ItemModSword {
public ItemDragonglassSword() {
  super(dragonglassMaterial, DRAGONGLASSSWORD);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == dragonglassIngot) {
    return true;
  }
  return false;
  
}
}
