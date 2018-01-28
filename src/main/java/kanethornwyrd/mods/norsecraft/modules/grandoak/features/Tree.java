package kanethornwyrd.mods.norsecraft.modules.grandoak.features;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.common.lib.ColorHandler;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.grandoak.tree.Leaf;
import kanethornwyrd.mods.norsecraft.modules.grandoak.tree.Log;
import kanethornwyrd.mods.norsecraft.modules.grandoak.tree.Sapling;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Tree extends Feature {

public static Block grandOakLog;
public static Block grandOakLeaf;
public static Block grandOakSapling;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  grandOakLog = new Log().setCreativeTab(Norsecraft.creativeTab);
  
  grandOakSapling = new Sapling().setCreativeTab(Norsecraft.creativeTab);
  
  grandOakLeaf = new Leaf(grandOakSapling).setCreativeTab(Norsecraft.creativeTab);
}

@Override
@SideOnly(Side.CLIENT)
public void postInitClient( FMLPostInitializationEvent event ) {
  ColorHandler.blockColourHandlerRegister(grandOakLeaf);
  ColorHandler.itemColourHandlerRegister(Item.getItemFromBlock(grandOakLeaf));
}

@Override
public boolean requiresMinecraftRestartToEnable() {
  return true;
}

}
