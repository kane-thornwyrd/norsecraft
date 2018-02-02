package kanethornwyrd.mods.norsecraft;

import kanethornwyrd.mods.norsecraft.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import static kanethornwyrd.mods.norsecraft.Misc.*;

@Mod(
        modid = MOD_ID,
        name = MOD_NAME,
        version = VERSION,
        acceptedMinecraftVersions = MC_VERSION,
        dependencies = DEPENDENCIES,
        canBeDeactivated = false
)
public class Norsecraft {

public static final NorsecraftCreativeTab creativeTab = NorsecraftCreativeTab.INSTANCE;

@SidedProxy(
  serverSide = PROXY_SERVER,
  clientSide = PROXY_CLIENT
)
public static CommonProxy proxy;
public static Logger logger;
/**
 * This is the instance of your mod as created by Forge. It will never be null.
 */
@Mod.Instance(MOD_ID)
public static Norsecraft INSTANCE;

/**
 * This is the first initialization event. Register tile entities here.
 * The registry events below will have fired prior to entry to this method.
 */
@Mod.EventHandler
public void preinit( FMLPreInitializationEvent event ) {
  System.out.println(Misc.MOD_NAME + " is loading !");
  logger = event.getModLog();
  proxy.preInit(event);
}

/**
 * This is the second initialization event. Register custom recipes
 */
@Mod.EventHandler
public void init( FMLInitializationEvent event ) {
  proxy.init(event);
}

/**
 * This is the final initialization event. Register actions from other mods here
 */
@Mod.EventHandler
public void postinit( FMLPostInitializationEvent event ) {
  proxy.postInit(event);
}

}
