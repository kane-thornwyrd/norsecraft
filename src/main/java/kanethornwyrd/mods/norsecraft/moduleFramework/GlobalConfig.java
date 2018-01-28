package kanethornwyrd.mods.norsecraft.moduleFramework;

import net.minecraftforge.common.config.Configuration;

public class GlobalConfig {
    public static boolean enableAntiOverlap;
    public static boolean enableConfigCommand;

    public static void initGlobalConfig() {
        String category = "_global";

        ConfigHelper.needsRestart = ConfigHelper.allNeedRestart = true;

        enableAntiOverlap = ConfigHelper.loadPropBool("Enable Anti-Overlap", category,
                "Set this to false to remove the system that has features turn themselves off automatically when"
                        + "other mods are present that add similar features."
                        + "\nNote that you can force features to be enabled individually through their respective configs.", true);

        ConfigHelper.needsRestart = ConfigHelper.allNeedRestart = false;
    }

    public static void changeConfig(String moduleName, String category, String key, String value, boolean saveToFile) {
        if (!enableConfigCommand)
            return;

        Configuration config = ModuleLoader.config;
        String fullCategory = moduleName;
        if (!category.equals("-"))
            fullCategory += "." + category;

        char type = key.charAt(0);
        key = key.substring(2);

        if (config.hasKey(fullCategory, key)) {
            boolean changed = false;

            try {
                switch (type) {
                    case 'B':
                        boolean b = Boolean.parseBoolean(value);
                        config.get(fullCategory, key, false).setValue(b);
                    case 'I':
                        int i = Integer.parseInt(value);
                        config.get(fullCategory, key, 0).setValue(i);
                    case 'D':
                        double d = Double.parseDouble(value);
                        config.get(fullCategory, key, 0.0).setValue(d);
                    case 'S':
                        config.get(fullCategory, key, "").setValue(value);
                }
            } catch (IllegalArgumentException e) {
            }

            if (config.hasChanged()) {
                ModuleLoader.forEachModule(module -> module.setupConfig());

                if (saveToFile)
                    config.save();
            }
        }
    }
}

