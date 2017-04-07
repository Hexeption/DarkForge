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

import org.lwjgl.input.Mouse;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.gui.gui.elements.ComboBox;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 01/04/2017.
 */
public class DarkForgeComboBox extends ComponentRenderer {

    public DarkForgeComboBox(Theme theme) {

        super(ComponentType.COMBO_BOX, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        ComboBox comboBox = (ComboBox) component;
        Dimension area = comboBox.getDimension();

        glEnable(GL_BLEND);
        glDisable(GL_CULL_FACE);
        glDisable(GL_TEXTURE_2D);

        glTranslated(1 * comboBox.getX(), 1 * comboBox.getY(), 0);
        int maxWidth = 0;
        for (String element : comboBox.getElements())
            maxWidth = Math.max(maxWidth, theme.getFontRenderer().getStringWidth(element));
        int extendedHeight = 0;
        if (comboBox.isSelected()) {
            String[] elements = comboBox.getElements();
            for (int i = 0; i < elements.length - 1; i++)
                extendedHeight += theme.getFontRenderer().getHeight() + 2;
            extendedHeight += 2;
        }

        comboBox.setDimension(new Dimension(maxWidth + 8 + theme.getFontRenderer().getHeight(), theme.getFontRenderer().getHeight()));

        GLUtils.glColor(new Color(2, 2, 2, 40));
        glBegin(GL_QUADS);
        {
            glVertex2d(0, 0);
            glVertex2d(area.width, 0);
            glVertex2d(area.width, area.height + extendedHeight);
            glVertex2d(0, area.height + extendedHeight);
        }
        glEnd();
        Point mouse = new Point(mouseX, mouseY);
        glColor4f(0.0f, 0.0f, 0.0f, Mouse.isButtonDown(0) ? 0.5f : 0.3f);
        if (GLUtils.isHovered(comboBox.getX(), comboBox.getY(), area.width, area.height, mouseX, mouseY)) {
            glBegin(GL_QUADS);
            {
                glVertex2d(0, 0);
                glVertex2d(area.width, 0);
                glVertex2d(area.width, area.height);
                glVertex2d(0, area.height);
            }
            glEnd();
        } else if (comboBox.isSelected() && mouse.x >= comboBox.getX() && mouse.x <= comboBox.getX() + area.width) {
            int offset = area.height;
            String[] elements = comboBox.getElements();
            for (int i = 0; i < elements.length; i++) {
                if (i == comboBox.getSelectedIndex())
                    continue;
                int height = theme.getFontRenderer().getHeight() + 2;
                if ((comboBox.getSelectedIndex() == 0 ? i == 1 : i == 0) || (comboBox.getSelectedIndex() == elements.length - 1 ? i == elements.length - 2 : i == elements.length - 1))
                    height++;
                if (mouse.y >= comboBox.getY() + offset && mouse.y <= comboBox.getY() + offset + height) {
                    glBegin(GL_QUADS);
                    {
                        glVertex2d(0, offset);
                        glVertex2d(0, offset + height);
                        glVertex2d(area.width, offset + height);
                        glVertex2d(area.width, offset);
                    }
                    glEnd();
                    break;
                }
                offset += height;
            }
        }
        int height = theme.getFontRenderer().getHeight() + 4;
        glColor4f(0.0f, 0.0f, 0.0f, 0.3f);
        glBegin(GL_TRIANGLES);
        {
            if (comboBox.isSelected()) {
                glVertex2d(maxWidth + 4 + height / 2d, height / 3d);
                glVertex2d(maxWidth + 4 + height / 3d, 2d * height / 3d);
                glVertex2d(maxWidth + 4 + 2d * height / 3d, 2d * height / 3d);
            } else {
                glVertex2d(maxWidth + 4 + height / 3d, height / 3d);
                glVertex2d(maxWidth + 4 + 2d * height / 3d, height / 3d);
                glVertex2d(maxWidth + 4 + height / 2d, 2d * height / 3d);
            }
        }
        glEnd();
        glLineWidth(1.0f);
        glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        if (comboBox.isSelected()) {
            glBegin(GL_LINES);
            {
                glVertex2d(2, area.height);
                glVertex2d(area.width - 2, area.height);
            }
            glEnd();
        }
        glBegin(GL_LINES);
        {
            glVertex2d(maxWidth + 4, 2);
            glVertex2d(maxWidth + 4, area.height - 2);
        }
        glEnd();
        glBegin(GL_LINE_LOOP);
        {
            if (comboBox.isSelected()) {
                glVertex2d(maxWidth + 4 + height / 2d, height / 3d);
                glVertex2d(maxWidth + 4 + height / 3d, 2d * height / 3d);
                glVertex2d(maxWidth + 4 + 2d * height / 3d, 2d * height / 3d);
            } else {
                glVertex2d(maxWidth + 4 + height / 3d, height / 3d);
                glVertex2d(maxWidth + 4 + 2d * height / 3d, height / 3d);
                glVertex2d(maxWidth + 4 + height / 2d, 2d * height / 3d);
            }
        }
        glEnd();
        glEnable(GL_TEXTURE_2D);

        String text = comboBox.getSelectedElement();
        theme.getFontRenderer().drawString(text, 2, area.height / 2 - theme.getFontRenderer().getHeight() / 4, -1);
        if (comboBox.isSelected()) {
            int offset = area.height + 2;
            String[] elements = comboBox.getElements();
            for (int i = 0; i < elements.length; i++) {
                if (i == comboBox.getSelectedIndex())
                    continue;
                theme.getFontRenderer().drawString(elements[i], 2, offset, -1, 100);
                offset += theme.getFontRenderer().getHeight() + 2;
            }
        }

        glEnable(GL_CULL_FACE);
        glDisable(GL_BLEND);
        glTranslated(-1 * comboBox.getX(), -1 * comboBox.getY(), 0);
    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

        ComboBox comboBox = (ComboBox) component;

        LogHelper.info("TEST");

    }
}
