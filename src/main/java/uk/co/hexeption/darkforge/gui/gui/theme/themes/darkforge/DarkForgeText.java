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

package uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge;

import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.gui.gui.elements.Text;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;

/**
 * Created by Hexeption on 04/03/2017.
 */
public class DarkForgeText extends ComponentRenderer {

    public DarkForgeText(Theme theme) {

        super(ComponentType.TEXT, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Text text = (Text) component;
        String[] message = text.getMessage();

        int y = text.getY();

        for (String s : message) {
            theme.fontRenderer.drawString(s, text.getX() + 5, y + 5, -1);
            y += 10;
        }
    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

    }
}
