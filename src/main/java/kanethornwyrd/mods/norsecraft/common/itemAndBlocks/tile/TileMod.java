package kanethornwyrd.mods.norsecraft.common.itemAndBlocks.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public abstract class TileMod extends TileEntity implements ITickable {

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if(nbt == null) nbt = new NBTTagCompound();
        NBTTagCompound ret = super.writeToNBT(nbt);
        writePacketNBT(ret);
        return ret;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        readPacketNBT(nbt);
        super.readFromNBT(nbt);
        super.writeToNBT(nbt);
    }

    public abstract void writePacketNBT(NBTTagCompound cmp);

    public abstract void readPacketNBT(NBTTagCompound cmp);

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writePacketNBT(tag);
        return new SPacketUpdateTileEntity(pos, -999, tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readPacketNBT(packet.getNbtCompound());
    }
}
