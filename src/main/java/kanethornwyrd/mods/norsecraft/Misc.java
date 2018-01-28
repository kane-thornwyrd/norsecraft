package kanethornwyrd.mods.norsecraft;

public class Misc {

// Mod Constants
public static final String MOD_ID = "norsecraft";
public static final String MOD_NAME = "Norsecraft";
public static final String BUILD = "GRADLE:BUILD";
public static final String VERSION_ID = "GRADLE:VERSION";
public static final String VERSION = VERSION_ID + "-" + BUILD;
public static final String PREFIX_MOD = MOD_ID + ":";
public static final String DEPENDENCIES = "after:notenoughitems;after:forge@[14.23.1.2604,)";
public static final String MC_VERSION = "[1.12,1.13)";


// Network Contants
public static final String NETWORK_CHANNEL = MOD_ID;

// Proxy Constants
public static final String PROXY_SERVER = "kanethornwyrd.mods.norsecraft.proxies.CommonProxy";
public static final String PROXY_CLIENT = "kanethornwyrd.mods.norsecraft.proxies.ClientProxy";
public static final String GUI_FACTORY = "kanethornwyrd.mods.norsecraft.client.GuiFactory";
}
