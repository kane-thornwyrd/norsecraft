package kanethornwyrd.mods.norsecraft.modules.core;

import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.core.items.ItemMod;
import kanethornwyrd.mods.norsecraft.modules.core.items.ItemNames;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;

public class IconFeature extends Feature {

public static Item icon;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  icon = new Icon();
}

@Override
public String getFeatureDescription() {
  return "The Icon of the mod";
}

public class Icon extends ItemMod {
  public Icon() {
    super(ItemNames.ICON);
    setMaxStackSize(1);
  }
  
  @Override
  public String getModNamespace() { return MOD_ID; }
}
}
