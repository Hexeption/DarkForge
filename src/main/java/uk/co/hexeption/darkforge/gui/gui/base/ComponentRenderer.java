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

package uk.co.hexeption.darkforge.gui.gui.base;

import uk.co.hexeption.darkforge.gui.gui.theme.Theme;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class ComponentRenderer {

    public Theme theme;

    private ComponentType type;

    public ComponentRenderer(ComponentType type, Theme theme) {

        this.type = type;

        this.theme = theme;
    }

    public void drawComponent(Component component, int mouseX, int mouseY) {

    }

    public void doInteractions(Component component, int mouseX, int mouseY) {

    }


}
