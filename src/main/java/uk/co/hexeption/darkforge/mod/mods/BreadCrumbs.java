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
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import uk.co.hexeption.darkforge.api.annotation.TestClass;
import uk.co.hexeption.darkforge.mod.Mod;

import java.util.LinkedList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
@TestClass
@Mod.ModInfo(name = "Bread Crumbs", description = "Leaves a trail behind you", category = Mod.Category.RENDER, bind = 0)
public class BreadCrumbs extends Mod {

    private final LinkedList<double[]> positions = new LinkedList<double[]>();

    @Override
    public void onWorldTick() {

        synchronized (positions) {
            positions.add(new double[]{getPlayer().posX, getPlayer().posY, getPlayer().posZ});
        }
    }

    @Override
    public void onWorldRender() {

        synchronized (positions) {
            glPushMatrix();

            glDisable(GL11.GL_TEXTURE_2D);
            glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            glEnable(GL11.GL_LINE_SMOOTH);
            glEnable(GL11.GL_BLEND);
            glDisable(GL11.GL_DEPTH_TEST);
            getEntityRenderer().disableLightmap();
            glBegin(GL11.GL_LINE_STRIP);
            glColor4d(0, 0.7D, 0.7D, 1);
            double renderPosX = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "o", "renderPosX");
            double renderPosY = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "p", "renderPosY");
            double renderPosZ = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "q", "renderPosZ");

            for (final double[] pos : positions) {
                glVertex3d(pos[0] - renderPosX, pos[1] - renderPosY, pos[2] - renderPosZ);
            }

            glColor4d(1, 1, 1, 1);
            glEnd();
            glEnable(GL11.GL_DEPTH_TEST);
            glDisable(GL11.GL_LINE_SMOOTH);
            glDisable(GL11.GL_BLEND);
            glEnable(GL11.GL_TEXTURE_2D);
            glPopMatrix();
        }
    }
}
