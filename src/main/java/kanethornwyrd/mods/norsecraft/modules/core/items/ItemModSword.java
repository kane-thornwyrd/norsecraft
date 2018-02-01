package kanethornwyrd.mods.norsecraft.modules.core.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import kanethornwyrd.mods.norsecraft.lib.TooltipHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;

public abstract class ItemModSword extends ItemMod {
protected final ToolMaterial material;
protected float attackDamage;

public ItemModSword( ToolMaterial material, String name, String... variants ) {
  super(name, variants);
  this.material = material;
  this.maxStackSize = 1;
  this.setMaxDamage(material.getMaxUses());
  this.attackDamage = 3.0F + material.getAttackDamage();
}

@SideOnly(Side.CLIENT)
public static void tooltipIfShift( List<String> tooltip, Runnable r ) {
  TooltipHandler.tooltipIfShift(tooltip, r);
}

@SideOnly(Side.CLIENT)
public static void addToTooltip( List<String> tooltip, String s, Object... format ) {
  TooltipHandler.addToTooltip(tooltip, s, format);
}

@SideOnly(Side.CLIENT)
public static String local( String s ) {
  return TooltipHandler.local(s);
}

public String getModNamespace() {
  return MOD_ID;
}

public boolean isInCreativeTab( CreativeTabs tab ) {
  return Arrays.asList(this.getCreativeTabs()).contains(tab);
}

public ItemMeshDefinition getCustomMeshDefinition() {
  return null;
}

public float getAttackDamage() {
  return this.material.getAttackDamage();
}

public float getDestroySpeed( ItemStack stack, IBlockState state ) {
  Block block = state.getBlock();
  if (block == Blocks.WEB) {
    return 15.0F;
  } else {
    Material material = state.getMaterial();
    return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
  }
}

public boolean hitEntity( ItemStack stack, EntityLivingBase target, EntityLivingBase attacker ) {
  stack.damageItem(1, attacker);
  return true;
}

public boolean onBlockDestroyed( ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving ) {
  if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
    stack.damageItem(2, entityLiving);
  }
  
  return true;
}

public boolean canHarvestBlock( IBlockState blockIn ) {
  return blockIn.getBlock() == Blocks.WEB;
}

@SideOnly(Side.CLIENT)
public boolean isFull3D() {
  return true;
}

public int getItemEnchantability() {
  return this.material.getEnchantability();
}

public boolean getIsRepairable( ItemStack toRepair, ItemStack repair ) {
  ItemStack mat = this.material.getRepairItemStack();
  return !mat.isEmpty() && OreDictionary.itemMatches(mat, repair, false) ? true : super.getIsRepairable(toRepair, repair);
}

public Multimap<String, AttributeModifier> getItemAttributeModifiers( EntityEquipmentSlot equipmentSlot ) {
  Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
  if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
    multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
    multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
  }
  
  return multimap;
}

@Override
public boolean canApplyAtEnchantingTable( ItemStack stack, Enchantment enchantment ) {
  return Sets.newHashSet(new String[]{
    "minecraft:binding_curse",
    "minecraft:sharpness",
    "minecraft:smite",
    "minecraft:bane_of_arthropods",
    "minecraft:knockback",
    "minecraft:fire_aspect",
    "minecraft:looting",
    "minecraft:unbreaking",
    "minecraft:mending"
  }).contains(Enchantment.REGISTRY.getNameForObject(enchantment).toString());
}

public String getToolMaterialName() {
  return this.material.toString();
}
}
