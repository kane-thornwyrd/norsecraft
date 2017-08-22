package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.tile.TileRune;
import kanethornwyrd.mods.norsecraft.common.core.NorsecraftCreativeTab;
import kanethornwyrd.mods.norsecraft.common.lib.BaseRunes;
import kanethornwyrd.mods.norsecraft.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;


public class BlockRune extends BlockMod implements ITileEntityProvider {

    private HashMap<String, BaseRunes.BaseRune> runes;

    public BlockRune(HashMap<String, BaseRunes.BaseRune> runes) {
        super(Material.ROCK, LibBlockNames.RUNE);
        this.runes = runes;
        setHardness(20F);
        setSoundType(SoundType.STONE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileRune();
    }

    @Override
    public void getSubBlocks(CreativeTabs ct, NonNullList<ItemStack> is) {

    }
}
