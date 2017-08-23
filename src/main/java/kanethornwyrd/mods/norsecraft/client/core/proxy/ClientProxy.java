package kanethornwyrd.mods.norsecraft.client.core.proxy;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.client.core.handler.PersistentVariableHelper;
import kanethornwyrd.mods.norsecraft.common.core.proxy.IProxy;
import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        PersistentVariableHelper.setCacheFile(new File(Minecraft.getMinecraft().mcDataDir, LibMisc.MOD_ID + ".dat"));
        try {
            PersistentVariableHelper.load();
            PersistentVariableHelper.save();
        } catch (IOException e) {
            Norsecraft.logger.fatal("Persistent Variables couldn't load!!");
        }

        initRenderers();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        // MinecraftForge.EVENT_BUS.registerBlocks(HUDHandler.class);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @SideOnly(Side.CLIENT)
    private void initRenderers() {
    }

    @Override
    public boolean isTheClientPlayer(EntityLivingBase entity) {
        return entity == Minecraft.getMinecraft().player;
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public String getLastVersion() {
        String s = PersistentVariableHelper.lastNorsecraftVersion;

        if(s == null)
            return "N/A";

        if(s.indexOf("-") > 0)
            return s.split("-")[1];

        return s;
    }

    @Override
    public int getClientRenderDistance() {
        return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(LibMisc.MOD_ID + ":" + id, "inventory"));
    }
}