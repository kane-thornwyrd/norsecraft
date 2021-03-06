package kanethornwyrd.mods.norsecraft.modules.weirwood.features;

import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.modules.weirwood.tree.Sapling;
import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class WorldGen extends Feature {
double weirwoodPerChunk;

@Override
public void setupConfig() {
  weirwoodPerChunk = loadPropInt("Grand Oak count per chunk", "Must be an integer if above 1. If below 1, works as" +
                                                                " a chance to generate one per chunk.", 5);
}

@Override
public boolean hasTerrainSubscriptions() { return true; }

@SubscribeEvent
public void decorate( DecorateBiomeEvent.Decorate event ) {
  World world = event.getWorld();
  Biome biome = world.getBiome(event.getPos());
  Random rand = event.getRand();
  Sapling weirwoodSapling = new Sapling();
  
  if (
    (biome == Biomes.TAIGA ||
       biome == Biomes.TAIGA_HILLS ||
       biome == Biomes.COLD_TAIGA ||
       biome == Biomes.COLD_TAIGA_HILLS ||
       biome == Biomes.MUTATED_REDWOOD_TAIGA ||
       biome == Biomes.MUTATED_REDWOOD_TAIGA_HILLS ||
       biome == Biomes.MUTATED_TAIGA ||
       biome == Biomes.MUTATED_TAIGA_COLD ||
       biome == Biomes.REDWOOD_TAIGA ||
       biome == Biomes.REDWOOD_TAIGA_HILLS ||
       biome == Biomes.PLAINS ||
       biome == Biomes.FOREST ||
       biome == Biomes.MUTATED_PLAINS ||
       biome == Biomes.MUTATED_FOREST ||
       biome == Biomes.FOREST_HILLS ||
       biome == Biomes.ICE_PLAINS) &&
      event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE
    ) {
    if (weirwoodPerChunk < 1 && rand.nextDouble() > weirwoodPerChunk)
      return;
    
    int amount = (int) Math.max(1, weirwoodPerChunk);
    for (int i = 0; i < amount; i++) {
      weirwoodSapling.generateSingleTree(false).generate(world, rand, event.getPos());
    }
    
    Loader.instance().setActiveModContainer(null);
    return;
  }
  return;
}
}
