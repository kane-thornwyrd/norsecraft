package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.metals.lib.ModGuiHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


public class BlastFurnaceFeature extends Feature {

public static Block blastFurnace;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  NetworkRegistry.INSTANCE.registerGuiHandler(Norsecraft.INSTANCE, new ModGuiHandler());
  blastFurnace = new BlastFurnace().setCreativeTab(Norsecraft.creativeTab);
}


@Override
public void preInitClient( FMLPreInitializationEvent event ) {
  NetworkRegistry.INSTANCE.registerGuiHandler(Norsecraft.INSTANCE, new ModGuiHandler());
}
}
