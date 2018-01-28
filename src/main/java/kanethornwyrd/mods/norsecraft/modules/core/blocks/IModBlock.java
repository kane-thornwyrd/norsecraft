package kanethornwyrd.mods.norsecraft.modules.core.blocks;

import kanethornwyrd.mods.norsecraft.interf.IStateMapperProvider;
import kanethornwyrd.mods.norsecraft.interf.IVariantEnumHolder;
import kanethornwyrd.mods.norsecraft.interf.IVariantHolder;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;

public interface IModBlock extends IVariantHolder, IVariantEnumHolder, IStateMapperProvider {
String getBareName();

IProperty getVariantProp();

IProperty[] getIgnoredProperties();

EnumRarity getBlockRarity( ItemStack var1 );

default boolean shouldDisplayVariant( int variant ) {
  return true;
}

@SideOnly(Side.CLIENT)
default IStateMapper getStateMapper() {
  return null;
}

@Override
default String getModNamespace() {
  return MOD_ID;
}
}