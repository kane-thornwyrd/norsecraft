package kanethornwyrd.mods.norsecraft.common.lib;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ColorHandler {
    private static final Minecraft minecraft = Minecraft.getMinecraft();

    public static void blockColourHandlerRegister(Block block) {

        BlockColors blockColors = minecraft.getBlockColors();

        final IBlockColor foliageColourHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (blockAccess != null && pos != null) {
                return BiomeColorHelper.getFoliageColorAtPos(blockAccess, pos);
            }

            return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
        };

        final IBlockColor grassColourHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (blockAccess != null && pos != null) {
                return BiomeColorHelper.getGrassColorAtPos(blockAccess, pos);
            }

            return ColorizerGrass.getGrassColor(0.5D, 1.0D);
        };

        if (block instanceof BlockLeaves) {
            blockColors.registerBlockColorHandler(foliageColourHandler, block);
        } else if (block instanceof BlockGrass) {
            blockColors.registerBlockColorHandler(grassColourHandler, block);
        }

    }

    public static void itemColourHandlerRegister(Item item) {

        BlockColors blockColors = minecraft.getBlockColors();
        final ItemColors itemColors = minecraft.getItemColors();

        final IItemColor itemBlockColourHandler = (stack, tintIndex) ->
        {
            IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
        };

        itemColors.registerItemColorHandler(itemBlockColourHandler, item);
    }
}