package kanethornwyrd.mods.norsecraft.modules.grandoak.tree;

import kanethornwyrd.mods.norsecraft.lib.MapHelper;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModSapling;
import kanethornwyrd.mods.norsecraft.modules.core.lib.WorldGenSpaceColonizationTree;
import kanethornwyrd.mods.norsecraft.modules.grandoak.features.Tree;
import kanethornwyrd.mods.norsecraft.modules.grandoak.lib.BlockNames;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class Sapling extends ModSapling {
public Sapling() {
  super(BlockNames.GRANDOAKSAPLING);
}

@Override
public WorldGenAbstractTree generateThreeByThreeTree( Boolean notify ) {
  return new WorldGenSpaceColonizationTree(notify,
                                           Tree.grandOakLog,
                                           Tree.grandOakLeaf
  ).configure(MapHelper.<String, Float>builder()
                .put("girth", 3f)
                .put("minHeight", 5f)
                .put("maxHeight", 15f)
                .put("maxDistanceMag", 10000f)
                .put("minDistanceMag", 0f)
                .put("cubage", 500f)
                .unmodifiable(true)
                .build()
             );
}

@Override
public WorldGenAbstractTree generateTwoByTwoTree( Boolean notify ) {
  return new WorldGenSpaceColonizationTree(notify,
                                           Tree.grandOakLog,
                                           Tree.grandOakLeaf
  ).configure(MapHelper.<String, Float>builder()
                .put("girth", 2f)
                .put("minHeight", 5f)
                .put("maxHeight", 30f)
                .put("maxDistanceMag", 1000f)
                .put("minDistanceMag", 2f)
                .put("cubage", 500f)
                .unmodifiable(true)
                .build()
             );
}

@Override
public WorldGenAbstractTree generateSingleTree( Boolean notify ) {
  
  return new WorldGenSpaceColonizationTree(notify,
                                           Tree.grandOakLog,
                                           Tree.grandOakLeaf
  ).configure(MapHelper.<String, Float>builder()
                .put("girth", 1f)
                .put("minHeight", 5f)
                .put("maxHeight", 15f)
                .put("maxDistanceMag", 1000f)
                .put("minDistanceMag", 0f)
                .put("cubage", 300f)
                .unmodifiable(true)
                .build()
             );
}
}
