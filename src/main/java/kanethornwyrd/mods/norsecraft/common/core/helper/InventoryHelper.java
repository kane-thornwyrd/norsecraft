package kanethornwyrd.mods.norsecraft.common.core.helper;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.tile.TileSimpleInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InventoryHelper {
    public static void dropInventory(TileSimpleInventory inv, World world, IBlockState state, BlockPos pos) {
        if(inv != null) {
            for(int j1 = 0; j1 < inv.getSizeInventory(); ++j1) {
                ItemStack itemstack = inv.getItemHandler().getStackInSlot(j1);

                if(itemstack != null) {
                    net.minecraft.inventory.InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
                }
            }

            world.updateComparatorOutputLevel(pos, state.getBlock());
        }
    }
}
