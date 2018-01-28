package kanethornwyrd.mods.norsecraft.client;

import kanethornwyrd.mods.norsecraft.moduleFramework.ModuleLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;

public class GuiFactory implements IModGuiFactory {

@Override
public void initialize( Minecraft minecraftInstance ) {
  // NO-OP
}

@Override
public boolean hasConfigGui() {
  return true;
}

@Override
@SideOnly(Side.CLIENT)
public GuiScreen createConfigGui( GuiScreen parentScreen ) {
  return new GuiNorsecraftScreen();
}

@Override
public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
  return null;
}

public Class<? extends GuiScreen> mainConfigGuiClass() {
  return GuiNorsecraftConfig.class;
}

public RuntimeOptionCategoryElement getHandlerFor( RuntimeOptionCategoryElement element ) {
  return null;
}

public static class GuiNorsecraftConfig extends GuiConfig {
  public GuiNorsecraftConfig( GuiScreen parentScreen ) {
    super(
      parentScreen,
      getAllElements(),
      MOD_ID,
      false,
      false,
      GuiConfig.getAbridgedConfigPath(ModuleLoader.config.toString())
         );
  }
  
  public static List<IConfigElement> getAllElements() {
    List<IConfigElement> list = new ArrayList();
    
    Set<String> categories = ModuleLoader.config.getCategoryNames();
    for (String s : categories)
      if (!s.contains("."))
        list.add(
          new DummyConfigElement.DummyCategoryElement(
            s,
            s,
            new ConfigElement(ModuleLoader.config.getCategory(s)).getChildElements()
          )
                );
    
    return list;
  }
}

public static class GuiNorsecraftScreen extends GuiScreen {
  public GuiNorsecraftScreen() {
  }
}

}
