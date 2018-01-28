package kanethornwyrd.mods.norsecraft.modules.weirwood.worldGen;

import kanethornwyrd.mods.norsecraft.modules.weirwood.tree.Sapling;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenWeirwood extends WorldGenAbstractTree {
private Sapling sapling = new Sapling();

public WorldGenWeirwood() {
  super(false);
}

@Override
public boolean generate( World worldIn, Random rand, BlockPos position ) {
  sapling.generateSingleTree(false);
  return true;
}
}
