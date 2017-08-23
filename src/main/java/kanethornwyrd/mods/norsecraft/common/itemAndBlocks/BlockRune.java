package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.common.lib.LibBlockNames;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;


public class BlockRune extends BlockTileEntity<TileRune> {

    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

    public BlockRune() {
        super(Material.ROCK, LibBlockNames.RUNE);
        setHardness(20F);
        setResistance(5f);
        setSoundType(SoundType.STONE);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }


    @Override
    public BlockMod setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public IBlockState getStateFromMeta(int p_getStateFromMeta_1_) {
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
        int i = p_getStateFromMeta_1_ & 12;
        if (i == 4) {
            enumfacing$axis = EnumFacing.Axis.X;
        } else if (i == 8) {
            enumfacing$axis = EnumFacing.Axis.Z;
        }

        return this.getDefaultState().withProperty(AXIS, enumfacing$axis);
    }

    public int getMetaFromState(IBlockState p_getMetaFromState_1_) {
        int i = 0;
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) p_getMetaFromState_1_.getValue(AXIS);
        if (enumfacing$axis == EnumFacing.Axis.X) {
            i |= 4;
        } else if (enumfacing$axis == EnumFacing.Axis.Z) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AXIS});
    }

    protected ItemStack getSilkTouchDrop(IBlockState p_getSilkTouchDrop_1_) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    public IBlockState getStateForPlacement(World p_getStateForPlacement_1_, BlockPos p_getStateForPlacement_2_, EnumFacing p_getStateForPlacement_3_, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase p_getStateForPlacement_8_) {
        return super.getStateForPlacement(p_getStateForPlacement_1_, p_getStateForPlacement_2_, p_getStateForPlacement_3_, p_getStateForPlacement_4_, p_getStateForPlacement_5_, p_getStateForPlacement_6_, p_getStateForPlacement_7_, p_getStateForPlacement_8_).withProperty(AXIS, p_getStateForPlacement_3_.getAxis());
    }

    @Override
    public Class<TileRune> getTileEntityClass() {
        return TileRune.class;
    }

    @Nullable
    @Override
    public TileRune createTileEntity(World world, IBlockState state) {
        return new TileRune();
    }
}
