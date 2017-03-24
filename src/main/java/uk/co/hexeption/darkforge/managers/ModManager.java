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
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.mod.mods.combat.Killaura;
import uk.co.hexeption.darkforge.mod.mods.gui.Gui;
import uk.co.hexeption.darkforge.mod.mods.misc.CustomChat;
import uk.co.hexeption.darkforge.mod.mods.misc.MiddleClickFriend;
import uk.co.hexeption.darkforge.mod.mods.misc.NameProtect;
import uk.co.hexeption.darkforge.mod.mods.movement.AutoSprint;
import uk.co.hexeption.darkforge.mod.mods.movement.Fly;
import uk.co.hexeption.darkforge.mod.mods.movement.InventoryMove;
import uk.co.hexeption.darkforge.mod.mods.movement.Step;
import uk.co.hexeption.darkforge.mod.mods.render.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class ModManager {

    private final List<Mod> mods = new ArrayList<>();

    public void Initialization() {

        initMods();
        LogHelper.info(String.format("Mods Loaded: %s!", mods.size()));
    }

    /**
     * TODO: Add Modules
     */
    private void initMods() {

        addModules(new Fly(), new BlockOverlay(), new Fullbright(), new BreadCrumbs(), new Tracers(),
                new ItemESP(), new ChestESP(), new Gui(), new AutoSprint(), new Step(), new Killaura(),
                new Xray(), new CustomChat(), new MiddleClickFriend(), new InventoryMove(), new NameProtect());
    }

    public void addModules(final Mod... mods) {

        for (final Mod mod : mods) {
            if (mod.getClass().isAnnotationPresent(Enabled.class)) {
                this.mods.add(mod);
                mod.toggle();
                continue;
            }
            this.mods.add(mod);
        }

    }

    /**
     * Returns a mod by class
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends Mod> T getModuleByClass(final Class<T> clazz) {

        for (final Mod mod : mods) {
            if (mod.getClass().equals(clazz)) {

                return clazz.cast(mod);
            }
        }

        LogHelper.warn(String.format("mod %s not found by class, returning null!", clazz.getCanonicalName()));
        return null;
    }

    /**
     * Returns a mod by a string name
     *
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends Mod> T getModuleByName(final String name) {

        for (final Mod mod : mods) {
            if (mod.getName().toLowerCase().equals(name.toLowerCase())) {
                return (T) mod;
            }
        }


        LogHelper.warn(String.format("mod %s not found by class, returning null!", name));
        return null;
    }

    public List<Mod> getMods() {

        synchronized (this.mods) {
            return this.mods;
        }
    }


}
