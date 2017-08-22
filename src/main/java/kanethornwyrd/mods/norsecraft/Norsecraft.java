package kanethornwyrd.mods.norsecraft;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModBlocks;
import kanethornwyrd.mods.norsecraft.common.command.CommandHelloWorld;
import kanethornwyrd.mods.norsecraft.common.core.proxy.IProxy;
import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModItems;
import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = LibMisc.MOD_ID,
        version = LibMisc.VERSION,
        acceptedMinecraftVersions = LibMisc.MC_VERSION,
        dependencies = LibMisc.DEPENDENCIES
)
public class Norsecraft {
    @Instance(LibMisc.MOD_ID)
    public static Norsecraft instance;

    @SidedProxy(
            serverSide = LibMisc.PROXY_SERVER,
            clientSide = LibMisc.PROXY_CLIENT
    )
    public static IProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(LibMisc.MOD_NAME + " is loading !");
        logger = event.getModLog();

        ModBlocks.init();
        ModItems.init();

        this.proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        this.proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        this.proxy.postInit(event);
        System.out.println(LibMisc.MOD_NAME + " loaded !");
    }


    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHelloWorld());
    }

    @EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> registry = event.getRegistry();
            BaseRunes.registerBlocks(registry);
            ModBlocks.register(registry);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> registry = event.getRegistry();
            ModItems.register(registry);
            //BaseRunes.registerItems(registry);
        }

    }
}
