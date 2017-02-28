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
import uk.co.hexeption.darkforge.gui.gui.elements.ExpandingButton;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

/**
 * Created by Hexeption on 28/02/2017.
 */
public class DarkForgeExpandingButton extends ComponentRenderer {

    public DarkForgeExpandingButton(Theme theme) {

        super(ComponentType.EXPANDING_BUTTON, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        ExpandingButton button = (ExpandingButton) component;
        String text = button.getText();
        Color color = new Color(31, 31, 31, 20);

        if (GLUtils.isHovered(button.getX(), button.getY(), button.getDimension().width, button.getDimension().height, mouseX, mouseY)) {
            color = new Color(31, 31, 31, 120);
        }

        RenderUtils.drawRect(button.getX(), button.getY(), button.getX() + button.getDimension().width - 1, button.getY() + button.getDimension().height, color);
        theme.fontRenderer.drawString(text, button.getX() + (button.getDimension().width / 2 - theme.fontRenderer.getStringWidth(text) / 2), button.getY() + (button.getButtonHeight() / 2 - theme.fontRenderer.getHeight() / 2), button.isEnabled() ? Color.green.hashCode() : Color.WHITE.hashCode());

        if (button.isMaximized()) {
            RenderUtils.drawRect(button.getX(), button.getY() + button.getButtonHeight() - 1, button.getX() + button.getDimension().width, button.getButtonHeight(), new Color(128, 128, 128, 55));
            RenderUtils.drawRect(button.getX(), button.getY() +  button.getDimension().height - 1, button.getX() + button.getDimension().width, button.getDimension().height, new Color(128, 128, 128, 55));
        }

        if(!button.isMaximized()){
            RenderUtils.drawTri(button.getX() + button.getDimension().width - 19 + 6, button.getY() + 6, MathUtils.getMiddleDouble(button.getX() + button.getDimension().width - 19, button.getX() + button.getDimension().width), button.getY() + 19 - 6, button.getX() + button.getDimension().width - 6, button.getY() + 6, 1.5, Color.WHITE);
        }else{
            RenderUtils.drawTri(button.getX() + button.getDimension().width - 19 + 6, button.getY() + 19 - 6, MathUtils.getMiddleDouble(button.getX() + button.getDimension().width - 19, button.getX() + button.getDimension().width), button.getY() + 6, button.getX() + button.getDimension().width - 6, button.getY() + 19 - 6, 1.5, Color.WHITE);
        }

        if (button.isMaximized()) {
            button.renderChildren(mouseX, mouseY);
        }
    }
}
