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

package uk.co.hexeption.darkforge.mod.mods.render;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventPlayerUpdate;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.value.BooleanValue;

/**
 * Created by Hexeption on 03/02/2017.
 */
@Mod.ModInfo(name = "Tracer", description = "Draws a line to a player/mob/friends", category = Mod.Category.RENDER, bind = Keyboard.KEY_P)
public class Tracers extends Mod {

    //TODO: Values
    private BooleanValue player, mob;

    public Tracers() {

        player = new BooleanValue("Player", true);
        mob = new BooleanValue("Mobs", false);

        addValue(player, mob);
    }

    private void player(EntityLivingBase entity) {

        render(255, 255, 255, 255, entity);
    }

    private void render(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = mixRM.getRenderPosX();
        double renderPosY = mixRM.getRenderPosY();
        double renderPosZ = mixRM.getRenderPosZ();
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * mixMC.getTimer().elapsedPartialTicks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * mixMC.getTimer().elapsedPartialTicks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * mixMC.getTimer().elapsedPartialTicks) - renderPosZ;
        RenderUtils.drawTracer(xPos, yPos, zPos, 2, red, green, blue, alpha);


    }

    @Override
    public void onEvent(Event event) {

        if (getState()) {
            if (event instanceof EventPlayerUpdate) {

                for (Object entityList : mc.world.loadedEntityList) {
                    if (!(entityList instanceof EntityLivingBase)) {
                        continue;
                    }

                    EntityLivingBase entity = (EntityLivingBase) entityList;

                    if (player.getValue()) {
                        if (entity instanceof EntityPlayer) {
                            if (entity != mc.player && !entity.isInvisible()) {
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
        }
    }
}
