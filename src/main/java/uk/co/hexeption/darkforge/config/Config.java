package uk.co.hexeption.darkforge.config;

import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.api.file.ConfigParser;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class Config {

    private static final File moduleFile = new File(String.format("%s%smodules.hexeption", DarkForge.getInstance().getDarkForgeDir(), File.separator));

    private static boolean initilized = false;

    private static final boolean FORCED_FILE_RECEATE = false;

    private static Config instance;

    private final ConfigParser configParser;

    public Config() {

        configParser = ConfigParser.getInstance(moduleFile);
    }

    public static Config getInstance() {

        if (!initilized) {
            LogHelper.info("Initializing configs");
            instance = new Config();
            initialize();
        }
        return instance;
    }

    private static void initialize() {

        if (!moduleFile.exists() || FORCED_FILE_RECEATE) {
            try {
                LogHelper.info("Creating module since it was not found");
                moduleFile.createNewFile();
                instance.saveModuleConfig();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        instance.loadModuleConfig();
        initilized = true;
    }

    private void saveModuleConfig() {

        recreate(moduleFile);
        final List<String> moduleInfo = new ArrayList<String>();
        createModuleFileComments(moduleInfo);
        for (final Module module : ModuleManager.getInstance().getModules()) {
            moduleInfo.add(String.format("%s%s%d%s%b\n", module.getName().replaceAll(" ", "").toLowerCase(), ";", module.getBind(), ";", module.getState()));
        }
        configParser.setupWrite();
        configParser.write(moduleInfo);
        configParser.closeStream();
    }

    private void loadModuleConfig() {

        configParser.setupRead();
        final List<String> configLines = configParser.read();
        configParser.closeStream();

        for (final String s : configLines) {
            final String[] args = s.split(";");
            final Module m = ModuleManager.getInstance().getModuleByName(args[0]);
            m.setBind(Integer.parseInt(args[1]));
            if (Boolean.parseBoolean(args[2])) {
                m.toggle();
            }
        }

    }

    private void createModuleFileComments(final List<String> moduleInfo) {

        moduleInfo.add("# Coded By Hexeption\n");
        moduleInfo.add("# Module File \n");
    }

    private void recreate(final File file) {

        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            LogHelper.info(String.format("Failed to recrate file '%s', this is bad XD", file.getName()));
            e.printStackTrace();
        }
    }
}
