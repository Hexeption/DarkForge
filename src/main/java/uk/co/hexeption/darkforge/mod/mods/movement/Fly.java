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
import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.movement.MoveEvent;
import uk.co.hexeption.darkforge.event.events.movement.PreMotionUpdateEvent;
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

    @EventTarget
    public void eventPreMotionUpdate(PreMotionUpdateEvent event) {

        if (getPlayer().movementInput.jump) {
            getPlayer().motionY = speed.getValue();
        } else if (getPlayer().movementInput.sneak) {
            getPlayer().motionY = -speed.getValue();
        } else {
            getPlayer().motionY = 0;
        }
    }

    @EventTarget
    public void eventMove(MoveEvent event) {

        setMoveSpeed(event, speed.getValue());
    }

    public void setMoveSpeed(final MoveEvent event, final double speed) {

        double forward = getPlayer().movementInput.moveForward;
        double strafe = getPlayer().movementInput.moveStrafe;
        float yaw = getPlayer().rotationYaw;

        if (forward == 0.0 && strafe == 0.0) {
            event.setMotionX(0.0);
            event.setMotionZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += ((forward > 0.0) ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += ((forward > 0.0) ? 45 : -45);
                }

                strafe = 0.0;

                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }

            event.setMotionX(forward * speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f)));
            event.setMotionZ(forward * speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f)));
        }
    }
}
