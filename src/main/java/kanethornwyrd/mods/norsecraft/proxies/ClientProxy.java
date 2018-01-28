package kanethornwyrd.mods.norsecraft.proxies;

import kanethornwyrd.mods.norsecraft.client.*;
import kanethornwyrd.mods.norsecraft.common.lib.Obfuscation;

import kanethornwyrd.mods.norsecraft.lib.ModelHandler;
import kanethornwyrd.mods.norsecraft.moduleFramework.ModuleLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ClientProxy extends CommonProxy {

    ResourceProxy resourceProxy;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(ModelHandler.class);
        ModuleLoader.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(ClientTicker.class);
        ModuleLoader.initClient(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ModuleLoader.postInitClient(event);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void hookResourceProxy() {
        List<IResourcePack> packs = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), Obfuscation.DEFAULT_RESOURCE_PACKS);
        resourceProxy = new ResourceProxy();
        packs.add(resourceProxy);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addResourceOverride(String space, String dir, String file, String ext) {
        resourceProxy.addResource(space, dir, file, ext);
    }

}