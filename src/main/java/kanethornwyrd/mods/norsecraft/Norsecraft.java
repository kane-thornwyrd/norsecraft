package kanethornwyrd.mods.norsecraft;

import kanethornwyrd.mods.norsecraft.common.command.CommandHelloWorld;
import kanethornwyrd.mods.norsecraft.common.core.NorsecraftCreativeTab;
import kanethornwyrd.mods.norsecraft.common.core.proxy.IProxy;
import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModBlocks;
import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModItems;
import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = LibMisc.MOD_ID,
        version = LibMisc.VERSION,
        acceptedMinecraftVersions = LibMisc.MC_VERSION,
        dependencies = LibMisc.DEPENDENCIES,
        canBeDeactivated = false
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
    public static final NorsecraftCreativeTab creativeTab = NorsecraftCreativeTab.INSTANCE;

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
            ModBlocks.registerBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.registerItems(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModBlocks.registerItemModels();
            ModItems.registerModels();
        }

    }
}
