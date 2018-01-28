package kanethornwyrd.mods.norsecraft.modules.metals;

import kanethornwyrd.mods.norsecraft.moduleFramework.Module;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.BlastFurnaceFeature;


public class Metals extends Module {


@Override
public void addFeatures() {
  registerFeature(new BlastFurnaceFeature());
}


@Override
public boolean canBeDisabled() {
  return true;
}

@Override
public String getModuleDescription() {
  return "The Metals";
}
}
