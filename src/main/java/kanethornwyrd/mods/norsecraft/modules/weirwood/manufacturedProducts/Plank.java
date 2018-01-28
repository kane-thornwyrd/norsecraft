package kanethornwyrd.mods.norsecraft.modules.weirwood.manufacturedProducts;

import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModBlock;
import kanethornwyrd.mods.norsecraft.modules.weirwood.lib.BlockNames;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class Plank extends ModBlock {
  public Plank(){
    super(BlockNames.WEIRWOODPLANK, Material.WOOD);
  }

  @Override
  public IProperty getVariantProp() {
    return null;
  }

  @Override
  public IProperty[] getIgnoredProperties() {
    return new IProperty[0];
  }

  @Override
  public EnumRarity getBlockRarity(ItemStack itemStack) {
    return EnumRarity.COMMON;
  }

  @Override
  public Class getVariantEnum() {
    return null;
  }
}
