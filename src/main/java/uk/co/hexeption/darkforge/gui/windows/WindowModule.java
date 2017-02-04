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

package uk.co.hexeption.darkforge.gui.windows;

import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.components.Button;
import uk.co.hexeption.darkforge.gui.components.Radar;
import uk.co.hexeption.darkforge.gui.components.Scrollbar;
import uk.co.hexeption.darkforge.gui.skin.SkinDarkForge;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class WindowModule extends Window {

    private final Module.Category category;

    public WindowModule(final Module.Category category, final int x, final int y) {

        super(category.name(), new SkinDarkForge(), x, y, 0, 0, true);
        this.category = category;
        addCompoentsLater();

    }

    @Override
    public void addComponents() {
    }

    private void addCompoentsLater() {

        for (final Module module : ModuleManager.getInstance().getModules()) {
            if (module.getCategory().equals(category)) {
                final Button button = new Button(module.getName(), module);
                button.setParent(this);
                addChild(button);
            }
        }
    }
}
