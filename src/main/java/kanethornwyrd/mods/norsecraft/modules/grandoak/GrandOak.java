package kanethornwyrd.mods.norsecraft.modules.grandoak;

import kanethornwyrd.mods.norsecraft.moduleFramework.Module;
import kanethornwyrd.mods.norsecraft.modules.grandoak.features.ManufacturedProducts;
import kanethornwyrd.mods.norsecraft.modules.grandoak.features.Tree;
import kanethornwyrd.mods.norsecraft.modules.grandoak.features.WorldGen;

public class GrandOak extends Module {

@Override
public void addFeatures() {
  registerFeature(new Tree());
  registerFeature(new ManufacturedProducts(), "All the sub-products made out of grand oak wood");
  registerFeature(new WorldGen());
}

@Override
public boolean canBeDisabled() {
  return true;
}

@Override
public String getModuleDescription() {
  return "The Grand Oak";
}
}
