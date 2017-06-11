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

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderWorld;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Keir on 22/04/2017.
 */
@Mod.ModInfo(name = "Charms", description = "highlights a entities", category = Mod.Category.RENDER, bind = Keyboard.KEY_J)
public class Charms extends Mod {
    @Override
    public void onEvent(Event event) {

        if (getState() && event.getType() == Event.Type.PRE) {
            if (event instanceof EventRenderWorld) {
                GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
                RenderHelper.enableStandardItemLighting();

                for (Entity entity : mc.world.getLoadedEntityList()) {
                    if ((entity == mc.getRenderViewEntity() && mc.gameSettings.thirdPersonView == 0)) {
                        continue;
                    }

                    mc.entityRenderer.disableLightmap();
//                    mc.getRenderManager().renderEntityStatic(entity, ((IMixinMinecraft) mc).getTimer().renderPartialTicks, false);
                    mc.entityRenderer.enableLightmap();
                }

            }
        }

    }

}
