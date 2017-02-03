package uk.co.hexeption.darkforge.api;

import uk.co.hexeption.darkforge.module.Module;

import java.util.HashSet;

/**
 * Created by Hexeption on 15/01/2017.
 */
public final class APIModuleSetup {

    private static final HashSet<Module> setupModules = new HashSet<Module>();

    public static void addModuleToSetupQueue(final Module module) {

        synchronized (setupModules) {
            setupModules.add(module);
        }
    }

    public static void setupModules() {

        synchronized (setupModules) {
            for (final Module module : setupModules) {
                module.initializeLater();
            }
        }
    }

}
