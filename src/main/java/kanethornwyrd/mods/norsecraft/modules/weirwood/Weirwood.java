package kanethornwyrd.mods.norsecraft.modules.weirwood;

import kanethornwyrd.mods.norsecraft.moduleFramework.Module;
import kanethornwyrd.mods.norsecraft.modules.weirwood.features.ManufacturedProducts;
import kanethornwyrd.mods.norsecraft.modules.weirwood.features.Tree;
import kanethornwyrd.mods.norsecraft.modules.weirwood.features.WorldGen;

public class Weirwood extends Module {

@Override
public void addFeatures() {
  registerFeature(new Tree());
  registerFeature(new ManufacturedProducts(), "All the sub-products made out of Weirwood");
  registerFeature(new WorldGen());
}

@Override
public boolean canBeDisabled() {
  return true;
}

@Override
public String getModuleDescription() {
  return "The Weirwood";
}
}
