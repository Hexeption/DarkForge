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

package uk.co.hexeption.darkforge.gui;

import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.windows.WindowModule;
import uk.co.hexeption.darkforge.gui.windows.WindowRadar;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class GuiModule extends GuiWindow {

    private int yOffset = 0;

    public GuiModule() {

        for (final Module.Category c : Module.Category.values()) {
            addAWindow(new WindowModule(c, 2, yOffset));
        }

        addAWindow(new WindowRadar(2, yOffset));

    }

    private void addAWindow(final Window window) {

        super.addWindow(window);
        yOffset += 15;
    }
}
