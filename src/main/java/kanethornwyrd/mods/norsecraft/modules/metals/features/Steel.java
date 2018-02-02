package kanethornwyrd.mods.norsecraft.modules.metals.features;


import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace.RecipesBlastFurnace;

import kanethornwyrd.mods.norsecraft.modules.metals.steel.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static kanethornwyrd.mods.norsecraft.modules.metals.ItemNames.STEELMATERIAL;

public class Steel extends Feature {


public static Item steelIngot;
public static Item steelSword;
public static Item steelPickaxe;
public static Item steelAxe;
public static Item steelShovel;
public static Item.ToolMaterial steelMaterial;

@Override
public void preInit( FMLPreInitializationEvent event ) {
  steelMaterial = EnumHelper.addToolMaterial(STEELMATERIAL, 2, 500, 7.0F, 2.5F, 14);
  steelIngot = new ItemSteelIngot().setCreativeTab(Norsecraft.creativeTab);
  steelSword = new ItemSteelSword().setCreativeTab(Norsecraft.creativeTab);
  steelPickaxe = new ItemSteelPickaxe().setCreativeTab(Norsecraft.creativeTab);
  steelAxe = new ItemSteelAxe().setCreativeTab(Norsecraft.creativeTab);
  steelShovel = new ItemSteelShovel().setCreativeTab(Norsecraft.creativeTab);
  RecipesBlastFurnace.addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(steelIngot));
}


@Override
public void preInitClient( FMLPreInitializationEvent event ) {
}
}

