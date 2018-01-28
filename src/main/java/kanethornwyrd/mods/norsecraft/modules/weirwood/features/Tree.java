package kanethornwyrd.mods.norsecraft.modules.weirwood.features;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.common.lib.ColorHandler;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.weirwood.tree.Leaf;
import kanethornwyrd.mods.norsecraft.modules.weirwood.tree.Log;
import kanethornwyrd.mods.norsecraft.modules.weirwood.tree.Sapling;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Tree extends Feature {

public static Block weirwoodLog;
public static Block weirwoodLeaf;
public static Block weirwoodSapling;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  weirwoodLog = new Log().setCreativeTab(Norsecraft.creativeTab);
  
  weirwoodSapling = new Sapling().setCreativeTab(Norsecraft.creativeTab);
  
  weirwoodLeaf = new Leaf(weirwoodSapling).setCreativeTab(Norsecraft.creativeTab);
}

@Override
@SideOnly(Side.CLIENT)
public void postInitClient( FMLPostInitializationEvent event ) {
  ColorHandler.blockColourHandlerRegister(weirwoodLeaf);
  ColorHandler.itemColourHandlerRegister(Item.getItemFromBlock(weirwoodLeaf));
}

@Override
public boolean requiresMinecraftRestartToEnable() {
  return true;
}

}
