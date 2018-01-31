package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import com.elytradev.mirage.event.GatherLightsEvent;
import com.elytradev.mirage.lighting.ILightEventConsumer;
import com.elytradev.mirage.lighting.Light;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.tile.TileSimpleInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Random;

import static kanethornwyrd.mods.norsecraft.modules.metals.BlockNames.BLASTFURNACE;


@Optional.Interface(iface = "com.elytradev.mirage.lighting.ILightEventConsumer", modid = "mirage")
public class TileEntityBlastFurnace extends TileSimpleInventory implements ITickable, ILightEventConsumer {

public static final int INGREDIENT = 0;
public static final int FUEL = 1;
public static final int RESULT = 2;
public static final int ADDITIVE = 3;

private static final int[] SLOTS_TOP = new int[]{ 0 };
private static final int[] SLOTS_BOTTOM = new int[]{ 2, 1 };
private static final int[] SLOTS_SIDES = new int[]{ 1 };
IItemHandler handlerTop;
IItemHandler handlerBottom;
IItemHandler handlerSide;
private NonNullList<ItemStack> furnaceItemStacks;
private int furnaceBurnTime;
private int currentItemBurnTime;
private int cookTime;
private int totalCookTime;
private String furnaceCustomName;
private Random rand;

public TileEntityBlastFurnace() {
  this.furnaceItemStacks = NonNullList.withSize(5, ItemStack.EMPTY);
  this.handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
  this.handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
  this.handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
  this.rand = new Random();
}

public static void registerFixesFurnace( DataFixer fixer ) {
  fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityBlastFurnace.class, new String[]{ "Items" }));
}

@SideOnly(Side.CLIENT)
public static boolean isBurning( IInventory inventory ) {
  return inventory.getField(INGREDIENT) > 0;
}

public static boolean isItemFuel( ItemStack stack ) {
  return getItemBurnTime(stack) > 0;
}

public static int getItemBurnTime( ItemStack stack ) {
  if (stack.isEmpty()) {
    return 0;
  } else {
    Item item = stack.getItem();
    if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
      return 16000;
    } else if (item == Items.COAL) {
      return 1600;
    }
  }
  return 0;
}

@Optional.Method(modid = "mirage")
@Override
public void gatherLights( GatherLightsEvent evt ) {
  if(this.isBurning()) {
    evt.add(Light.builder()
                 .pos(this.getPos())
                 .color(1, 1, 0)
                 .radius(3)
                 .build());
  }
  
}

public boolean hasFuelEmpty() {
  return this.getStackInSlot(FUEL).isEmpty();
}

public ItemStack getStackInSlot( int index ) {
  return (ItemStack) this.furnaceItemStacks.get(index);
}

public ItemStack decrStackSize( int index, int count ) {
  return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
}

public ItemStack removeStackFromSlot( int index ) {
  return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
}

public void setInventorySlotContents( int index, ItemStack stack ) {
  ItemStack itemstack = (ItemStack) this.furnaceItemStacks.get(index);
  boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
  this.furnaceItemStacks.set(index, stack);
  if (stack.getCount() > this.getInventoryStackLimit()) {
    stack.setCount(this.getInventoryStackLimit());
  }
  
  if (index == 0 && !flag) {
    this.totalCookTime = this.getCookTime(stack);
    this.cookTime = 0;
    this.markDirty();
  }
  
}

public int getInventoryStackLimit() {
  return 64;
}

public boolean isUsableByPlayer( EntityPlayer player ) {
  if (this.world.getTileEntity(this.pos) != this) {
    return false;
  } else {
    return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
  }
}

public boolean isItemValidForSlot( int index, ItemStack stack ) {
  switch (index) {
    case INGREDIENT:
    case ADDITIVE:
      return true;
    case FUEL:
      return stack.getItem() == Items.COAL || stack.getItem() == Item.getItemFromBlock(Blocks.COAL_BLOCK);
    case RESULT:
      return false;
  }
  return false;
}

public boolean hasCustomName() {
  return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
}

public void openInventory( EntityPlayer player ) {
}

public void closeInventory( EntityPlayer player ) {
}

public int getField( int id ) {
  switch (id) {
    case 0:
      return this.furnaceBurnTime;
    case 1:
      return this.currentItemBurnTime;
    case 2:
      return this.cookTime;
    case 3:
      return this.totalCookTime;
    default:
      return 0;
  }
}

public void setField( int id, int value ) {
  switch (id) {
    case 0:
      this.furnaceBurnTime = value;
      break;
    case 1:
      this.currentItemBurnTime = value;
      break;
    case 2:
      this.cookTime = value;
      break;
    case 3:
      this.totalCookTime = value;
  }
  
}

public int getFieldCount() {
  return 4;
}

public void clear() {
  for (int i = 0; i < this.furnaceItemStacks.size(); i++) {
    this.furnaceItemStacks.set(i, ItemStack.EMPTY);
  }
}

public int getCookTime( ItemStack stack ) {
  return 2000;
}

public ItemStack getRecipeResult() {
  return RecipesBlastFurnace.getRecipeResult(new ItemStack[]{
    this.getStackInSlot(INGREDIENT), this.getStackInSlot(ADDITIVE) });
}

