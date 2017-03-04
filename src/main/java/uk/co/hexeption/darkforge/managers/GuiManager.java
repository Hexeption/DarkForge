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

import com.sun.org.apache.xpath.internal.operations.Bool;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.gui.ClickGui;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.elements.*;
import uk.co.hexeption.darkforge.gui.gui.listener.CheckButtonClickListener;
import uk.co.hexeption.darkforge.gui.gui.listener.ComponentClickListener;
import uk.co.hexeption.darkforge.gui.gui.listener.SliderChangeListener;
import uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge.DarkForgeTheme;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.render.GLUtils;
import uk.co.hexeption.darkforge.value.BooleanValue;
import uk.co.hexeption.darkforge.value.DoubleValue;
import uk.co.hexeption.darkforge.value.FloatValue;
import uk.co.hexeption.darkforge.value.Value;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class GuiManager extends ClickGui {

    public void Initialization() {

        this.setTheme(new DarkForgeTheme());

        addCategoryPanels();
        addInfoPanel();
        addPlayerPanel();
        addMiniMapPanel();

    }

    private void addMiniMapPanel() {

    }

    private void addPlayerPanel() {

    }

    private void addInfoPanel() {

    }

    private void addCategoryPanels() {

        int x = 40;
        int y = 30;
        int right = GLUtils.getScreenWidth();

        for (Module.Category category : Module.Category.values()) {
            if (category != Module.Category.GUI) {
                String name = Character.toString(category.toString().toLowerCase().charAt(0)).toUpperCase() + category.toString().toLowerCase().substring(1);
                Frame frame = new Frame(x, y, 100, 130, name);

                for (final Module module : DarkForge.MODULE_MANAGER.getModules()) {
                    if (module.getCategory() == category) {
                        if (module.getValues().isEmpty()) {
                            final Button button = new Button(0, 0, 100, 18, frame, module.getName());
                            button.addListeners((component, button1) -> module.toggle());
                            button.setEnabled(module.getState());
                            frame.addComponent(button);
                        } else {
                            final ExpandingButton expandingButton = new ExpandingButton(0, 0, 100, 18, frame, module.getName());
                            expandingButton.addListner((component, button) -> module.toggle());
                            expandingButton.setEnabled(module.getState());

                            for (Value value : module.getValues()) {
                                if (value instanceof BooleanValue) {
                                    final BooleanValue booleanValue = (BooleanValue) value;
                                    CheckButton button = new CheckButton(0, 0, expandingButton.getDimension().width, 18, expandingButton, booleanValue.getName(), booleanValue.getValue());
                                    button.addListeners(checkButton -> {

                                        for (Value value1 : module.getValues()) {
                                            if (value1.getName().equals(booleanValue.getName())) {
                                                value1.setValue(checkButton.isEnabled());
                                            }
                                        }
                                    });
                                    expandingButton.addComponent(button);

                                } else if (value instanceof FloatValue) {
                                    final FloatValue floatValue = (FloatValue) value;
                                    Slider slider = new Slider(floatValue.getMin(), floatValue.getMax(), floatValue.getValue(), expandingButton, floatValue.getName());
                                    slider.addListener(slider1 -> {
                                        for (Value val : module.getValues()) {
                                            if (val.getName().equals(value.getName())) {
                                                val.setValue(slider1.getValue());
                                            }
                                        }
                                    });
                                    expandingButton.addComponent(slider);
                                } else if (value instanceof DoubleValue) {
                                    final DoubleValue doubleValue = (DoubleValue) value;
                                    Slider slider = new Slider(doubleValue.getMin(), doubleValue.getMax(), doubleValue.getValue(), expandingButton, doubleValue.getName());
                                    slider.addListener(slider12 -> {

                                        for (Value value1 : module.getValues()) {
                                            if (value1.getName().equals(value.getName())) {
                                                value1.setValue(slider12.getValue());
                                            }
                                        }
                                    });

                                    expandingButton.addComponent(slider);
                                }
                            }

                            frame.addComponent(expandingButton);
                        }
                    }
                }
                if (x + 120 < right) {
                    x += 120;
                } else {
                    x = 40;
                    y += 150;
                }

                frame.setMaximizible(true);
                frame.setPinnable(true);
                this.addFrame(frame);
            }
        }
    }


}
