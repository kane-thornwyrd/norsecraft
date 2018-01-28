package kanethornwyrd.mods.norsecraft.moduleFramework;

import kanethornwyrd.mods.norsecraft.modules.core.Core;
import kanethornwyrd.mods.norsecraft.modules.grandoak.GrandOak;
import kanethornwyrd.mods.norsecraft.modules.metals.Metals;
import kanethornwyrd.mods.norsecraft.modules.weirwood.Weirwood;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;
import static kanethornwyrd.mods.norsecraft.Misc.MOD_NAME;


public class ModuleLoader {

public static Map<Class<? extends Module>, Module> moduleInstances = new HashMap();
public static Map<Class<? extends Feature>, Feature> featureInstances = new HashMap();
public static List<Module> enabledModules;
public static Configuration config;
public static File configFile;
private static List<Class<? extends Module>> moduleClasses;

static {
  moduleClasses = new ArrayList();
  
  //registerModule(QuarkTweaks.class);
//        registerModule(NorseBark.class);
  registerModule(Metals.class);
  registerModule(Weirwood.class);
  registerModule(GrandOak.class);
  registerModule(Core.class);
}

public static void preInit( FMLPreInitializationEvent event ) {
  moduleClasses.forEach(clazz -> {
    try {
      moduleInstances.put(clazz, clazz.newInstance());
    } catch (Exception e) {
      throw new RuntimeException("Can't initialize module " + clazz, e);
    }
  });
  
  setupConfig(event);
  forEachModule(module -> FMLLog.info("[" + MOD_NAME + "] Module " + module.name + " is " + (module.enabled ?
                                                                                               "enabled" :
                                                                                               "disabled")));
  
  forEachEnabled(module -> module.preInit(event));
}

public static void setupConfig( FMLPreInitializationEvent event ) {
  configFile = event.getSuggestedConfigurationFile();
  config = new Configuration(configFile);
  config.load();
  
  GlobalConfig.initGlobalConfig();
  
  forEachModule(module -> {
    module.enabled = true;
    if (module.canBeDisabled()) {
      ConfigHelper.needsRestart = true;
      module.enabled = ConfigHelper.loadPropBool(module.name, "_modules", module.getModuleDescription(), module.isEnabledByDefault());
    }
  });
  
  enabledModules = new ArrayList(moduleInstances.values());
  enabledModules.removeIf(module -> !module.enabled);
  
  loadModuleConfigs();
  
  MinecraftForge.EVENT_BUS.register(new ChangeListener());
}

public static void forEachModule( Consumer<Module> consumer ) {
  moduleInstances.values().forEach(consumer);
}

public static void forEachEnabled( Consumer<Module> consumer ) {
  if (enabledModules != null) {
    enabledModules.forEach(consumer);
  }
}

private static void loadModuleConfigs() {
  forEachModule(module -> module.setupConfig());
  
  if (config.hasChanged())
    config.save();
}

public static void init( FMLInitializationEvent event ) {
  forEachEnabled(module -> module.init(event));
}

public static void postInit( FMLPostInitializationEvent event ) {
  forEachEnabled(module -> module.postInit(event));
}

public static void finalInit( FMLPostInitializationEvent event ) {
  forEachEnabled(module -> module.finalInit(event));
}

@SideOnly(Side.CLIENT)
public static void preInitClient( FMLPreInitializationEvent event ) {
  forEachEnabled(module -> module.preInitClient(event));
}

@SideOnly(Side.CLIENT)
public static void initClient( FMLInitializationEvent event ) {
  forEachEnabled(module -> module.initClient(event));
}

@SideOnly(Side.CLIENT)
public static void postInitClient( FMLPostInitializationEvent event ) {
  forEachEnabled(module -> module.postInitClient(event));
}

public static void serverStarting( FMLServerStartingEvent event ) {
  forEachEnabled(module -> module.serverStarting(event));
}

public static boolean isModuleEnabled( Class<? extends Module> clazz ) {
  return moduleInstances.get(clazz).enabled;
}

public static boolean isFeatureEnabled( Class<? extends Feature> clazz ) {
  return featureInstances.get(clazz).enabled;
}

private static void registerModule( Class<? extends Module> clazz ) {
  if (!moduleClasses.contains(clazz))
    moduleClasses.add(clazz);
}

public static class ChangeListener {
  
  @SubscribeEvent
  public void onConfigChanged( ConfigChangedEvent.OnConfigChangedEvent eventArgs ) {
    if (eventArgs.getModID().equals(MOD_ID))
      loadModuleConfigs();
  }
  
}
}
