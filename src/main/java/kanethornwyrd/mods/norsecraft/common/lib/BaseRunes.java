package kanethornwyrd.mods.norsecraft.common.lib;

import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.BlockRune;
import kanethornwyrd.mods.norsecraft.common.itemAndBlocks.ItemRune;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

public final class BaseRunes {
    public static HashMap<String, BaseRune> collection;
    public static BlockRune block;
    public static BaseRunes instance;

    public static void init(){
        if(null == BaseRunes.instance) {
            BaseRunes.instance = new BaseRunes();
        }
    }

    private BaseRunes(){
        BaseRunes.collection = new HashMap<>();

        addRune(new BaseRune(
                "fehu", "f", "Wealth", 1, 1600015
        ));
        addRune(new BaseRune(
                "uruz", "u", "Wealth", 1, 16777215
        ));
        addRune(new BaseRune(
                "thurisaz", "th", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "ansuz", "a", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "raidho", "r", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "kaunan", "k", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "gebo", "g", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "wunjo", "w", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "hagalaz", "h", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "naudhiz", "n", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "isaz", "i", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "jera", "j", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "aihwaz", "ï", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "p", "p", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "z", "z", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "sowilo", "s", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "tiwaz", "t", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "berkanan", "b", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "ehwaz", "e", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "mannaz", "m", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "laguz", "l", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "ingwaz", "ng", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "othalan", "o", "Wealth", 1, 16777215
        ));
        addRune( new BaseRune(
                "dagaz", "d", "Wealth", 1, 16777215
        ));
    }

    public static void addRune(BaseRune rune){
        BaseRunes.collection.put(rune.getName().toUpperCase(), rune);
    }

    public static final void registerBlocks(IForgeRegistry<Block> registry){
        BaseRunes.init();
        collection.forEach((name, rune) -> injectBlocks(name, rune, registry));
    }

    private static final void injectBlocks(String name, BaseRune rune, IForgeRegistry<Block> registry){
        if(null == block){
            block = new BlockRune(collection);
            block.setUnlocalizedName(name);
            registry.register(block);
        }
    }

    public class BaseRune {

        protected String name, phoneme, meaning;
        protected Integer essencePerTick, color;

        public BaseRune(String name, String phoneme, String meaning, Integer essencePerTick, int color) {
            setName(name);
            setPhoneme(phoneme);
            setMeaning(meaning);
            setEssencePerTick(essencePerTick);
            setColor(color);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneme() {
            return phoneme;
        }

        public void setPhoneme(String phoneme) {
            this.phoneme = phoneme;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public Integer getEssencePerTick() {
            return essencePerTick;
        }

        public void setEssencePerTick(Integer essencePerTick) {
            this.essencePerTick = essencePerTick;
        }

        public Integer getColor() {
            return color;
        }

        public void setColor(Integer color) {
            this.color = color;
        }

        public ItemRune asItem() {
            return new ItemRune(block, this);
        }
    }
}
