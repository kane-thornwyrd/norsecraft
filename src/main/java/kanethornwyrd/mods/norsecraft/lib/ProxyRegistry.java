package kanethornwyrd.mods.norsecraft.lib;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ProxyRegistry {
private static Multimap<Class<?>, IForgeRegistryEntry<?>> entries = MultimapBuilder.hashKeys().arrayListValues().build();
private static HashMap<Block, Item> temporaryItemBlockMap = new HashMap();

public ProxyRegistry() {
}

public static <T extends IForgeRegistryEntry<T>> void register( IForgeRegistryEntry<T> obj ) {
  entries.put(obj.getRegistryType(), obj);
  if (obj instanceof ItemBlock) {
    ItemBlock iblock = (ItemBlock) obj;
    Block block = iblock.getBlock();
    temporaryItemBlockMap.put(block, iblock);
  }
  
}

public static ItemStack newStack( Block block ) {
  return newStack((Block) block, 1);
}

public static ItemStack newStack( Block block, int size ) {
  return newStack((Block) block, size, 0);
}

public static ItemStack newStack( Block block, int size, int meta ) {
  return newStack(getItemMapping(block), size, meta);
}

public static ItemStack newStack( Item item, int size, int meta ) {
  return new ItemStack(item, size, meta);
}

private static Item getItemMapping( Block block ) {
  Item i = Item.getItemFromBlock(block);
  return (i == null || i == Item.getItemFromBlock(Blocks.AIR)) && temporaryItemBlockMap.containsKey(block) ? (Item) temporaryItemBlockMap.get(block) : i;
}

public static ItemStack newStack( Item item ) {
  return newStack((Item) item, 1);
}

public static ItemStack newStack( Item item, int size ) {
  return newStack((Item) item, size, 0);
}

@SubscribeEvent
public static void onRegistryEvent( RegistryEvent.Register event ) {
  Class<?> type = event.getRegistry().getRegistrySuperType();
  if (entries.containsKey(type)) {
    Collection<IForgeRegistryEntry<?>> ourEntries = entries.get(type);
    Iterator var3 = ourEntries.iterator();
    
    while (var3.hasNext()) {
      IForgeRegistryEntry<?> entry = (IForgeRegistryEntry) var3.next();
      event.getRegistry().register(entry);
    }
  }
  
}
}
