package uk.co.hexeption.darkforge.gui.windows;

import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.components.Button;
import uk.co.hexeption.darkforge.gui.components.Radar;
import uk.co.hexeption.darkforge.gui.components.Scrollbar;
import uk.co.hexeption.darkforge.gui.skin.SkinDarkForge;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class WindowModule extends Window {

    private final Module.Category category;

    public WindowModule(final Module.Category category, final int x, final int y) {

        super(category.name(), new SkinDarkForge(), x, y, 0, 0, true);
        this.category = category;
        addCompoentsLater();

    }

    @Override
    public void addComponents() {
    }

    private void addCompoentsLater() {

        for (final Module module : ModuleManager.getInstance().getModules()) {
            if (module.getCategory().equals(category)) {
                final Button button = new Button(module.getName(), module);
                button.setParent(this);
                addChild(button);
            }
        }
    }
}
