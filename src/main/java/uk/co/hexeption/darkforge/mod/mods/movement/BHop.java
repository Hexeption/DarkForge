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

import net.minecraft.potion.Potion;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventMove;
import uk.co.hexeption.darkforge.event.events.EventPlayerUpdate;
import uk.co.hexeption.darkforge.mixin.imp.IMixinMinecraft;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.MathUtils;

import java.util.List;

/**
 * Created by Keir on 22/04/2017.
 */
@Mod.ModInfo(name = "BHop", description = "Bunny HOP.", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_C)
public class BHop extends Mod {

    //TODO:
    boolean speed = true;
    private int stage;
    private double moveSpeed, lastDist;

    @Override
    public void onEvent(Event event) {

        if (getState() && event.getType() == Event.Type.PRE) {
            if (event instanceof EventMove) {
                mixMC.getTimer().timerSpeed = 1.0888F;

                if ((!mc.player.isCollidedHorizontally)) {
                    if (MathUtils.round(mc.player.posY - (int) mc.player.posY, 3) == MathUtils.round(0.4D, 3)) {
                        ((EventMove) event).setMotionY(mc.player.motionY = 0.31D);
                    } else if (MathUtils.round(mc.player.posY - (int) mc.player.posY, 3) == MathUtils.round(0.71D, 3)) {
                        ((EventMove) event).setMotionY(mc.player.motionY = 0.04D);
                    } else if (MathUtils.round(mc.player.posY - (int) mc.player.posY, 3) == MathUtils.round(0.75D, 3)) {
                        ((EventMove) event).setMotionY(mc.player.motionY = -0.2D);
                    } else if (MathUtils.round(mc.player.posY - (int) mc.player.posY, 3) == MathUtils.round(0.55D, 3)) {
                        ((EventMove) event).setMotionY(mc.player.motionY = -0.14D);
                    } else if (MathUtils.round(mc.player.posY - (int) mc.player.posY, 3) == MathUtils.round(0.41D, 3)) {
                        ((EventMove) event).setMotionY(mc.player.motionY = -0.2D);
                    }
                }
                if ((stage == 1) && ((mc.player.moveForward != 0.0F) || (mc.player.moveStrafing != 0.0F))) {
                    moveSpeed = 1.35D * getBaseMoveSpeed() - 0.01D;
                } else if ((stage == 2) && ((mc.player.moveForward != 0.0F) || (mc.player.moveStrafing != 0.0F))) {
                    ((EventMove) event).setMotionY(mc.player.motionY = 0.3999D);
                    moveSpeed *= 1.395D;
                } else if (stage == 3) {
                    double difference = 0.66D * (lastDist - getBaseMoveSpeed());
                    moveSpeed = lastDist - difference;
                    speed = !speed;
                } else {
                    List collidingList = mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0D, mc.player.motionY, 0.0D));
                    if (((collidingList.size() > 0) || (mc.player.isCollidedVertically)) && (stage > 0)) {
                        stage = (mc.player.moveForward != 0.0F) || (mc.player.moveStrafing != 0.0F) ? 1 : 0;
                    }
                    moveSpeed = lastDist - lastDist / 159.0D;
                }
                setMoveSpeed(((EventMove) event), moveSpeed = Math.max(moveSpeed, getBaseMoveSpeed()));
                if ((mc.player.moveForward != 0.0F) || (mc.player.moveStrafing != 0.0F)) {
                    stage += 1;
                }
            } else if (event instanceof EventPlayerUpdate) {
                double xDist = mc.player.posX - mc.player.prevPosX;
                double zDist = mc.player.posZ - mc.player.prevPosZ;
                lastDist = Math.sqrt(xDist * xDist + zDist * zDist);
            }
        }
    }

    private double getBaseMoveSpeed() {
        double baseSpeed = 0.2873D;
        if (mc.world != null && mc.player.isPotionActive(Potion.getPotionById(1))) {
            int amplifire = mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier();
            baseSpeed *= (1D + 0.2D * (amplifire + 1));
        }
        return baseSpeed;
    }

    private void setMoveSpeed(EventMove event, double moveSpeed) {
        double forward = mc.player.moveForward;
        double strafe = mc.player.moveStrafing;
        float yaw = mc.player.rotationYaw;
        if ((forward == 0.0D) && (strafe == 0.0D)) {
            event.setMotionX(0.0D);
            event.setMotionZ(0.0D);
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += (forward > 0.0D ? -45 : 45);
                } else if (strafe < 0.0D) {
                    yaw += (forward > 0.0D ? 45 : -45);
                }
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1.0D;
                } else if (forward < 0.0D) {
                    forward = -1.0D;
                }
            }
            event.setMotionX(forward * moveSpeed * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * moveSpeed * Math.sin(Math.toRadians(yaw + 90.0F)));
            event.setMotionZ(forward * moveSpeed * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * moveSpeed * Math.cos(Math.toRadians(yaw + 90.0F)));
        }
    }

}
