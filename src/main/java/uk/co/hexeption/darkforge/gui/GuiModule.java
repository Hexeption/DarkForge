package uk.co.hexeption.darkforge.gui;

import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.windows.WindowModule;
import uk.co.hexeption.darkforge.gui.windows.WindowRadar;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class GuiModule extends GuiWindow {

    private int yOffset = 0;

    public GuiModule() {

        for (final Module.Category c : Module.Category.values()) {
            addAWindow(new WindowModule(c, 2, yOffset));
        }

        addAWindow(new WindowRadar(2, yOffset));

    }

    private void addAWindow(final Window window) {

        super.addWindow(window);
        yOffset += 15;
    }
}
