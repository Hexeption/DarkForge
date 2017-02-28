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

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.GuiUtils;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

/**
 * Created by Hexeption on 27/02/2017.
 */
@SideOnly(Side.CLIENT)
public class DarkForgeFrame extends ComponentRenderer {

    public DarkForgeFrame(Theme theme) {

        super(ComponentType.FRAME, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Frame frame = (Frame) component;
        Dimension dimension = frame.getDimension();
        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isMaximized()) {
            isMaximized(frame, dimension, mouseX, mouseY);
        }

        drawRect(frame.getxPos(), frame.getyPos(), frame.getxPos() + dimension.width, frame.getyPos() + 20, new Color(54, 54, 54, 160));
        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isMaximizible()) {
            isMaximizible(frame, dimension, mouseX, mouseY);
        }

        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isPinnable()) {
            isPinnable(frame, dimension, mouseX, mouseY);
        }

        GLUtils.glColor(new Color(54, 54, 54));
        theme.fontRenderer.drawCenteredString(frame.getText(), frame.getxPos() + 30, MathUtils.getMiddle(frame.getyPos(), (int) (frame.getyPos() + 18)) - (theme.fontRenderer.getHeight() / 2), Color.WHITE.hashCode());
        GLUtils.glColor(new Color(54, 54, 54));
    }



    private void isPinnable(Frame frame, Dimension dimension, int mouseX, int mouseY) {

    }

    private void isMaximizible(Frame frame, Dimension dimension, int mouseX, int mouseY) {

    }

    private void isMaximized(Frame frame, Dimension dimension, int mouseX, int mouseY) {

    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

        super.doInteractions(component, mouseX, mouseY);
    }
}