public String getName() {
  return hasCustomName() ? this.furnaceCustomName : "tile." + BLASTFURNACE;
}

public void setCustomName( String name ) {
  this.furnaceCustomName = name;
}

public NBTTagCompound writeToNBT( NBTTagCompound compound ) {
  super.writeToNBT(compound);
  compound.setInteger("BurnTime", (short) this.furnaceBurnTime);
  compound.setInteger("CookTime", (short) this.cookTime);
  compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
  ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
  if (this.hasCustomName()) {
    compound.setString("CustomName", this.furnaceCustomName);
  }
  
  return compound;
}

public void readFromNBT( NBTTagCompound compound ) {
  super.readFromNBT(compound);
  this.furnaceItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
  ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
  this.furnaceBurnTime = compound.getInteger("BurnTime");
  this.cookTime = compound.getInteger("CookTime");
  this.totalCookTime = compound.getInteger("CookTimeTotal");
  this.currentItemBurnTime = getItemBurnTime((ItemStack) this.furnaceItemStacks.get(1));
  if (compound.hasKey("CustomName", 8)) {
    this.furnaceCustomName = compound.getString("CustomName");
  }
  
}

public int getSizeInventory() {
  return 64;
}

public boolean isEmpty() {
  for (ItemStack stack : this.furnaceItemStacks) {
    if (!stack.isEmpty()) {
      return false;
    }
  }
  return true;
}

@Override
public void update() {
  boolean flag = this.isBurning();
  boolean flag1 = false;
  if (this.isBurning()) {
    --this.furnaceBurnTime;
  }
  
  if (!this.world.isRemote) {
    ItemStack fuel = (ItemStack) this.furnaceItemStacks.get(FUEL);
    if (this.isBurning() || !fuel.isEmpty() && !((ItemStack) this.furnaceItemStacks.get(INGREDIENT)).isEmpty()) {
      if (!this.isBurning() && this.canSmelt()) {
        this.furnaceBurnTime = getItemBurnTime(fuel);
        this.currentItemBurnTime = this.furnaceBurnTime;
        if (this.isBurning()) {
          flag1 = true;
          if (!fuel.isEmpty()) {
            Item item = fuel.getItem();
            fuel.shrink(1);
            if (fuel.isEmpty()) {
              ItemStack item1 = item.getContainerItem(fuel);
              this.furnaceItemStacks.set(1, item1);
            }
          }
        }
      }
      
      if (this.isBurning() && this.canSmelt()) {
        ++this.cookTime;
        if (this.cookTime == this.totalCookTime) {
          this.cookTime = 0;
          this.totalCookTime = this.getCookTime((ItemStack) this.furnaceItemStacks.get(INGREDIENT));
          this.smelt();
          flag1 = true;
        }
      } else {
        this.cookTime = 0;
      }
    } else if (!this.isBurning() && this.cookTime > 0) {
      this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
    }
    
    if (flag != this.isBurning()) {
      flag1 = true;
      
      BlockPos pos = this.getPos();
      double d0 = (double) pos.getX() + 0.5D;
      double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
      double d2 = (double) pos.getZ() + 0.5D;
      double d4 = rand.nextDouble() * 0.6D - 0.3D;

//      if (rand.nextDouble() < 0.1D) {
      world.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
//      }
      world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + d4, d2, 0.0D, 0.5D, 0.0D, new int[ 0 ]);
      world.spawnParticle(EnumParticleTypes.FLAME, d0, d1 + d4, d2, 0.0D, 0.5D, 0.0D, new int[ 0 ]);
//      BlockFurnace.setState(this.isBurning(), this.world, this.pos);
    }
  }
  
  if (flag1) {
    this.markDirty();
  }
  
}


public boolean isBurning() {
  return this.furnaceBurnTime > 0;
}

private boolean canSmelt() {
  ItemStack result = this.getRecipeResult();
  if (result != null) {
    ItemStack slotResult = this.getStackInSlot(RESULT);
    if (slotResult.isEmpty())
      return true;
    if (slotResult.getItem() == result.getItem() && slotResult.getItemDamage() == result.getItemDamage()) {
      int newStackSize = slotResult.getCount() + result.getCount();
      if (newStackSize <= this.getInventoryStackLimit() && newStackSize <= slotResult.getMaxStackSize()) {
        return true;
      }
    }
  }
  return false;
}

public void smelt() {
  ItemStack result = this.getRecipeResult();
  this.decrStackSize(INGREDIENT, 1);
  this.decrStackSize(ADDITIVE, 1);
  ItemStack stack4 = this.getStackInSlot(RESULT);
  if (stack4.isEmpty()) {
    this.setInventorySlotContents(RESULT, result.copy());
  } else {
    stack4.setCount(stack4.getCount() + result.getCount());
  }
}

public Container createContainer( InventoryPlayer playerInventory, EntityPlayer playerIn ) {
  return null;
}

@Nullable
public <T> T getCapability( Capability<T> capability, @Nullable EnumFacing facing ) {
  if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
    if (facing == EnumFacing.DOWN) {
      return (T) this.handlerBottom;
    } else {
      return (T) (facing == EnumFacing.UP ? this.handlerTop : this.handlerSide);
    }
  } else {
    return super.getCapability(capability, facing);
  }
}

}
