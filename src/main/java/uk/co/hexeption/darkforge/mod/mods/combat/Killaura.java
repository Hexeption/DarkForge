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

package uk.co.hexeption.darkforge.mod.mods.combat;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.TimerUtils;
import uk.co.hexeption.darkforge.value.BooleanValue;
import uk.co.hexeption.darkforge.value.DoubleValue;
import uk.co.hexeption.darkforge.value.FloatValue;

/**
 * Created by Hexeption on 28/02/2017.
 */
@Mod.ModInfo(name = "Kill Aura", description = "Kill them kids", category = Mod.Category.COMBAT, bind = Keyboard.KEY_R)
public class Killaura extends Mod {

    public TimerUtils time = new TimerUtils();

    private BooleanValue player;

    private BooleanValue mob;

    private BooleanValue locked;

    private BooleanValue invisiableEntitys;

    private BooleanValue packetCrit;

    private BooleanValue jumpCrit;

    private BooleanValue multiAura;

    private BooleanValue autoDelay;

    private DoubleValue range;

    private FloatValue delay;

    private EntityLivingBase target;

    private float yaw;

    private float pitch;

    public Killaura() {

        player = new BooleanValue("Players", true);
        mob = new BooleanValue("Mobs", false);
        locked = new BooleanValue("Locked View", false);
        invisiableEntitys = new BooleanValue("Invisable Entitys", true);
        packetCrit = new BooleanValue("Packet Crit", false);
        jumpCrit = new BooleanValue("Jump Crit", false);
        multiAura = new BooleanValue("Multi Aura", false);
        autoDelay = new BooleanValue("Auto Delay", false);
        range = new DoubleValue("Range", 4D, 1D, 10D);
        delay = new FloatValue("Delay Time", 100F, 1F, 500F);

        addValue(range, delay, player, mob, locked, invisiableEntitys, packetCrit, jumpCrit, multiAura, autoDelay);
    }


//    @Override
//    public void onWorldTick() {
//
//        //Multi Aura
//        if (multiAura.getValue()) {
//            time.updateMS();
//
//            for (Object o : getWorld().loadedEntityList) {
//                if (o instanceof EntityLivingBase) {
//                    EntityLivingBase entity = (EntityLivingBase) o;
//
//                    if (!(entity instanceof EntityPlayerSP) && isValid(entity)) {
//                        faceTarget(entity, Float.MAX_VALUE, Float.MAX_VALUE);
//                        getPlayer().rotationPitch += 9.0E-4F;
//
//                        if (time.hasTimePassedM(delay.getValue().intValue())) {
//                            getPlayer().swingArm(EnumHand.MAIN_HAND);
//                            getMinecraft().playerController.attackEntity(getPlayer(), entity);
//                            time.updateLastMS();
//                        }
//                    }
//                }
//            }
//        }
//
//
//        updateTargets();
//
//        for (Object y : getWorld().loadedEntityList) {
//
//            if ((y instanceof EntityLiving)) {
//                EntityLiving e = (EntityLiving) y;
//
//                if ((isValid(e)) && (e.getDistanceToEntity(getPlayer()) < target.getDistanceToEntity(getPlayer()))) {
//                    target = e;
//                }
//            }
//        }
//
//        if (isValid(target)) {
//            if (isInStareRange(target)) {
//                faceTarget(target, Float.MAX_VALUE, Float.MAX_VALUE);
//                getPlayer().rotationPitch += 9.0E-4F;
//            }
//
//            faceTarget(target, Float.MAX_VALUE, Float.MAX_VALUE);
//            getPlayer().rotationPitch += 9.0E-4F;
//            attackEntity();
//        }
//    }

    private void updateTargets() {

        for (Object o : getWorld().loadedEntityList) {
            if ((o instanceof EntityLivingBase)) {
                EntityLivingBase entity = (EntityLivingBase) o;

                if ((!(entity instanceof EntityPlayerSP)) && (isValid(entity))) {
                    target = entity;
                }
            }
        }
    }

    private void attackEntity() {

        time.updateMS();

        if (isValid(target)) {
            faceTarget(target, Float.MAX_VALUE, Float.MAX_VALUE);
            getPlayer().rotationPitch += 9.0E-4F;

            //1.9 Aura
            if (autoDelay.getValue()) {

                if (getPlayer().getCooledAttackStrength(0) == 1) {

                    getMinecraft().playerController.attackEntity(getPlayer(), target);
                    getPlayer().swingArm(EnumHand.MAIN_HAND);

                }
            } else if (time.hasTimePassedM(delay.getValue().intValue())) {
                getMinecraft().playerController.attackEntity(getPlayer(), target);
                getPlayer().swingArm(EnumHand.MAIN_HAND);
                time.updateLastMS();
            }

        }
    }

    private void faceTarget(Entity entity, float i, float j) {

        double xPos = entity.posX - getPlayer().posX;
        double yPos = entity.posY - getPlayer().posY;
        double k;

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            k = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (getPlayer().posY - getPlayer().getEyeHeight());
        } else {
            k = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2D - (getPlayer().posY + getPlayer().getEyeHeight());
        }

        double l = MathHelper.sqrt(xPos * xPos + yPos * yPos);
        float m = (float) (Math.atan2(yPos, xPos) * 180D / Math.PI) - 90F;
        float n = (float) (Math.atan2(k - ((entity instanceof EntityLiving) ? 0.5F : 0F), l) * 180D / Math.PI);
        pitch = changeRoation(getPlayer().rotationPitch, n, j);
        yaw = changeRoation(getPlayer().rotationYaw, m, i);

        if (locked.getValue()) {
            getPlayer().rotationPitch = pitch;
            getPlayer().rotationYaw = yaw;
        }
    }

    private float changeRoation(float rotationYaw, float m, float i) {

        float angle = MathHelper.wrapDegrees(m - rotationYaw);

        if (angle > i) {
            angle = i;
        }

        if (angle < -i) {
            angle = -i;
        }

        return rotationYaw + angle;
    }


    public boolean isValid(EntityLivingBase entity) {

        if (player.getValue().booleanValue() && ((entity instanceof EntityPlayer)) && (isInAttackRange(entity)) && (entity.ticksExisted > 30) && (entity.getHealth() > 0.0F) && (!entity.isDead)) {
            if ((!entity.getName().startsWith("Body #"))) {
                if (invisiableEntitys.getValue().booleanValue() && (!entity.isInvisible()) && (entity.canEntityBeSeen(getPlayer()))) {
                    return true;
                }

                if (entity.canEntityBeSeen(getPlayer())) {
                    return true;
                }

                return true;
            }
        }

        if (mob.getValue().booleanValue() && ((entity instanceof EntityLivingBase)) && (!(entity instanceof EntityPlayer)) && (isInStareRange(entity)) && (entity.ticksExisted > 30) && (!entity.isDead)) {
            if (!entity.getName().startsWith("Body #")) {
                if (invisiableEntitys.getValue().booleanValue() && (!entity.isInvisible()) && (entity.canEntityBeSeen(getPlayer()))) {
                    return true;
                }

                if (entity.canEntityBeSeen(getPlayer())) {
                    return true;
                }

                return true;
            }
        }

        return false;
    }


    public boolean isInAttackRange(Entity target) {

        return target.getDistanceToEntity(getPlayer()) <= range.value.doubleValue();
    }

    public boolean isInStareRange(Entity target) {

        return target.getDistanceToEntity(getPlayer()) <= range.value.doubleValue() + 0.2D;
    }

}