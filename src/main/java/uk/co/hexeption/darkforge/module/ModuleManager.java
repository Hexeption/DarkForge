package uk.co.hexeption.darkforge.module;

import uk.co.hexeption.darkforge.api.annotation.Enabled;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.modules.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class ModuleManager {

    private static ModuleManager instance;

    private final List<Module> modules;

    public ModuleManager() {

        modules = new LinkedList<Module>();
        initMods();
        LogHelper.info(String.format("Modules Loaded: %s!", modules.size()));
    }

    /**
     * TODO: Add Modules
     */
    private void initMods() {

        addModules(new Fly(), new BlockOverlay(), new Hud(), new Fullbright(), new Gui(), new BreadCrumbs(), new Tracers());
    }

    public void addModules(final Module... modules) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getClass().isAnnotationPresent(Enabled.class)) {
                    this.modules.add(module);
                    module.toggle();
                    continue;
                }
                this.modules.add(module);
            }
        }
    }

    /**
     * Returns a module by class
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getClass().equals(clazz)) {

                    return (T) module;
                }
            }
        }
        LogHelper.warn(String.format("Module %s not found by class, returning null!", clazz.getCanonicalName()));
        return null;
    }

    /**
     * Returns a module by a string name
     *
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends Module> T getModuleByName(final String name) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getName().replaceAll(" ", "").toLowerCase().equals(name.toLowerCase())) {
                    return (T) module;
                }
            }
        }

        LogHelper.warn(String.format("Module %s not found by class, returning null!", name));
        return null;
    }

    public List<Module> getModules() {

        synchronized (this.modules) {
            return this.modules;
        }
    }

    public static ModuleManager getInstance() {

        if (instance == null)
            instance = new ModuleManager();
        return instance;
    }

}
