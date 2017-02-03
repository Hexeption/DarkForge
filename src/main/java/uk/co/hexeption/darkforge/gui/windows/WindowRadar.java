package uk.co.hexeption.darkforge.gui.windows;

import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.components.Radar;
import uk.co.hexeption.darkforge.gui.skin.SkinDarkForge;

/**
 * Created by Hexeption on 16/01/2017.
 */
public class WindowRadar extends Window {

    public WindowRadar( int x, int y) {

        super("Radar",new SkinDarkForge(), x, y,0,0 , true);
    }

    @Override
    public void addComponents() {
        addChild(new Radar());
    }
}
