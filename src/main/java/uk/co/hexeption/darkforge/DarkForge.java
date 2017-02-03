package uk.co.hexeption.darkforge;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.api.APIModuleSetup;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.ModuleManager;
import uk.co.hexeption.darkforge.events.EventManager;
import uk.co.hexeption.darkforge.ttf.FontManager;

import java.io.File;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION_BUILD)
public class DarkForge {

    @Mod.Instance(ModInfo.MOD_ID)
    private static DarkForge instance;

    private EventManager eventManager;

    private FontManager fontManager = new FontManager();

    private final File darkForgeDir = new File(String.format("%s%sdarkforge%s", Minecraft.getMinecraft().mcDataDir, File.separator, File.separator));

    private String commandPrefix = "#";


    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {

        LogHelper.info(String.format("Starting up %s v%s", ModInfo.MOD_NAME, ModInfo.VERSION_BUILD));
        LogHelper.info(String.format("Running %s in Minecraft \"%s\", Forge \"%s\"", ModInfo.MOD_NAME, MinecraftForge.MC_VERSION, ForgeVersion.getVersion()));

        instance = new DarkForge();
        darkForgeInit();

    }

    private void darkForgeInit() {

        LogHelper.info("Loading Modules...");
        ModuleManager.getInstance();
        LogHelper.info("Registering Forge Events");
        eventManager = new EventManager();
        MinecraftForge.EVENT_BUS.register(eventManager);
        APIModuleSetup.setupModules();

        LogHelper.info("Loading config..");
        //TODO: Work on config
//        Config.getInstance();

    }

    @SideOnly(Side.CLIENT)
    public File getDarkForgeDir() {

        if (!darkForgeDir.exists()) {
            if (darkForgeDir.mkdirs()) {
                return darkForgeDir;
            }
        } else {
            LogHelper.fatal("Could not create the Darkforge data directory!");
            LogHelper.fatal("Shutting down...");
//            Minecraft.getMinecraft().shutdown();
        }
        return darkForgeDir;
    }

    public static DarkForge getInstance() {

        return instance;
    }

    public FontManager getFontManager() {

        return fontManager;
    }
}
