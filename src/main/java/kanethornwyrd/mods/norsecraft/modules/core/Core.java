package kanethornwyrd.mods.norsecraft.modules.core;

import kanethornwyrd.mods.norsecraft.moduleFramework.Module;

public class Core extends Module {

@Override
public void addFeatures() {
  registerFeature(new IconFeature());
}

@Override
public String getModuleDescription() {
  return "The Core of Norsecraft";
}


@Override
public boolean canBeDisabled() {
  return false;
}
}
