package kanethornwyrd.mods.norsecraft.modules.runes;

import kanethornwyrd.mods.norsecraft.Norsecraft;

import kanethornwyrd.mods.norsecraft.lib.ProxyRegistry;
import kanethornwyrd.mods.norsecraft.moduleFramework.Feature;
import kanethornwyrd.mods.norsecraft.moduleFramework.Module;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.ModBlock;
import kanethornwyrd.mods.norsecraft.modules.core.blocks.tile.TileMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;


import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Random;

public class Runes extends Module {
public static HashMap<String, Rune> RUNES = new HashMap<>();

public Runes() {
  addRune(new Rune(
    "fehu", "f", "Wealth", 1, 1600015
  ));
  addRune(new Rune(
    "uruz", "u", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "thurisaz", "th", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "ansuz", "a", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "raidho", "r", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "kaunan", "k", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "gebo", "g", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "wunjo", "w", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "hagalaz", "h", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "naudhiz", "n", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "isaz", "i", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "jera", "j", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "aihwaz", "ï", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "perth", "p", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "algiz", "z", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "sowilo", "s", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "tiwaz", "t", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "berkanan", "b", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "ehwaz", "e", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "mannaz", "m", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "laguz", "l", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "ingwaz", "ng", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "othalan", "o", "Wealth", 1, 16777215
  ));
  addRune(new Rune(
    "dagaz", "d", "Wealth", 1, 16777215
  ));
}


public void addRune( Rune rune ) {
  RUNES.put(rune.name.toUpperCase(), rune);
  registerFeature(rune.asFeature(), rune.name);
}

final public static String ucfirst( String subject ) {
  return Character.toUpperCase(subject.charAt(0)) + subject.substring(1);
}

private class Rune {
  public String name, phoneme, meaning;
  public Integer essencePerTick, color;
  public RuneBlock block;
  public RuneFeature feat;
  
  public Rune( String name, String phoneme, String meaning, Integer essencePerTick, int color ) {
    this.name = name;
    this.phoneme = phoneme;
    this.meaning = meaning;
    this.essencePerTick = essencePerTick;
    this.color = color;
    this.feat = new RuneFeature(name);
    this.block = this.feat.getBlock();
  }
  
  public Feature asFeature() {
    return this.feat;
  }
  
  public Block asBlock(){
    return this.block;
  }
  
  private class RuneBlock extends ModBlock {
    public RuneBlock( String name ) {
      super(name, Material.ROCK);
      Class<?> TE = null;
      try {
        TE = Class.forName("kanethornwyrd.mods.norsecraft.modules.runes.tiles." + ucfirst(name));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      GameRegistry.registerTileEntity(TE.asSubclass(TileMod.class), this.getPrefix() + name);
    }
    
    @Override
    public EnumRarity getBlockRarity( ItemStack stack ) {
      return EnumRarity.EPIC;
    }
  
    @Override
    public void randomDisplayTick( IBlockState stateIn, World worldIn, BlockPos pos, Random rand ) {
      EnumFacing enumfacing = EnumFacing.random(rand);
      if (enumfacing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isTopSolid()) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        if (enumfacing == EnumFacing.DOWN) {
          d1 -= 0.07D;
          d0 += rand.nextDouble();
          d2 += rand.nextDouble();
        } else {
          d1 += rand.nextDouble() * 0.8D;
          if (enumfacing.getAxis() == EnumFacing.Axis.X) {
            d2 += rand.nextDouble();
            if (enumfacing == EnumFacing.EAST) {
              d0 += 1.1D;
            } else {
              d0 += 0.1D;
            }
          } else {
            d0 += rand.nextDouble();
            if (enumfacing == EnumFacing.SOUTH) {
              d2 += 1.1D;
            } else {
              d2 += 0.1D;
            }
          }
        }
        worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
      }
    }
  }
  
  private class RuneFeature extends Feature {
    private RuneBlock block;
    private String name;
    public RuneFeature(String name) {
      this.name = name;
    }
    
    public RuneBlock getBlock(){
      return this.block;
    }
  
    @Override
    public void preInit( FMLPreInitializationEvent event ) {
      this.block = (RuneBlock) new RuneBlock(this.name).setCreativeTab(Norsecraft.creativeTab);
    }
  
    @Override
    public void init( FMLInitializationEvent event ) {
      OreDictionary.registerOre("oreRune", ProxyRegistry.newStack(this.block, 1, 0));
    }
  }
  
}
}
