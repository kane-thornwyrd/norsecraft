package kanethornwyrd.mods.norsecraft.modules.core.blocks;
import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;


import kanethornwyrd.mods.norsecraft.modules.core.items.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class BlockModStairs extends BlockStairs implements IModBlock {
private final String[] variants;
private final String bareName;

public BlockModStairs(String name, IBlockState state) {
  super(state);
  this.variants = new String[]{name};
  this.bareName = name;
  this.setUnlocalizedName(name);
  this.useNeighborBrightness = true;
}

public Block setUnlocalizedName(String name) {
  super.setUnlocalizedName(name);
  this.setRegistryName(this.getPrefix() + name);
  ProxyRegistry.register(this);
  ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(this.getPrefix() + name)));
  return this;
}

public String getBareName() {
  return this.bareName;
}

public String[] getVariants() {
  return this.variants;
}

public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

public EnumRarity getBlockRarity(ItemStack stack) {
  return EnumRarity.COMMON;
}

public IProperty[] getIgnoredProperties() {
  return new IProperty[0];
}

public IProperty getVariantProp() {
  return null;
}

public Class getVariantEnum() {
  return null;
}

public static void initStairs(Block base, int meta, BlockStairs block) {
}
}