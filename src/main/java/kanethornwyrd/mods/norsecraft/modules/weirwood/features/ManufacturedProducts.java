package kanethornwyrd.mods.norsecraft.modules.weirwood.features;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.BlockModSlab;
import kanethornwyrd.mods.norsecraft.modules.weirwood.manufacturedProducts.Plank;
import kanethornwyrd.mods.norsecraft.modules.weirwood.manufacturedProducts.Slab;
import kanethornwyrd.mods.norsecraft.modules.weirwood.manufacturedProducts.Stairs;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;


public class ManufacturedProducts extends Feature {

public static Block weirwoodPlank;
public static Block weirwoodSlab;
public static Block weirwoodStairs;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  weirwoodPlank = new Plank().setCreativeTab(Norsecraft.creativeTab);
  
  weirwoodSlab = new Slab(false).setCreativeTab(Norsecraft.creativeTab);
  BlockModSlab.initSlab(weirwoodPlank, OreDictionary.WILDCARD_VALUE, (BlockModSlab) weirwoodSlab, new Slab(true));
  
  weirwoodStairs = new Stairs().setCreativeTab(Norsecraft.creativeTab);
}

@Override
public void init( FMLInitializationEvent event ) {
  OreDictionary.registerOre("plankWood", ProxyRegistry.newStack(weirwoodPlank, 1, OreDictionary.WILDCARD_VALUE));
}

@Override
public String getFeatureDescription() {
  return super.getFeatureDescription();
}

@Override
public boolean requiresMinecraftRestartToEnable() { return true; }
}
