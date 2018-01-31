package kanethornwyrd.mods.norsecraft.modules.metals.steel;


import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;


import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.RecipesBlastFurnace;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class SteelFeature extends Feature {


public static Item steelIngot;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  steelIngot = new ItemSteelIngot().setCreativeTab(Norsecraft.creativeTab);
  RecipesBlastFurnace.addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(steelIngot));
}


@Override
public void preInitClient( FMLPreInitializationEvent event ) {
}
}

