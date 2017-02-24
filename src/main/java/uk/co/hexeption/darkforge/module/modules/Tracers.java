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

package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.RenderUtils;

/**
 * Created by Hexeption on 03/02/2017.
 */
@SideOnly(Side.CLIENT)
@Module.ModInfo(name = "Tracer", description = "Draws a line to a player/mob/friends", category = Module.Category.RENDER, bind = Keyboard.KEY_P)
public class Tracers extends Module {

    //TODO: Values
    public static boolean player = true, mob;

    public static double ticks;

    public static double x, y, z;

    @Override
    public void onWorldRender() {

        for (Object entityList : getWorld().loadedEntityList) {
            if (!(entityList instanceof EntityLivingBase)) {
                continue;
            }

            EntityLivingBase entity = (EntityLivingBase) entityList;

            if (player) {
                if (entity instanceof EntityPlayer) {
                    if (entity != getPlayer() && !entity.isInvisible()) {
                        player(entity);

                    }
                }
            }
            mob = true;

            if (mob) {
                if (entity instanceof EntityMob && entity instanceof EntityAnimal) {
                    player(entity);
                }
            }


        }
    }

    private void player(EntityLivingBase entity) {

        render(1, 1, 1, 1, entity);
    }

    private void render(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "o", "renderPosX");
        double renderPosY = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "p", "renderPosY");
        double renderPosZ = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "q", "renderPosZ");
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * ticks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * ticks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * ticks) - renderPosZ;
//        LogHelper.info("X:" + x + " Y:" + y + " Z:" + z);
        RenderUtils.drawTracer(xPos, yPos, zPos, 2, red, green, blue, alpha);


    }


}
