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
package uk.co.hexeption.darkforge.mod.mods;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.mod.Mod;

@Mod.ModInfo(name = "Step", description = "Automatically Sprints for you.", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_K)
public class Step extends Mod {

    private float stepHeight = 1.5f;

    /**
     * One and a half block
     */

    @Override
    public void onEnable() {

        if (getPlayer() != null)
            getPlayer().stepHeight = stepHeight;
    }

    @Override
    public void onDisable() {

        if (getPlayer() != null)
            getPlayer().stepHeight = 0.5f;
    }
}
