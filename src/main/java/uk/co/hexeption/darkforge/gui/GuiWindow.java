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

package uk.co.hexeption.darkforge.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.gui.base.Window;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class GuiWindow extends GuiScreen {

    private final ArrayList<Window> windowList = new ArrayList();

    @Override
    public void initGui() {

        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void onGuiClosed() {

        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        renderWindow(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void renderWindow(int mouseX, int mouseY) {

        try {
            synchronized (windowList) {
                for (int i = windowList.size() - 1; i >= 0; i--) {
                    final Window window = windowList.get(i);
                    window.drawWindow(mouseX, mouseY);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void moveToFrontOfList(final Window window) {

        synchronized (windowList) {
            windowList.remove(window);
            windowList.add(0, window);
        }
    }

    private boolean isInFrontOfList(final Window window) {

        synchronized (windowList) {
            return windowList.get(0) == window;
        }
    }

    public ArrayList<Window> getWindowList() {

        synchronized (windowList) {
            return windowList;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {

        synchronized (windowList) {
            for (final Window window : windowList) {
                if (isInFrontOfList(window)) {
                    window.keyTyped(keyCode, typedChar);
                    break;
                }
            }
        }
        super.keyTyped(typedChar, keyCode);
    }

    public void addWindow(final Window window) {

        synchronized (windowList) {
            if (windowList.contains(window)) {
                return;
            }
            windowList.add(window);
        }
    }

    public void removeWindow(final Window window) {

        synchronized (windowList) {
            if (!windowList.contains(window)) {
                return;
            }
            windowList.remove(window);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        synchronized (windowList) {
            for (final Window window : windowList) {
                if (window.mouseClicked(mouseX, mouseY, mouseButton)) {
                    if (!isInFrontOfList(window)) {
                        moveToFrontOfList(window);
                    }
                    break;
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


}
