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

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.render.EventRender3D;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.value.BooleanValue;
import uk.co.hexeption.darkforge.value.DoubleValue;
import uk.co.hexeption.darkforge.value.FloatValue;

/**
 * Created by Hexeption on 03/02/2017.
 */
@SideOnly(Side.CLIENT)
@Mod.ModInfo(name = "Tracer", description = "Draws a line to a player/mob/friends", category = Mod.Category.RENDER, bind = Keyboard.KEY_P)
public class Tracers extends Mod {

    public static double ticks;

    public static double x, y, z;

    //TODO: Values
    private BooleanValue player, mob;

    private FloatValue testingFloat;

    private DoubleValue testingDouble;

    public Tracers() {

        player = new BooleanValue("Player", true);
        mob = new BooleanValue("Mobs", false);
        testingFloat = new FloatValue("Float", 10f, 1f, 100f);
        testingDouble = new DoubleValue("Double", 10D, 1D, 100D);

        addValue(player, mob, testingFloat, testingDouble);
    }


    @EventTarget
    public void onRender3D(EventRender3D event) {

        for (Object entityList : getWorld().loadedEntityList) {
            if (!(entityList instanceof EntityLivingBase)) {
                continue;
            }

            EntityLivingBase entity = (EntityLivingBase) entityList;

            if (player.getValue()) {
                if (entity instanceof EntityPlayer) {
                    if (entity != getPlayer() && !entity.isInvisible()) {
                        player(entity);

                    }
                }
            }

            if (mob.getValue()) {
                if (entity instanceof EntityMob) {
                    player(entity);
                }
            }


        }
    }

    private void player(EntityLivingBase entity) {

        render(1, 1, 1, 1, entity);
    }

    private void render(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = mc.getRenderManager().viewerPosX;
        double renderPosY = mc.getRenderManager().viewerPosY;
        double renderPosZ = mc.getRenderManager().viewerPosZ;
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * ticks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * ticks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * ticks) - renderPosZ;
//        LogHelper.info("X:" + x + " Y:" + y + " Z:" + z);
        RenderUtils.drawTracer(xPos, yPos, zPos, 2, red, green, blue, alpha);


    }


}
