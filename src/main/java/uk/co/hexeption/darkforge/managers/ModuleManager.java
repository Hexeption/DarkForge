/*******************************************************************************
 *     DarkForge a Forge Hacked Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package uk.co.hexeption.darkforge.managers;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.api.annotation.Enabled;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.modules.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class ModuleManager {

    private final List<Module> modules = new LinkedList<Module>();

    public void Initialization() {

        initMods();
        LogHelper.info(String.format("Modules Loaded: %s!", modules.size()));
    }

    /**
     * TODO: Add Modules
     */
    private void initMods() {

        addModules(new Fly(), new BlockOverlay(), new Fullbright(), new BreadCrumbs(), new Tracers(), new ItemESP(), new ChestESP(), new Gui(), new AutoSprint(), new Step());
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
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {

        synchronized (this.modules) {
            for (final Module module : modules) {
                if (module.getClass().equals(clazz)) {

                    return clazz.cast(module);
                }
            }
        }
        LogHelper.warn(String.format("module %s not found by class, returning null!", clazz.getCanonicalName()));
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

        LogHelper.warn(String.format("module %s not found by class, returning null!", name));
        return null;
    }

    public List<Module> getModules() {

        synchronized (this.modules) {
            return this.modules;
        }
    }


}
