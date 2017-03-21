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
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.ui.tab.ITab;

import java.util.ArrayList;

public class DarkForgeTab implements ITab {

    /**
     * TODO: Finish
     */

    private ArrayList<String> category = new ArrayList<>();

    private int selectedMod, selectedTab;

    private int tab;

    private int tabHeight = 45;

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

    @Override
    public String name() {

        return "DarkForge";
    }

    @Override
    public void render(Minecraft mc, int diaplyWidth, int displayHeight) {

//        int count = 0;
//        for (Mod.Category category : Mod.Category.values()) {
//            if (!category.name().equalsIgnoreCase("gui")) {
//                int y = tabHeight + (count * 15);
//
//                GLUtils.glColor(new Color(108, 1, 0, 200));
//                GLUtils.drawBorderRect(1, y, 70, y + 15, 1);
//                count++;
//            }
//        }
    }

    @Override
    public void onKeypressed(int key) {

    }
}
