package kanethornwyrd.mods.norsecraft.moduleFramework;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_NAME;

public class Module {
public final String name = makeName();
public final Map<String, Feature> features = new HashMap();
public final List<Feature> enabledFeatures = new ArrayList();
public boolean enabled;

public void registerFeature( Feature feature ) {
  registerFeature(feature, convertName(feature.getClass().getSimpleName()));
}

public void registerFeature( Feature feature, String name ) {
  registerFeature(feature, name, true);
}

public String convertName( String origName ) {
  String withSpaces = origName.replaceAll("(?<=.)([A-Z])", " $1").toLowerCase();
  return Character.toUpperCase(withSpaces.charAt(0)) + withSpaces.substring(1);
}

public void registerFeature( Feature feature, String name, boolean enabledByDefault ) {
  ModuleLoader.featureInstances.put(feature.getClass(), feature);
  features.put(name, feature);
  
  feature.enabledByDefault = enabledByDefault;
  feature.prevEnabled = false;
  
  feature.module = this;
  feature.configName = name;
  feature.configCategory = this.name + "." + name;
}

public void registerFeature( Feature feature, boolean enabledByDefault ) {
  registerFeature(feature, convertName(feature.getClass().getSimpleName()), enabledByDefault);
}

public void setupConfig() {
  if (features.isEmpty())
    addFeatures();
  
  forEachFeature(feature -> {
    ConfigHelper.needsRestart = feature.requiresMinecraftRestartToEnable();
    feature.enabled = loadPropBool(feature.configName, feature.getFeatureDescription(), feature.enabledByDefault) && enabled;
    
    feature.setupConstantConfig();
    
    if (!feature.forceLoad && !GlobalConfig.enableAntiOverlap) {
      String[] incompatibilities = feature.getIncompatibleMods();
      if (incompatibilities != null) {
        List<String> failiures = new ArrayList();
        
        for (String s : incompatibilities)
          if (Loader.isModLoaded(s)) {
            feature.enabled = false;
            failiures.add(s);
          }
        
        if (!failiures.isEmpty())
          FMLLog.info("[Quark] '" + feature.configName + "' is forcefully disabled as it's incompatible with the following loaded mods: " + failiures);
      }
    }
    
    if (!feature.loadtimeDone) {
      feature.enabledAtLoadtime = feature.enabled;
      feature.loadtimeDone = true;
    }
    
    if (feature.enabled && !enabledFeatures.contains(feature))
      enabledFeatures.add(feature);
    else if (!feature.enabled && enabledFeatures.contains(feature))
      enabledFeatures.remove(feature);
    
    feature.setupConfig();
    
    if (!feature.enabled && feature.prevEnabled) {
      if (feature.hasSubscriptions())
        MinecraftForge.EVENT_BUS.unregister(feature);
      if (feature.hasTerrainSubscriptions())
        MinecraftForge.TERRAIN_GEN_BUS.unregister(feature);
      if (feature.hasOreGenSubscriptions())
        MinecraftForge.ORE_GEN_BUS.unregister(feature);
    } else if (feature.enabled && (feature.enabledAtLoadtime || !feature.requiresMinecraftRestartToEnable()) && !feature.prevEnabled) {
      if (feature.hasSubscriptions())
        MinecraftForge.EVENT_BUS.register(feature);
      if (feature.hasTerrainSubscriptions())
        MinecraftForge.TERRAIN_GEN_BUS.register(feature);
      if (feature.hasOreGenSubscriptions())
        MinecraftForge.ORE_GEN_BUS.register(feature);
    }
    
    feature.prevEnabled = feature.enabled;
  });
}

public void addFeatures() {
  // NO-OP
}

public final void forEachFeature( Consumer<Feature> consumer ) {
  features.values().forEach(consumer);
}

public final boolean loadPropBool( String propName, String desc, boolean default_ ) {
  return ConfigHelper.loadPropBool(propName, name, desc, default_);
}

public void preInit( FMLPreInitializationEvent event ) {
  forEachEnabled(feature -> feature.preInit(event));
}

public final void forEachEnabled( Consumer<Feature> consumer ) {
  enabledFeatures.forEach(consumer);
}

public void init( FMLInitializationEvent event ) {
  forEachEnabled(feature -> feature.init(event));
}

public void postInit( FMLPostInitializationEvent event ) {
  forEachEnabled(feature -> feature.postInit(event));
}

public void finalInit( FMLPostInitializationEvent event ) {
  forEachEnabled(feature -> feature.finalInit(event));
}

@SideOnly(Side.CLIENT)
public void preInitClient( FMLPreInitializationEvent event ) {
  forEachEnabled(feature -> feature.preInitClient(event));
}

@SideOnly(Side.CLIENT)
public void initClient( FMLInitializationEvent event ) {
  forEachEnabled(feature -> feature.initClient(event));
}

@SideOnly(Side.CLIENT)
public void postInitClient( FMLPostInitializationEvent event ) {
  forEachEnabled(feature -> feature.postInitClient(event));
}

public void serverStarting( FMLServerStartingEvent event ) {
  forEachEnabled(feature -> feature.serverStarting(event));
}

public boolean canBeDisabled() {
  return true;
}

public boolean isEnabledByDefault() {
  return true;
}

String makeName() {
  return getClass().getSimpleName().replaceAll(MOD_NAME, "").toLowerCase();
}

public String getModuleDescription() {
  return "";
}

public final int loadPropInt( String propName, String desc, int default_ ) {
  return ConfigHelper.loadPropInt(propName, name, desc, default_);
}

public final double loadPropDouble( String propName, String desc, double default_ ) {
  return ConfigHelper.loadPropDouble(propName, name, desc, default_);
}

public final String loadPropString( String propName, String desc, String default_ ) {
  return ConfigHelper.loadPropString(propName, name, desc, default_);
}
}
