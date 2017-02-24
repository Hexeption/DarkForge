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
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.RenderUtils;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
@Module.ModInfo(name = "ItemESP", description = "highlights a Item", category = Module.Category.RENDER, bind = Keyboard.KEY_L)
public class ItemESP extends Module {

    private static final AxisAlignedBB ITEM_BOX = new AxisAlignedBB(-0.175, 0, -0.175, 0.175, 0.35, 0.175);

    @Override
    @SideOnly(Side.CLIENT)
    public void onWorldRender() {

        glPushMatrix();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(2);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        double renderPosX = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "o", "renderPosX");
        double renderPosY = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "p", "renderPosY");
        double renderPosZ = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "q", "renderPosZ");

        glTranslated(-renderPosX, -renderPosY, -renderPosZ);

        glColor4f(0.4f, 0, 1, 0.5F);

        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityItem) {
                glPushMatrix();
                glTranslated(entity.posX, entity.posY, entity.posZ);

                RenderUtils.drawOutlinedBox(ITEM_BOX);
                glPopMatrix();
            }
        }

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDisable(GL_LINE_SMOOTH);
        glPopMatrix();
    }
}
