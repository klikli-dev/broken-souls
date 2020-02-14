package tao.brokensouls;

import tao.brokensouls.commands.CommandDebug;
import tao.brokensouls.data.LostSoulData;
import tao.brokensouls.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;

@Mod(modid = BrokenSouls.MODID, name = "Broken Souls",
        dependencies =
                "required-after:lostcities@[" + BrokenSouls.LOSTCITY_VERSION + ",);" +
                        "required-after:compatlayer@[" + BrokenSouls.COMPATLAYER_VER + ",);" +
                        "after:Forge@[" + BrokenSouls.MIN_FORGE10_VER + ",);" +
                        "after:forge@[" + BrokenSouls.MIN_FORGE11_VER + ",)",
        version = BrokenSouls.VERSION,
        acceptedMinecraftVersions = "[1.10,1.12)",
        acceptableRemoteVersions = "*")
public class BrokenSouls {
    public static final String MODID = "brokensouls";
    public static final String VERSION = "1.0.0";
    public static final String LOSTCITY_VERSION = "1.0.2";
    public static final String MIN_FORGE10_VER = "12.18.1.2082";
    public static final String MIN_FORGE11_VER = "13.19.0.2176";
    public static final String COMPATLAYER_VER = "0.1.6";

    @SidedProxy(clientSide = "tao.brokensouls.proxy.ClientProxy", serverSide = "tao.brokensouls.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance("brokensouls")
    public static BrokenSouls instance;

    public static Logger logger;

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and
     * register them with the GameRegistry.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        this.proxy.preInit(e);
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDebug());
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        LostSoulData.clearInstance();
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
    }
}
