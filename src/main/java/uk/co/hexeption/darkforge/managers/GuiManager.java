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
import uk.co.hexeption.darkforge.gui.gui.elements.*;
import uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge.DarkForgeTheme;
import uk.co.hexeption.darkforge.mod.Mod;
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

//        this.setTheme(new HuzuniTheme());
        this.setTheme(new DarkForgeTheme());

        addCategoryPanels();
        addInfoPanel();
        addPlayerPanel();
        addMiniMapPanel();

    }

//    private void categorys(){
//        Frame frame = new Frame(10, 10,100, 50, "Gui Manager");
//        for (Mod.Category category : Mod.Category.values()) {
//            if(category != Mod.Category.GUI){
//                String name = Character.toString(category.toString().toLowerCase().charAt(0)).toUpperCase() + category.toString().toLowerCase().substring(1);
//                Button button = new Button(0,0,100,18, frame, name);
//                button.addListeners(new ComponentClickListener() {
//
//                    @Override
//                    public void onComponenetClick(Component component, int button) {
//
//                    }
//                });
//                frame.addComponent(button);
//            }
//        }
//
//        frame.setMaximizible(true);
//        frame.setPinnable(false);
//        this.addFrame(frame);
//    }

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

        for (Mod.Category category : Mod.Category.values()) {
            if (category != Mod.Category.GUI) {
                String name = Character.toString(category.toString().toLowerCase().charAt(0)).toUpperCase() + category.toString().toLowerCase().substring(1);
                Frame frame = new Frame(x, y, 100, 130, name);

                for (final Mod mod : DarkForge.INSTANCE.modManager.getMods()) {
                    if (mod.getCategory() == category) {
                        final ExpandingButton expandingButton = new ExpandingButton(0, 0, 100, 18, frame, mod.getName(), mod);
                        expandingButton.addListner((component, button) -> mod.toggle());
                        expandingButton.setEnabled(mod.getState());
                        if (!mod.getValues().isEmpty()) {
                            for (Value value : mod.getValues()) {
                                if (value instanceof BooleanValue) {
                                    final BooleanValue booleanValue = (BooleanValue) value;
                                    CheckButton button = new CheckButton(0, 0, expandingButton.getDimension().width, 18, expandingButton, booleanValue.getName(), booleanValue.getValue());
                                    button.addListeners(checkButton -> {

                                        for (Value value1 : mod.getValues()) {
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
                                        for (Value val : mod.getValues()) {
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

                                        for (Value value1 : mod.getValues()) {
                                            if (value1.getName().equals(value.getName())) {
                                                value1.setValue(slider12.getValue());
                                            }
                                        }
                                    });

                                    expandingButton.addComponent(slider);
                                }
                            }
                        }

                        KeybindMods keybind = new KeybindMods(0, 0, 12, 15, expandingButton, mod);
                        expandingButton.addComponent(keybind);
                        frame.addComponent(expandingButton);
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
