package kanethornwyrd.mods.norsecraft.common.lib;

public class LibMisc {

    // Mod Constants
    public static final String MOD_ID = "norsecraft";
    public static final String MOD_NAME = "Norsecraft";
    public static final String BUILD = "GRADLE:BUILD";
    public static final String VERSION_ID = "GRADLE:VERSION";
    public static final String VERSION = VERSION_ID + "-" + BUILD;
    public static final String DEPENDENCIES = "required-after:baubles;after:forge@[14.21.1.2443,)";
    public static final String MC_VERSION = "[1.12,1.13)";


    // Network Contants
    public static final String NETWORK_CHANNEL = MOD_ID;

    // Proxy Constants
    public static final String PROXY_SERVER = "kanethornwyrd.mods.norsecraft.common.core.proxy.ServerProxy";
    public static final String PROXY_CLIENT = "kanethornwyrd.mods.norsecraft.client.core.proxy.ClientProxy";
    public static final String GUI_FACTORY = "kanethornwyrd.mods.norsecraft.client.core.proxy.GuiFactory";
}
