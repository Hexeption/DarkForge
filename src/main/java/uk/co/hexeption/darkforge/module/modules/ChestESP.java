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

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.RenderUtils;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Module.ModInfo(name = "ChestESP", description = "highlights a Chest", category = Module.Category.RENDER, bind = Keyboard.KEY_N)
public class ChestESP extends Module {

    private final ArrayList<AxisAlignedBB> chest = new ArrayList<>();

    private final ArrayList<AxisAlignedBB> enderChest = new ArrayList<>();

    private final ArrayList<AxisAlignedBB> trapedChest = new ArrayList<>();

    @Override
    @SideOnly(Side.CLIENT)

    public void onWorldRender() {

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(2);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);

        double renderPosX = ReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "renderPosX");
        double renderPosY = ReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "renderPosY");
        double renderPosZ = ReflectionHelper.getPrivateValue(RenderManager.class, mc.getRenderManager(), "renderPosZ");

        glPushMatrix();
        glTranslated(-renderPosX, -renderPosY, -renderPosZ);

        glColor4f(0, 1, 0, 0.25F);
        chest.forEach((bb) -> RenderUtils.drawSolidBox(bb));
        glColor4f(0, 1, 0, 0.5F);
        chest.forEach((bb) -> RenderUtils.drawOutlinedBox(bb));

        glColor4f(1, 0.5F, 1, 0.25F);
        enderChest.forEach((bb) -> RenderUtils.drawSolidBox(bb));
        glColor4f(1, 0.5F, 1, 0.5F);
        enderChest.forEach((bb) -> RenderUtils.drawOutlinedBox(bb));

        glColor4f(1, 0, 0, 0.25F);
        trapedChest.forEach((bb) -> RenderUtils.drawSolidBox(bb));
        glColor4f(1, 0, 0, 0.5F);
        trapedChest.forEach((bb) -> RenderUtils.drawOutlinedBox(bb));
        glPopMatrix();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDisable(GL_LINE_SMOOTH);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onWorldTick() {

        chest.clear();
        enderChest.clear();
        trapedChest.clear();

        for (TileEntity tileEntity : mc.world.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityChest) {
                TileEntityChest chest = (TileEntityChest) tileEntity;

                if (chest.adjacentChestXPos != null || chest.adjacentChestZPos != null)
                    continue;

                AxisAlignedBB bb = RenderUtils.getBoundingBox(chest.getPos());

                if (chest.adjacentChestXNeg != null)
                    bb = bb.union(RenderUtils.getBoundingBox(chest.adjacentChestXNeg.getPos()));
                else if (chest.adjacentChestZNeg != null)
                    bb = bb.union(RenderUtils.getBoundingBox(chest.adjacentChestZNeg.getPos()));

                boolean trapped = chest.getChestType() == BlockChest.Type.TRAP;

                if (trapped)
                    this.trapedChest.add(bb);
                else
                    this.chest.add(bb);
            }

            if (tileEntity instanceof TileEntityEnderChest) {
                AxisAlignedBB bb = RenderUtils.getBoundingBox(tileEntity.getPos());

                this.enderChest.add(bb);
            }
        }

    }
}
