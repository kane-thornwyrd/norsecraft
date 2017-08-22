package kanethornwyrd.mods.norsecraft.common.core.proxy;

import kanethornwyrd.mods.norsecraft.common.lib.LibMisc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {}

    @Override
    public boolean isTheClientPlayer(EntityLivingBase entity) { return false; }

    @Override
    public EntityPlayer getClientPlayer() { return null; }

    @Override
    public String getLastVersion() { return LibMisc.BUILD; }

    @Override
    public int getClientRenderDistance() { return 0; }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {

    }
}
