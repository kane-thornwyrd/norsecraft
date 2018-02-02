package kanethornwyrd.mods.norsecraft.modules.core.lib;

import kanethornwyrd.mods.norsecraft.modules.metals.features.Dragonglass;
import kanethornwyrd.mods.norsecraft.modules.metals.features.Steel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModToolMaterial {

public static enum ModMaterials {
  STEEL(2, 500, 7.0F, 2.5F, 14, Steel.steelIngot),
  DRAGONGLASS(4, 3122, 16.0F, 4.0F, 44, Dragonglass.dragonglassIngot);
//  WOOD(0, 59, 2.0F, 0.0F, 15),
//  STONE(1, 131, 4.0F, 1.0F, 5),
//  IRON(2, 250, 6.0F, 2.0F, 14),
//  DIAMOND(3, 1561, 8.0F, 3.0F, 10),
//  GOLD(0, 32, 12.0F, 0.0F, 22);
  
  
  private final int harvestLevel;
  private final int maxUses;
  private final float efficiency;
  private final float attackDamage;
  private final int enchantability;
  private ItemStack repairMaterial;
  
  private ModMaterials ( int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability, Item repairItem ) {
    this.repairMaterial = ItemStack.EMPTY;
    this.harvestLevel = harvestLevel;
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.attackDamage = damageVsEntity;
    this.enchantability = enchantability;
    this.repairMaterial = new ItemStack(repairItem, 1, 32767);
  }
  
  public int getMaxUses() {
    return this.maxUses;
  }
  
  public float getEfficiency() {
    return this.efficiency;
  }
  
  public float getAttackDamage() {
    return this.attackDamage;
  }
  
  public int getHarvestLevel() {
    return this.harvestLevel;
  }
  
  public int getEnchantability() {
    return this.enchantability;
  }
  
  public ItemStack getRepairItemStack() {
    return this.repairMaterial;
  }
  
  public ModMaterials setRepairItem( ItemStack stack ) {
    if (!this.repairMaterial.isEmpty()) {
      throw new RuntimeException("Repair material has already been set");
    }
    this.repairMaterial = stack;
    return this;
  }
}
}