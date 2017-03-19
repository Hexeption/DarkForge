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
import uk.co.hexeption.darkforge.utils.render.GLUtils;

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

    public void drawExpanded(int x, int y, int size, boolean expanded, int color) {

        GLUtils.glColor(color);
        theme.icons.render(x, y, size, size, expanded ? 64F / 256F : 0F, 0F, expanded ? 128F / 256F : 64F / 256F, 64F / 256F);

    }

    public void drawPin(int x, int y, int size, boolean expanded, int color) {

        GLUtils.glColor(color);
        theme.icons.render(x, y, size, size, 64F / 256F, 128F / 256F, 128F / 256F, 64F / 256F);

    }

    public void drawArrow(int x, int y, int size, boolean right, int color) {

        GLUtils.glColor(color);
        theme.icons.render(x, y, size, size, 0, right ? 0 : 64F / 256F, 64F / 256F, right ? 64F / 256F : 128F / 256F);
    }

    public void drawArrow(int x, int y, int size, boolean right) {

        drawArrow(x, y, size, right, 0xFFFFFFFF);
    }


}
