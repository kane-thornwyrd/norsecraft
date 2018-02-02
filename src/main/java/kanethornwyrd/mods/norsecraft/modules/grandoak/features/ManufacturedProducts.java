package kanethornwyrd.mods.norsecraft.modules.grandoak.features;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.BlockModSlab;
import kanethornwyrd.mods.norsecraft.modules.grandoak.manufacturedProducts.Plank;
import kanethornwyrd.mods.norsecraft.modules.grandoak.manufacturedProducts.Slab;
import kanethornwyrd.mods.norsecraft.modules.grandoak.manufacturedProducts.Stairs;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;


public class ManufacturedProducts extends Feature {

public static Block grandOakPlank;
public static Block grandOakSlab;
public static Block grandOakStairs;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  grandOakPlank = new Plank().setCreativeTab(Norsecraft.creativeTab);
  
  grandOakSlab = new Slab(false).setCreativeTab(Norsecraft.creativeTab);
  BlockModSlab.initSlab(grandOakPlank, OreDictionary.WILDCARD_VALUE, (BlockModSlab) grandOakSlab, new Slab(true));
  
  grandOakStairs = new Stairs().setCreativeTab(Norsecraft.creativeTab);
}

@Override
public void init( FMLInitializationEvent event ) {
  OreDictionary.registerOre("plankWood", ProxyRegistry.newStack(grandOakPlank, 1, 0));
}

@Override
public String getFeatureDescription() {
  return super.getFeatureDescription();
}

@Override
public boolean requiresMinecraftRestartToEnable() { return true; }
}
