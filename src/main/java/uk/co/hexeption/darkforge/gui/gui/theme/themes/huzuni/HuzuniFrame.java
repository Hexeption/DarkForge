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

package uk.co.hexeption.darkforge.gui.gui.theme.themes.huzuni;

import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class HuzuniFrame extends ComponentRenderer {

    public HuzuniFrame(Theme theme) {

        super(ComponentType.FRAME, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Frame frame = (Frame) component;
        Dimension dimension = frame.getDimension();
        GLUtils.glColor(new Color(8, 7, 5, 100));

        if (frame.isMaximized()) {
            isMaximized(frame, dimension, mouseX, mouseY);
        }

        RenderUtils.drawRect(frame.getX(), frame.getY(), frame.getX() + dimension.width, frame.getY() + 15, new Color(8, 7, 5, 240));


        if (frame.isMaximizible()) {
            isMaximizible(frame, dimension, mouseX, mouseY);
        }


        theme.fontRenderer.drawString(frame.getText(), frame.getX() + 5, MathUtils.getMiddle(frame.getY(), frame.getY() + 10) - (theme.fontRenderer.getHeight() / 10), Color.WHITE.hashCode());


    }

    private void isMaximizible(Frame frame, Dimension dimension, int mouseX, int mouseY) {

        Color color;

        if (mouseX >= frame.getX() + dimension.width - 19 && mouseY >= frame.getY() && mouseY <= frame.getY() + 19 && mouseX <= frame.getX() + dimension.width) {
            color = tooltipColor.brighter();
        } else {
            color = tooltipColor.darker();
        }

        if (frame.isMaximized()) {
            RenderUtils.drawRect(frame.getX() + dimension.width - 15, frame.getY() + 3, frame.getX() + dimension.width - 5, frame.getY() + 13, color);

//            drawExpanded(frame.getX() + dimension.width - 15, frame.getY() + 2, 12, true, new Color(255, 255, 255, 255).hashCode());
//            RenderUtils.drawVLine(frame.getX(), frame.getY() + 14, frame.getY() + dimension.height, new Color(255, 1, 0, 255).hashCode());
//            RenderUtils.drawVLine(frame.getX() + dimension.width, frame.getY() + 14, frame.getY() + dimension.height, new Color(8, 7, 5, 255).hashCode());
//            RenderUtils.drawHLine(frame.getX(), frame.getX() + dimension.width, frame.getY() + dimension.height, new Color(8, 7, 5, 255).hashCode());
        } else {
            RenderUtils.drawRect(frame.getX() + dimension.width - 15, frame.getY() + 3, frame.getX() + dimension.width - 5, frame.getY() + 13, color);
//            drawExpanded(frame.getX() + dimension.width - 15, frame.getY() + 2, 12, false, new Color(255, 255, 255, 255).hashCode());
        }
    }

    private void isMaximized(Frame frame, Dimension dimension, int mouseX, int mouseY) {

        for (Component component : frame.getComponents()) {
            component.setxPos(frame.getX());
        }

        RenderUtils.drawRect(frame.getX(), frame.getY() + frame.getFrameBoxHeight(), frame.getX() + dimension.width, frame.getY() + dimension.height, new Color(64, 64, 64, 240));
        float height = 5;
        float maxHeight = 0;
        height = dimension.height - 16;

        for (Component component : frame.getComponents()) {
            maxHeight += component.getDimension().height;
        }
        float barHeight = height * (height / maxHeight);
        double y = (frame.getDimension().getHeight() - 16 - barHeight) * ((double) frame.getScrollAmmount() / (double) frame.getMaxScroll());
        y += frame.getY() + 16;
        frame.renderChildren(mouseX, mouseY);

        if (!(barHeight >= height)) {
            RenderUtils.drawRect((int) (frame.getX() + dimension.getWidth() - 4), (int) y, (int) (frame.getX() + frame.getDimension().getWidth()), (int) (y + barHeight), tooltipColor);
        }

    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

        Frame frame = (Frame) component;
        Dimension area = frame.getDimension();

        if (mouseX >= frame.getX() + area.width - 16 && frame.isMaximizible() && mouseY >= frame.getY() && mouseY <= frame.getY() + 16 && mouseX <= frame.getX() + area.width) {
            frame.setMaximized(!frame.isMaximized());
        }

    }


}
