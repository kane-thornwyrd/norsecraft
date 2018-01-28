package kanethornwyrd.mods.norsecraft.modules.core.plugins.JEI;

import kanethornwyrd.mods.norsecraft.modules.core.IconFeature;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class NorsecraftCoreJEI extends BlankModPlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
//        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
//        blacklist.addIngredientToBlacklist(new ItemStack(IconFeature.icon));
    }
}