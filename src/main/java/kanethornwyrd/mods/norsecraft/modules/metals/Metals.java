package kanethornwyrd.mods.norsecraft.modules.metals;

import kanethornwyrd.mods.norsecraft.moduleFramework.Module;
import kanethornwyrd.mods.norsecraft.modules.metals.features.BlastFurnace;



import kanethornwyrd.mods.norsecraft.modules.metals.features.Dragonglass;
import kanethornwyrd.mods.norsecraft.modules.metals.features.Steel;


public class Metals extends Module {


@Override
public void addFeatures() {
  registerFeature(new BlastFurnace());
  registerFeature(new Steel());
  registerFeature(new Dragonglass());
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
