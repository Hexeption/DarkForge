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

import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.gui.ClickGui;
import uk.co.hexeption.darkforge.gui.gui.elements.Button;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.elements.Slider;
import uk.co.hexeption.darkforge.gui.gui.listener.SliderChangeListener;
import uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge.DarkForgeTheme;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class GuiManager extends ClickGui {

    public void Initialization() {

        this.setTheme(new DarkForgeTheme());
        int x = 40;
        int y = 30;

        Frame frame = new Frame(x, y, 100, 130, "Testing");

        final Button button = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button1 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button2 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button3 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button4 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button5 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button6 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button7 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button8 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button9 = new Button(0, 0, 100, 18, frame, "Hex");
        final Button button10 = new Button(0, 0, 100, 18, frame, "Hex");
        final Slider slider = new Slider(1, 100, 2, frame, "Testing");
        slider.addListener(slider1 -> System.out.println(slider1.getValue()));

        frame.addComponent(slider);
        frame.addComponent(button);
        frame.addComponent(button1);
        frame.addComponent(button2);
        frame.addComponent(button3);
        frame.addComponent(button4);
        frame.addComponent(button5);
        frame.addComponent(button6);
        frame.addComponent(button7);
        frame.addComponent(button8);
        frame.addComponent(button9);
        frame.addComponent(button10);

        this.addFrame(frame);

//        int right = GLUtils.getScreenWidth();
//
//        for (Module.Category category : Module.Category.values()) {
//            if (category != Module.Category.GUI) {
//                String name = Character.toString(category.toString().toLowerCase().charAt(0)).toUpperCase() + category.toString().toLowerCase().substring(1);
//                Frame frame = new Frame(x, y, 100, 130, name);
//
//                for (final Module module : DarkForge.MODULE_MANAGER.getModules()) {
//                    if (module.getCategory() == category) {
//                        final Button button = new Button(0, 0, 100, 18, frame, module.getName());
//                        button.addListeners((component, button1) -> module.toggle());
//                        button.setEnabled(module.getState());
//                        frame.addComponent(button);
//                    }
//                }
//                if (x + 120 < right) {
//                    x += 120;
//                } else {
//                    x = 40;
//                    y += 150;
//                }
//
//                frame.setMaximizible(true);
//                frame.setPinnable(true);
//                this.addFrame(frame);
//            }
//        }

    }


}
