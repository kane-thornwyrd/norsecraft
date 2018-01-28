package kanethornwyrd.mods.norsecraft.proxies;


import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;
import kanethornwyrd.mods.norsecraft.moduleFramework.*;
import kanethornwyrd.mods.norsecraft.network.GuiHandler;
import kanethornwyrd.mods.norsecraft.network.MessageRegister;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

public void preInit(FMLPreInitializationEvent event ) {
  hookResourceProxy();
  ModuleLoader.preInit(event);
  MinecraftForge.EVENT_BUS.register(ProxyRegistry.class);
  NetworkRegistry.INSTANCE.registerGuiHandler(Norsecraft.INSTANCE, new GuiHandler());
  MessageRegister.init();
}

public void init(FMLInitializationEvent event ) {
  ModuleLoader.init(event);
}

public void postInit(FMLPostInitializationEvent event ) {
  ModuleLoader.postInit(event);
}

public void finalInit(FMLPostInitializationEvent event) {
  ModuleLoader.finalInit(event);
}

public void serverStarting(FMLServerStartingEvent event ) {
  ModuleLoader.serverStarting(event);

//  if(GlobalConfig.enableConfigCommand)
//      event.registerServerCommand(new CommandConfig());
}

public void hookResourceProxy() {
  // proxy override
}

public void addResourceOverride(String space, String dir, String file, String ext) {
  // proxy override
}
}
