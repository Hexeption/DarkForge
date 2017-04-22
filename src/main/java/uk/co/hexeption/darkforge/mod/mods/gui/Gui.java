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

package uk.co.hexeption.darkforge.mod.mods.gui;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventKeyboard;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 27/02/2017.
 */
@Mod.ModInfo(name = "Click Gui", description = "Enable shit", category = Mod.Category.GUI, bind = Keyboard.KEY_M)
public class Gui extends Mod {

    @Override
    public void onEvent(Event event) {
        if (getState()) {
            if (event instanceof EventKeyboard) {
                if (event.getType() == Event.Type.PRE) {
                    mc.displayGuiScreen(DarkForge.INSTANCE.getGui());
                }
            }
        }
    }
}
