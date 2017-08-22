package kanethornwyrd.mods.norsecraft.client.core.handler;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistentVariableHelper {

    private static File cacheFile;
    public static String lastNorsecraftVersion = "";

    public static void save() throws IOException {
        NBTTagCompound cmp = new NBTTagCompound();

        injectNBTToFile(cmp, getCacheFile());
    }

    public static void load() throws IOException {
        NBTTagCompound cmp = getCacheCompound();
    }

    public static void saveSafe() {
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCacheFile(File f) {
        cacheFile = f;
    }

    private static File getCacheFile() throws IOException {
        if(!cacheFile.exists())
            cacheFile.createNewFile();

        return cacheFile;
    }

    private static NBTTagCompound getCacheCompound() throws IOException {
        return getCacheCompound(getCacheFile());
    }

    private static NBTTagCompound getCacheCompound(File cache) throws IOException {
        if(cache == null)
            throw new RuntimeException("No cache file!");

        try {
            return CompressedStreamTools.readCompressed(new FileInputStream(cache));
        } catch(IOException e) {
            NBTTagCompound cmp = new NBTTagCompound();
            CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(cache));
            return getCacheCompound(cache);
        }
    }

    private static void injectNBTToFile(NBTTagCompound cmp, File f) {
        try {
            CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
