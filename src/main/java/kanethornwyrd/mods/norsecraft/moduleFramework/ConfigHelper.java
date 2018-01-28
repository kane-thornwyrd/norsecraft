package kanethornwyrd.mods.norsecraft.moduleFramework;


import net.minecraftforge.common.config.Property;

public class ConfigHelper {

    public static boolean needsRestart;
    public static boolean allNeedRestart = false;

    public static int loadPropInt(String propName, String category, String desc, int default_) {
        Property prop = ModuleLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        return prop.getInt(default_);
    }

    public static double loadPropDouble(String propName, String category, String desc, double default_) {
        Property prop = ModuleLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        return prop.getDouble(default_);
    }

    public static boolean loadPropBool(String propName, String category, String desc, boolean default_) {
        Property prop = ModuleLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        return prop.getBoolean(default_);
    }

    public static String loadPropString(String propName, String category, String desc, String default_) {
        Property prop = ModuleLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        return prop.getString();
    }

    public static String[] loadPropStringList(String propName, String category, String desc, String[] default_) {
        Property prop = ModuleLoader.config.get(category, propName, default_);
        prop.setComment(desc);
        setNeedsRestart(prop);

        return prop.getStringList();
    }

    private static void setNeedsRestart(Property prop) {
        if (needsRestart)
            prop.setRequiresMcRestart(needsRestart);
        needsRestart = allNeedRestart;
    }
}
