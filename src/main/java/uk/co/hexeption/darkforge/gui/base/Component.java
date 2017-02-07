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

package uk.co.hexeption.darkforge.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.ttf.MinecraftFontRenderer;

import java.awt.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public abstract class Component {

    private int x, y, w, h;

    private String text;

    private boolean visible;

    private Window parent;

    private final FontRenderer vanillaFontRenderer;

    {
        vanillaFontRenderer = new FontRenderer(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
    }

    public Component() {

        this(100, 20);
    }

    public Component(int w, int h) {

        this("", w, h);
    }

    public Component(String text, int w, int h) {

        this(text, w, h, true);
    }

    public Component(String text, int w, int h, boolean visible) {

        this.w = w;
        this.h = h;
        this.text = text;
        this.visible = visible;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getW() {

        return w;
    }

    public void setW(int w) {

        this.w = w;
    }

    public int getH() {

        return h;
    }

    public void setH(int h) {

        this.h = h;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    public boolean isVisible() {

        return visible;
    }

    public void setVisible(boolean visible) {

        this.visible = visible;
    }

    public Window getParent() {

        return parent;
    }

    public void setParent(Window parent) {

        this.parent = parent;
    }

    /**
     * Draw the component
     *
     * @param window
     * @param skin
     * @param mouseX
     * @param mouseY
     */
    public abstract void draw(Window window, ISkin skin, int mouseX, int mouseY);

    /**
     * Updates the component
     */
    public abstract void update();

    /**
     * Draws extra things
     */
    public abstract void drawExtra();

    /**
     * Mouse Clicks
     *
     * @param window
     * @param mouseX
     * @param mouseY
     * @param button
     */
    public abstract void mouseClicked(Window window, int mouseX, int mouseY, int button);

    /**
     * On Key typed
     *
     * @param window
     * @param key
     * @param c
     */
    public abstract void keyTyped(Window window, int key, char c);

    /**
     * DarkStorm's method for calculating the location of the mouse
     *
     * @return
     */
    protected Point calculateMouseLocation() {

        final Minecraft minecraft = Minecraft.getMinecraft();
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0) {
            scale = 1000;
        }

        int scaleFactor = 0;
        while ((scaleFactor < scale) && ((minecraft.displayWidth / (scaleFactor + 1)) >= 320) && ((minecraft.displayHeight / (scaleFactor + 1)) >= 240)) {
            scaleFactor++;
        }
        return new Point(Mouse.getX() / scaleFactor, (minecraft.displayHeight / scaleFactor) - (Mouse.getY() / scaleFactor) - 1);
    }

    public boolean isMouseOver() {

        final Point p = calculateMouseLocation();
        if (p.x >= x) {
            if (p.x <= (x + w)) {
                if (p.y >= y) {
                    if (p.y <= (y + h)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    protected static MinecraftFontRenderer getFontRenderer() {

        return DarkForge.instance.FONT_MANAGER.guiTitle;
    }
}
