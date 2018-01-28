package kanethornwyrd.mods.norsecraft.lib;

import kanethornwyrd.mods.norsecraft.interf.IBlockColorProvider;
import kanethornwyrd.mods.norsecraft.interf.IExtraVariantHolder;
import kanethornwyrd.mods.norsecraft.interf.IItemColorProvider;
import kanethornwyrd.mods.norsecraft.interf.IVariantHolder;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.IModBlock;
import kanethornwyrd.mods.norsecraft.modules.core.items.ItemMod;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Iterator;

public final class ModelHandler {
public static final HashMap<String, ModelResourceLocation> resourceLocations = new HashMap();

public ModelHandler() {
}

@SubscribeEvent
public static void onRegister( ModelRegistryEvent event ) {
  Iterator var1 = ItemMod.variantHolders.iterator();
  
  while (var1.hasNext()) {
    IVariantHolder holder = (IVariantHolder) var1.next();
    registerModels(holder);
  }
  
}

public static void registerModels( IVariantHolder holder ) {
  String unique = holder.getUniqueModel();
  String prefix = holder.getPrefix();
  Item i = (Item) holder;
  ItemMeshDefinition def = holder.getCustomMeshDefinition();
  if (def != null) {
    ModelLoader.setCustomMeshDefinition((Item) holder, def);
  } else {
    registerModels(i, prefix, holder.getVariants(), unique, false);
  }
  
  if (holder instanceof IExtraVariantHolder) {
    IExtraVariantHolder extra = (IExtraVariantHolder) holder;
    registerModels(i, prefix, extra.getExtraVariants(), unique, true);
  }
  
}

public static void registerModels( Item item, String prefix, String[] variants, String uniqueVariant, boolean extra ) {
  if (item instanceof ItemBlock && ((ItemBlock) item).getBlock() instanceof IModBlock) {
    IModBlock quarkBlock = (IModBlock) ((ItemBlock) item).getBlock();
    Class clazz = quarkBlock.getVariantEnum();
    IProperty variantProp = quarkBlock.getVariantProp();
    boolean ignoresVariant = false;
    IStateMapper mapper = quarkBlock.getStateMapper();
    IProperty[] ignored = quarkBlock.getIgnoredProperties();
    if (mapper != null || ignored != null && ignored.length > 0) {
      if (mapper != null) {
        ModelLoader.setCustomStateMapper((Block) quarkBlock, mapper);
      } else {
        Builder builder = new Builder();
        IProperty[] var12 = ignored;
        int var13 = ignored.length;
        
        for (int var14 = 0; var14 < var13; ++var14) {
          IProperty p = var12[ var14 ];
          if (p == variantProp) {
            ignoresVariant = true;
          }
          
          builder.ignore(new IProperty[]{ p });
        }
        
        ModelLoader.setCustomStateMapper((Block) quarkBlock, builder.build());
      }
    }
    
    if (clazz != null && !ignoresVariant) {
      registerVariantsDefaulted(item, (Block) quarkBlock, clazz, variantProp.getName());
      return;
    }
  }
  
  for (int i = 0; i < variants.length; ++i) {
    String var = variants[ i ];
    if (!extra && uniqueVariant != null) {
      var = uniqueVariant;
    }
    
    String name = prefix + var;
    ModelResourceLocation loc = new ModelResourceLocation(name, "inventory");
    if (!extra) {
      ModelLoader.setCustomModelResourceLocation(item, i, loc);
      resourceLocations.put(getKey(item, i), loc);
    } else {
      ModelBakery.registerItemVariants(item, new ResourceLocation[]{ loc });
      resourceLocations.put(variants[ i ], loc);
    }
  }
  
}

private static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted( Item item, Block b, Class<T> enumclazz, String variantHeader ) {
  String baseName = b.getRegistryName().toString();
  Enum[] var5 = (Enum[]) enumclazz.getEnumConstants();
  int var6 = var5.length;
  
  for (int var7 = 0; var7 < var6; ++var7) {
    T e = (T) var5[ var7 ];
    String variantName = variantHeader + "=" + ((IStringSerializable) e).getName();
    ModelResourceLocation loc = new ModelResourceLocation(baseName, variantName);
    int i = e.ordinal();
    ModelLoader.setCustomModelResourceLocation(item, i, loc);
    resourceLocations.put(getKey(item, i), loc);
  }
  
}

private static String getKey( Item item, int meta ) {
  return "i_" + item.getRegistryName() + "@" + meta;
}

public static void init() {
  ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
  BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
  Iterator var2 = ItemMod.variantHolders.iterator();
  
  while (var2.hasNext()) {
    IVariantHolder holder = (IVariantHolder) var2.next();
    if (holder instanceof IItemColorProvider) {
      itemColors.registerItemColorHandler(((IItemColorProvider) holder).getItemColor(), new Item[]{ (Item) holder });
    }
    
    if (holder instanceof ItemBlock && ((ItemBlock) holder).getBlock() instanceof IBlockColorProvider) {
      Block block = ((ItemBlock) holder).getBlock();
      blockColors.registerBlockColorHandler(((IBlockColorProvider) block).getBlockColor(), new Block[]{ block });
      itemColors.registerItemColorHandler(((IBlockColorProvider) block).getItemColor(), new Block[]{ block });
    }
  }
  
}

public static ModelResourceLocation getModelLocation( ItemStack stack ) {
  return !stack.isEmpty() ? null : getModelLocation(stack.getItem(), stack.getItemDamage());
}

public static ModelResourceLocation getModelLocation( Item item, int meta ) {
  String key = getKey(item, meta);
  return resourceLocations.containsKey(key) ? (ModelResourceLocation) resourceLocations.get(key) : null;
}
}