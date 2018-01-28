package kanethornwyrd.mods.norsecraft.modules.grandoak.worldGen;

import kanethornwyrd.mods.norsecraft.modules.grandoak.tree.Sapling;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenGrandOak extends WorldGenAbstractTree {
private Sapling sapling = new Sapling();

public WorldGenGrandOak() {
  super(false);
}

@Override
public boolean generate( World worldIn, Random rand, BlockPos position ) {
  sapling.generateSingleTree(false);
  return true;
}
}
