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

package uk.co.hexeption.darkforge.mod.mods.movement;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventPlayerUpdate;
import uk.co.hexeption.darkforge.event.events.EventPlayerWalking;
import uk.co.hexeption.darkforge.mod.Mod;

@Mod.ModInfo(name = "Auto Sprint", description = "Automatically Sprints for you.", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_L)
public class AutoSprint extends Mod {

    @Override
    public void onEnable() {

        if (getPlayer() != null)
            getPlayer().setSprinting(true);
    }

    @Override
    public void onDisable() {

        if (getPlayer() != null)
            getPlayer().setSprinting(false);
    }

    @Override
    public void onEvent(Event event) {
        if (getState()) {
            if (event instanceof EventPlayerWalking) {
                if (event.getType() == Event.Type.PRE) {
                    if ((!mc.player.isCollidedHorizontally) && (mc.player.moveForward > 0.0F) && (!mc.player.isSneaking())) {
                        mc.player.setSprinting(true);
                    }
                }
            } else if (event instanceof EventPlayerUpdate) {

            }
        }
    }
}
