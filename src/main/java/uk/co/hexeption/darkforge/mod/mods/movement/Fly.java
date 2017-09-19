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
import uk.co.hexeption.darkforge.event.events.EventPlayerWalking;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.value.DoubleValue;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Mod.ModInfo(name = "Fly", description = "Be like SuperGirl <3", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_F)
public class Fly extends Mod {

    private final DoubleValue speed = new DoubleValue("Speed", 0.8D, 0D, 9D);


    public Fly() {

        addValue(speed);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (mc.world == null)
            return;
        mc.player.capabilities.isFlying = true;
        mc.player.onGround = false;
        mc.player.isAirBorne = true;

    }

    @Override
    public void onDisable() {
        if (mc.world == null)
            return;
        mc.player.capabilities.isFlying = false;
        mc.player.onGround = true;
        mc.player.isAirBorne = false;
        super.onDisable();
    }

    @Override
    public void onEvent(Event event) {
        if (getState() && event instanceof EventPlayerWalking && event.getType() == Event.Type.PRE) {
            EventPlayerWalking events = event.cast();

            events.getEntity().capabilities.isFlying = true;
            events.getEntity().onGround = false;
            events.getEntity().isAirBorne = true;
        }
    }
}
