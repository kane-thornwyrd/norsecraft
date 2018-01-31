package kanethornwyrd.mods.norsecraft.modules.metals.dragonglass;


import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.RecipesBlastFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



import static kanethornwyrd.mods.norsecraft.modules.metals.steel.SteelFeature.steelIngot;

public class DragonglassFeature extends Feature {


public static Item dragonglassIngot ;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  dragonglassIngot = new ItemDragonglassIngot().setCreativeTab(Norsecraft.creativeTab);
  RecipesBlastFurnace.addRecipe(new ItemStack(steelIngot), new ItemStack(Blocks.OBSIDIAN), new ItemStack(dragonglassIngot ));
}


@Override
public void preInitClient( FMLPreInitializationEvent event ) {
}
}

