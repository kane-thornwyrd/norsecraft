package kanethornwyrd.mods.norsecraft.modules.weirwood.tree;

import kanethornwyrd.mods.norsecraft.lib.MapHelper;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModSapling;
import kanethornwyrd.mods.norsecraft.modules.core.lib.WorldGenRandomTree;
import kanethornwyrd.mods.norsecraft.modules.weirwood.features.Tree;
import kanethornwyrd.mods.norsecraft.modules.weirwood.lib.BlockNames;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class Sapling extends ModSapling {
public Sapling() {
  super(BlockNames.WEIRWOODSAPLING);
}

@Override
public WorldGenAbstractTree generateThreeByThreeTree( Boolean notify ) {
  return new WorldGenRandomTree(notify,
                                Tree.weirwoodLog,
                                Tree.weirwoodLeaf
  ).configure(MapHelper.<String, Float>builder()
                .put("girth", 3f)
                .put("minHeight", 2f)
                .put("maxHeight", 10f)
                .put("maxDistanceMag", 50f)
                .put("minDistanceMag", 0f)
                .put("cubage", 1000f)
                .unmodifiable(true)
                .build()
             );
}

@Override
public WorldGenAbstractTree generateTwoByTwoTree( Boolean notify ) {
  return new WorldGenRandomTree(notify,
                                Tree.weirwoodLog,
                                Tree.weirwoodLeaf
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
  
  return new WorldGenRandomTree(notify,
                                Tree.weirwoodLog,
                                Tree.weirwoodLeaf
  ).configure(MapHelper.<String, Float>builder()
                .put("girth", 1f)
                .put("minHeight", 5f)
                .put("maxHeight", 10f)
                .put("maxDistanceMag", 50f)
                .put("minDistanceMag", 0f)
                .put("cubage", 500f)
                .unmodifiable(true)
                .build()
             );
}
}
