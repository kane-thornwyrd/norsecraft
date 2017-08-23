package kanethornwyrd.mods.norsecraft.plugins.JEI;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ModItems;
import kanethornwyrd.mods.norsecraft.common.lib.LibItemNames;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class NorsecraftJEI extends BlankModPlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        blacklist.addIngredientToBlacklist(new ItemStack(ModItems.items.get(LibItemNames.ICON)));
    }
}
