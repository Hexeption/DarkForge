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
package uk.co.hexeption.darkforge.gui.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.Container;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.elements.Slider;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;

import javax.vecmath.Vector2f;
import java.io.IOException;
import java.util.ArrayList;

public class ClickGui extends GuiScreen {

    private static Theme theme;

    private final ArrayList<Frame> frames = new ArrayList<>();

    private Frame currentFrame;

    public static int[] mouse = new int[2];

    private boolean dragging = false;

    private Vector2f draggingOffset;

    //Screen

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        for (Frame frame : frames) {
            frame.render(mouse[0], mouse[1]);
        }
    }

    @Override
    public void handleInput() throws IOException {

        int scale = mc.gameSettings.guiScale;
        mc.gameSettings.guiScale = 2;

        if (Keyboard.isCreated()) {
            Keyboard.enableRepeatEvents(true);

            while (Keyboard.next()) {
                if (Keyboard.getEventKey() == 1) {
                    mc.displayGuiScreen(null);
                }
            }
        }

        if (Mouse.isCreated()) {
            while (Mouse.next()) {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                int mouseX = Mouse.getEventX() * scaledResolution.getScaledWidth() / mc.displayWidth;
                int mouseY = scaledResolution.getScaledHeight() - Mouse.getEventY() * scaledResolution.getScaledHeight() / mc.displayHeight - 1;

                if (Mouse.getEventButton() == -1) {
                    if (Mouse.getEventDWheel() != 0) {
                        int x = mouseX;
                        int y = mouseY;
                        onMouseScroll((Mouse.getEventDWheel() / 100) * 3);
                    }

                    onMouseUpdate(mouseX, mouseY);
                    mouse[0] = mouseX;
                    mouse[1] = mouseY;
                } else if (Mouse.getEventButtonState()) {
                    onMouseClick(mouseX, mouseY, Mouse.getEventButton());
                } else {
                    onMouseRelease(mouseX, mouseY);
                }
            }
        }

        mc.gameSettings.guiScale = scale;

        super.handleInput();
    }

    @Override
    public void updateScreen() {

        onUpdate();
    }


    //Frame

    public void onMouseUpdate(int x, int y) {

        for (Frame frame : frames) {
            for (Component component : frame.getComponents()) {
                if (component.isMouseOver(x, y)) {
                    component.onMouseDrag(x, y);
                } else {
                    if (component instanceof Slider) {
                        Slider s = (Slider) component;
                        s.dragging = false;
                    }
                }
            }
        }

        if (dragging && this.currentFrame != null) {
            int yOffset = (int) ((y - this.draggingOffset.getY()) - currentFrame.getY());
            currentFrame.setxPos((int) (x - this.draggingOffset.getX()));
            currentFrame.setyPos((int) (y - this.draggingOffset.getY()));

            for (Component component : currentFrame.getComponents()) {
                component.setyBase(component.getyBase() + yOffset);

                if (component instanceof Container) {
                    Container container = (Container) component;
                    int height = 0;

                    for (Component component1 : container.getComponents()) {
                        component1.setxPos(component.getX());
                        component1.setyPos(component.getY());
                        height += component1.getDimension().height;
                    }
                }
            }
        }
    }

    public void onMouseScroll(int ammount) {

        for (Frame frame : frames) {
            if (frame.isMouseOver(mouse[0], mouse[1])) {
                frame.scrollFrame(ammount * 4);
            }
        }
    }

    public void onMouseRelease(int x, int y) {

        for (Frame frame : frames) {
            if (frame.isMouseOver(x, y)) {
                this.currentFrame = frame;

                if (frame.isMouseOverBar(x, y)) {
                    this.dragging = false;
                }

                frame.onMouseRelease(x, y, 0);
            }
        }
    }

    public void onMouseClick(int x, int y, int buttonID) {

        for (Frame frame : frames) {
            if (frame.isMouseOver(x, y)) {
                this.currentFrame = frame;

                if (frame.isMouseOver(x, y)) {
                    this.dragging = true;
                    this.draggingOffset = new Vector2f(x - frame.getX(), y - frame.getY());
                }

                frame.onMousePress(x, y, buttonID);
            }
        }
    }

    public void onUpdate() {

        for (Frame frame : frames) {
            frame.updateComponents();
        }
    }


    public void addFrame(Frame frame) {

        this.frames.add(frame);
    }

    public void setTheme(Theme theme) {

        ClickGui.theme = theme;
    }

    public ArrayList<Frame> getFrames() {

        return frames;
    }

    public static Theme getTheme() {

        return theme;
    }


}
