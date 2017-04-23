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
import uk.co.hexeption.darkforge.gui.gui.elements.CheckButton;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;

import java.awt.*;

/**
 * Created by Hexeption on 28/02/2017.
 */
public class DarkForgeCheckButton extends ComponentRenderer {

    public DarkForgeCheckButton(Theme theme) {

        super(ComponentType.CHECK_BUTTON, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        CheckButton button = (CheckButton) component;
        String text = button.getText();
        Color color = new Color(71, 71, 71);

        RenderUtils.drawRect(button.getX() + 4, button.getY() + 4, button.getX() + button.getDimension().height - 4, button.getY() + button.getDimension().height - 4, color);

        if (button.isEnabled()) {
            RenderUtils.drawTri(button.getX() + 6, button.getY() + 8, button.getX() + button.getDimension().height - 10, button.getY() + button.getDimension().height - 6, button.getX() + button.getDimension().height - 7, button.getY() + 6, 2, Color.white);
        }

        theme.fontRenderer.drawString(text, button.getX() + 18, MathUtils.getMiddle(button.getY(), button.getY() + button.getDimension().height) - theme.fontRenderer.getHeight() / 2 + 1, Color.white.hashCode());
    }
}
