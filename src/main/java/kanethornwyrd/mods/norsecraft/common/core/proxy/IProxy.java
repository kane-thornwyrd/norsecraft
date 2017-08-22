package kanethornwyrd.mods.norsecraft.common.core.proxy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    void preInit(FMLPreInitializationEvent event);
    void init(FMLInitializationEvent event);
    void postInit(FMLPostInitializationEvent event);
    boolean isTheClientPlayer(EntityLivingBase entity);
    EntityPlayer getClientPlayer();
    String getLastVersion();
    int getClientRenderDistance();
    public void registerItemRenderer(Item item, int meta, String id);
}
