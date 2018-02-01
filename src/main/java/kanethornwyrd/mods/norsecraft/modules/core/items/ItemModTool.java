package kanethornwyrd.mods.norsecraft.modules.core.items;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Set;

public class ItemModTool extends ItemMod {

private final Set<Block> effectiveBlocks;
protected float efficiency;
protected float attackDamage;
protected float attackSpeed;
protected ToolMaterial toolMaterial;
@Nullable
private String toolClass;

public ItemModTool( float attackDamageIn, float attackSpeedIn, ToolMaterial material, Set<Block> effectiveBlocksIn, String name, String... variants ) {
  super(name, variants);
  this.efficiency = 4.0F;
  this.toolMaterial = material;
  this.effectiveBlocks = effectiveBlocksIn;
  this.maxStackSize = 1;
  this.setMaxDamage(material.getMaxUses());
  this.efficiency = material.getEfficiency();
  this.attackDamage = attackDamageIn + material.getAttackDamage();
  this.attackSpeed = attackSpeedIn;
  this.setCreativeTab(CreativeTabs.TOOLS);
  if (this instanceof ItemModPickaxe) {
    this.toolClass = "pickaxe";
  } else if (this instanceof ItemModAxe) {
    this.toolClass = "axe";
  } else if (this instanceof ItemModSpade) {
    this.toolClass = "shovel";
  }
}


public float getDestroySpeed( ItemStack stack, IBlockState state ) {
  Iterator var3 = this.getToolClasses(stack).iterator();
  
  String type;
  do
  {
    if (!var3.hasNext()) {
      return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiency : 1.0F;
    }
    
    type = (String) var3.next();
  }
  while (!state.getBlock().isToolEffective(type, state));
  
  return this.efficiency;
}

public boolean hitEntity( ItemStack stack, EntityLivingBase target, EntityLivingBase attacker ) {
  stack.damageItem(2, attacker);
  return true;
}

public boolean onBlockDestroyed( ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving ) {
  if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
    stack.damageItem(1, entityLiving);
  }
  
  return true;
}

@SideOnly(Side.CLIENT)
public boolean isFull3D() {
  return true;
}

public int getItemEnchantability() {
  return this.toolMaterial.getEnchantability();
}


public Multimap<String, AttributeModifier> getItemAttributeModifiers( EntityEquipmentSlot equipmentSlot ) {
  Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
  if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
    multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
    multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
  }
  
  return multimap;
}

public Set<String> getToolClasses( ItemStack stack ) {
  return (Set) (this.toolClass != null ? ImmutableSet.of(this.toolClass) : super.getToolClasses(stack));
}

public int getHarvestLevel( ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState ) {
  int level = super.getHarvestLevel(stack, toolClass, player, blockState);
  return level == -1 && toolClass.equals(this.toolClass) ? this.toolMaterial.getHarvestLevel() : level;
}


@Override
public boolean canApplyAtEnchantingTable( ItemStack stack, Enchantment enchantment ) {
  return Sets.newHashSet(new String[]{
    "minecraft:efficiency",
    "minecraft:silk_touch",
    "minecraft:fortune",
    "minecraft:unbreaking",
    "minecraft:mending"
  }).contains(Enchantment.REGISTRY.getNameForObject(enchantment).toString());
}

}