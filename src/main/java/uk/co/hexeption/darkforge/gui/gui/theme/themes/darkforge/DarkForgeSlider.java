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
import uk.co.hexeption.darkforge.gui.gui.elements.Slider;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

/**
 * Created by Hexeption on 28/02/2017.
 */
public class DarkForgeSlider extends ComponentRenderer {

    public DarkForgeSlider(Theme theme) {

        super(ComponentType.SLIDER, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Slider slider = (Slider) component;
        int width = (int) ((slider.getDimension().getWidth()) * slider.getPercent());
        GLUtils.glColor(new Color(128, 128, 128));
        theme.fontRenderer.drawString(slider.getText(), slider.getX() + 4, slider.getY() + 2, Color.white.hashCode());
        theme.fontRenderer.drawString(slider.getValue() + "", slider.getX() + slider.getDimension().width - theme.fontRenderer.getStringWidth(slider.getValue() + "") - 2, slider.getY() + 2, Color.white.hashCode());
        RenderUtils.drawRect(slider.getX(), slider.getY() + slider.getDimension().height / 2 + 3, slider.getX() + (width) + 1, (slider.getY() + slider.getDimension().height / 2) + 6, new Color(7, 23, 38));
        RenderUtils.drawRect(slider.getX(), slider.getY() + slider.getDimension().height / 2 + 3, slider.getX() + (width), (slider.getY() + slider.getDimension().height / 2) + 6, new Color(128, 128, 128));
    }
}
