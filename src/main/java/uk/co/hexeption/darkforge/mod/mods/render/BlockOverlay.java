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

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderWorld;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.RenderUtils;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Mod.ModInfo(name = "Block Overlay", description = "highlights a block", category = Mod.Category.RENDER, bind = Keyboard.KEY_O)
public class BlockOverlay extends Mod {

    @Override
    public void onEvent(Event event) {
        if (getState()) {
            if (event instanceof EventRenderWorld) {
                RayTraceResult rayTraceResult = mc.objectMouseOver;

                if (rayTraceResult.entityHit != null)
                    return;

                Block block = mc.world.getBlockState(rayTraceResult.getBlockPos()).getBlock();
                BlockPos blockPos = rayTraceResult.getBlockPos();


                if (Block.getIdFromBlock(block) == 0)
                    return;

                glPushMatrix();
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glEnable(GL_LINE_SMOOTH);
                glLineWidth(2);
                glDisable(GL_TEXTURE_2D);
                glEnable(GL_CULL_FACE);
                glDisable(GL_DEPTH_TEST);
                double renderPosX = mc.getRenderManager().viewerPosX;
                double renderPosY = mc.getRenderManager().viewerPosY;
                double renderPosZ = mc.getRenderManager().viewerPosZ;

                glTranslated(-renderPosX, -renderPosY, -renderPosZ);
                glTranslated(blockPos.getX(), blockPos.getY(), blockPos.getZ());

                //TODO: Add Controller Mixin
                float currentBlockDamage = MCWrapper.getController().getBlockDamage();

                float progress = currentBlockDamage;

                if (progress < 0)
                    progress = 1;

                float red = progress * 2;
                float green = 2 - red;

                glColor4f(red, green, 0, 0.25F);
                RenderUtils.drawSolidBox();
                glColor4f(red, green, 0, 0.5F);
                RenderUtils.drawOutlinedBox();

                glColor4f(1, 1, 1, 1);

                glEnable(GL_DEPTH_TEST);
                glEnable(GL_TEXTURE_2D);
                glDisable(GL_BLEND);
                glDisable(GL_LINE_SMOOTH);
                glPopMatrix()
            }
        }
    }
}
