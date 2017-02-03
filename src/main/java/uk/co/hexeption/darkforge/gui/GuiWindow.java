package uk.co.hexeption.darkforge.gui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.gui.base.Window;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hexeption on 15/01/2017.
 */
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
