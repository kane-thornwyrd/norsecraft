package kanethornwyrd.mods.norsecraft.common.itemAndBlocks;

import kanethornwyrd.mods.norsecraft.Norsecraft;
import kanethornwyrd.mods.norsecraft.client.lib.LibResources;
import kanethornwyrd.mods.norsecraft.client.render.IModelRegister;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public abstract class ItemMod extends Item implements IModelRegister {

    public ItemMod(String name) {
        setUnlocalizedName(name);
    }

    @Nonnull
    @Override
    public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item\\.", "itemAndBlocks." + LibResources.PREFIX_MOD);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Norsecraft.logger.warn("========================================>" + new ModelResourceLocation(getRegistryName(), "inventory").toString());
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ItemMod setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
