package kanethornwyrd.mods.norsecraft.modules.metals.dragonglass;

import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModPickaxe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.DRAGONGLASSPICKAXE;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassIngot;
import static kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.DragonglassFeature.dragonglassMaterial;


public class ItemDragonglassPickaxe extends ItemModPickaxe {
public ItemDragonglassPickaxe() {
  super(dragonglassMaterial, DRAGONGLASSPICKAXE);
  this.addEfficiencyOn(Blocks.OBSIDIAN);
}

@Override
public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  if (repair.getItem() == dragonglassIngot) {
    return true;
  }
  return false;
}
}
