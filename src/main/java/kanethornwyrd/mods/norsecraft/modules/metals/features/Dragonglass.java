package kanethornwyrd.mods.norsecraft.modules.metals.features;


import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.RecipesBlastFurnace;

import kanethornwyrd.mods.norsecraft.modules.metals.dragonglass.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.DRAGONGLASSMATERIAL;
import static kanethornwyrd.mods.norsecraft.modules.metals.features.Steel.steelIngot;

public class Dragonglass extends Feature {


public static Item dragonglassIngot;
public static Item dragonglassSword;
public static Item dragonglassPickaxe;
public static Item dragonglassAxe;
public static Item dragonglassShovel;
public static Item.ToolMaterial dragonglassMaterial;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  dragonglassMaterial = EnumHelper.addToolMaterial(DRAGONGLASSMATERIAL, 4, 3122, 32.0F, 4.0F, 44);
  dragonglassIngot = new ItemDragonglassIngot().setCreativeTab(Norsecraft.creativeTab);
  dragonglassSword = new ItemDragonglassSword().setCreativeTab(Norsecraft.creativeTab);
  dragonglassPickaxe = new ItemDragonglassPickaxe().setCreativeTab(Norsecraft.creativeTab);
  dragonglassAxe = new ItemDragonglassAxe().setCreativeTab(Norsecraft.creativeTab);
  dragonglassShovel = new ItemDragonglassShovel().setCreativeTab(Norsecraft.creativeTab);
  RecipesBlastFurnace.addRecipe(new ItemStack(steelIngot), new ItemStack(Blocks.OBSIDIAN), new ItemStack(dragonglassIngot));
}


@Override
public void preInitClient( FMLPreInitializationEvent event ) {
}
}

