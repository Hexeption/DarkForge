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

package uk.co.hexeption.darkforge.ui.tab.themes;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.ui.tab.ITab;
import uk.co.hexeption.darkforge.utils.render.GLUtils;
import uk.co.hexeption.darkforge.value.Value;

import java.awt.*;
import java.util.ArrayList;

public class DarkForgeTab implements ITab {

    /**
     * TODO: Add Values
     */

    private ArrayList<String> category = new ArrayList<>();

    private int selectedMod, selectedTab;

    private int tab;

    private int tabHeight = 30;

    /**
     * Initialises mods for the categorys
     */
    public DarkForgeTab() {

        Mod.Category[] array;
        int j = (array = Mod.Category.values()).length;

        for (int i = 0; i < j; i++) {
            Mod.Category category = array[i];

            if (category.name().equalsIgnoreCase("gui")) {
                continue;

            }

            this.category.add(category.toString().substring(0, 1) + category.toString().substring(1, category.toString().length()).toLowerCase());
        }
    }

    /**
     * Name for the Tab
     *
     * @return
     */
    @Override
    public String name() {

        return "DarkForge";
    }

    /**
     * Renders the tab
     *
     * @param mc
     * @param displayWidth
     * @param displayHeight
     */
    @Override
    public void render(Minecraft mc, int displayWidth, int displayHeight) {

        int count = 0;
        for (Mod.Category category : Mod.Category.values()) {
            if (!category.name().equalsIgnoreCase("gui")) {
                int y = tabHeight + (count * 15);

                GLUtils.glColor(new Color(47, 47, 47, 235));
                GLUtils.drawRect(0, y, 70, y + 15);
                count++;
            }
        }

        int count1 = 0;
        for (Mod.Category categorys : Mod.Category.values()) {
            if (categorys != Mod.Category.GUI) {
                if (categorys.name().equalsIgnoreCase(category.get(this.selectedTab))) {
                    DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow("> " + categorys.name(), 6, tabHeight + count1 * 15 + 4, -1);
                } else {
                    DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(categorys.name(), 6, tabHeight + count1 * 15 + 4, -1);

                }
                count1++;
            }
        }

        if (tab == 1 || tab == 2) {
            int modcount = 0;

            for (Mod mod : getModsForCategorys()) {
                int color;
                if (mod.getState()) {
                    color = mod.getCategory().color;
                } else {
                    color = 0xffffffff;
                }

                int y = tabHeight + (modcount * 15);
                GLUtils.glColor(new Color(47, 47, 47, 235));
                GLUtils.drawRect(73, y, 100 + this.getLongestModWidth(), y + 15);
                DarkForge.INSTANCE.fontManager.arraylist.drawCenteredStringWithShadow(!mod.getName().equalsIgnoreCase(this.getModsForCategorys().get(this.selectedMod).getName()) ? mod.getName() : "> " + mod.getName(), DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(mod.getName()) / 2 + 80, y + 8, color);
                modcount++;
            }
        }
    }

    /**
     * Moves up on the tab list
     */
    private void up() {

        if (tab == 0) {
            if (this.selectedTab <= 0) {
                this.selectedTab = this.category.size();
            }
            this.selectedTab -= 1;
        } else if (tab == 1) {
            if (this.selectedTab <= 0) {
                this.selectedMod = getModsForCategorys().size();
            }

            this.selectedMod -= 1;
        }
    }

    /**
     * Moves down on the tab list
     */
    private void down() {

        if (tab == 0) {
            if (selectedTab >= this.category.size() - 1) {
                this.selectedTab = -1;
            }

            this.selectedTab += 1;
        } else if (tab == 1) {
            if (this.selectedMod >= getModsForCategorys().size() - 1) {
                this.selectedMod = -1;
            }

            this.selectedMod += 1;
        }

    }

    /**
     * Moves to the left to close the mod list
     */
    private void left() {

        if (tab == 1) {
            tab = 0;
        }
    }

    /**
     * Moves to the right to open the mod list and activate the mods
     */
    private void right() {

        if (tab == 1) {
            this.enter();
        } else {
            if (tab == 0) {
                tab = 1;
                this.selectedMod = 0;
            }
        }
    }

    /**
     * Enables the mods
     */
    private void enter() {

        if (tab == 1) {
            getModsForCategorys().get(this.selectedMod).toggle();
        }
    }

    /**
     * Runs the keybinds for the tab
     *
     * @param key
     */
    @Override
    public void onKeyPressed(int key) {

        if (key == Keyboard.KEY_UP) {
            this.up();
        }

        if (key == Keyboard.KEY_DOWN) {
            this.down();
        }

        if (key == Keyboard.KEY_LEFT) {
            this.left();
        }

        if (key == Keyboard.KEY_RIGHT) {
            this.right();
        }

        if (key == Keyboard.KEY_RETURN) {
            this.enter();
        }
    }

    /**
     * Gets the mods for each category
     *
     * @return
     */
    private ArrayList<Mod> getModsForCategorys() {

        ArrayList<Mod> mods = new ArrayList<>();

        for (Mod mod : DarkForge.INSTANCE.modManager.getMods()) {
            if (mod.getCategory() == Mod.Category.valueOf(this.category.get(this.selectedTab).toUpperCase())) {
                mods.add(mod);
            }
        }

        return mods;
    }

    /**
     * returns the longest mod width
     *
     * @return
     */
    private int getLongestModWidth() {

        int longest = 0;
        for (Mod mod : getModsForCategorys()) {
            if (DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(mod.getName()) + 5 > longest) {
                longest = DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(mod.getName()) + 5;
            }
        }
        return longest;
    }

    /**
     * returns the longest value width
     *
     * @return
     */
    private int getLongestValueWidth() {

        int longest = 0;
        for (Mod mod : getModsForCategorys()) {
            for (Value value : mod.getValues()) {
                if (DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(value.getName()) + 5 > longest) {
                    longest = DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(value.getName()) + 5;
                }
            }
        }
        return longest;
    }
}
