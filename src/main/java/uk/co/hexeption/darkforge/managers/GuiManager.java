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

package uk.co.hexeption.darkforge.managers;

import uk.co.hexeption.darkforge.gui.gui.ClickGui;
import uk.co.hexeption.darkforge.gui.gui.elements.Button;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge.DarkForgeTheme;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class GuiManager extends ClickGui {

    public GuiManager() {

        this.setTheme(new DarkForgeTheme());
        int x = 10;
        int y = 20;

        Frame frame = new Frame(x, y, 100, 130, "Test");
        Button button = new Button(0, 0, 100, 18, frame, "hi");
        frame.addComponent(button);
        this.addFrame(frame);
    }
}
