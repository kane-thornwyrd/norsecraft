package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ContainerBlastFurnace extends Container {
public static final int INGREDIENT = 0;
public static final int FUEL = 1;
public static final int RESULT = 2;
public static final int ADDITIVE = 3;
private TileEntityBlastFurnace tile;
private int cookTime = 0;
private int totalCookTime = 0;
private int furnaceBurnTime = 0;
private int currentItemBurnTime = 0;

public ContainerBlastFurnace( TileEntityBlastFurnace tile, InventoryPlayer playerInv) {
  this.tile = tile;
  this.addSlotToContainer(new Slot(tile, INGREDIENT, 56, 17));
  this.addSlotToContainer(new Slot(tile, ADDITIVE, 83, 17));
  this.addSlotToContainer(new Slot(tile, FUEL, 56, 53));
  this.addSlotToContainer(new SlotOutput(tile, RESULT, 116, 35));
  
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 9; j++) {
      addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
    }
  }
  
  for (int k = 0; k < 9; k++) {
    addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
  }
}

public void addListener( IContainerListener listener ) {
  super.addListener(listener);
  listener.sendAllWindowProperties(this, this.tile);
}

public void detectAndSendChanges() {
  super.detectAndSendChanges();
  
  for (int i = 0; i < this.listeners.size(); ++i) {
    IContainerListener icontainerlistener = (IContainerListener) this.listeners.get(i);
    if (this.cookTime != this.tile.getField(2)) {
      icontainerlistener.sendWindowProperty(this, 2, this.tile.getField(2));
    }
    
    if (this.furnaceBurnTime != this.tile.getField(0)) {
      icontainerlistener.sendWindowProperty(this, 0, this.tile.getField(0));
    }
    
    if (this.currentItemBurnTime != this.tile.getField(1)) {
      icontainerlistener.sendWindowProperty(this, 1, this.tile.getField(1));
    }
    
    if (this.totalCookTime != this.tile.getField(3)) {
      icontainerlistener.sendWindowProperty(this, 3, this.tile.getField(3));
    }
  }
  
  this.cookTime = this.tile.getField(2);
  this.furnaceBurnTime = this.tile.getField(0);
  this.currentItemBurnTime = this.tile.getField(1);
  this.totalCookTime = this.tile.getField(3);
}

@Override
public ItemStack transferStackInSlot( EntityPlayer player, int index ) {
  ItemStack itemstack = ItemStack.EMPTY;
  Slot slot = inventorySlots.get(index);
  
  if (slot != null && slot.getHasStack()) {
    ItemStack itemstack1 = slot.getStack();
    itemstack = itemstack1.copy();
    
    int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
    
    if (index < containerSlots) {
      if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
        return ItemStack.EMPTY;
      }
    } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
      return ItemStack.EMPTY;
    }
    
    if (itemstack1.getCount() == 0) {
      slot.putStack(ItemStack.EMPTY);
    } else {
      slot.onSlotChanged();
    }
    
    if (itemstack1.getCount() == itemstack.getCount()) {
      return ItemStack.EMPTY;
    }
    
    slot.onTake(player, itemstack1);
  }
  
  return itemstack;
}

@SideOnly(Side.CLIENT)
public void updateProgressBar( int id, int data ) {
  this.tile.setField(id, data);
}

public boolean canInteractWith( EntityPlayer playerIn ) {
  return this.tile.isUsableByPlayer(playerIn);
}

}
