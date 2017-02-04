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

package uk.co.hexeption.darkforge.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static org.lwjgl.opengl.GL11.*;

import uk.co.hexeption.darkforge.gui.base.Component;
import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.utils.GuiUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;

import java.util.List;

/**
 * Created by Hexeption on 16/01/2017.
 */
@SideOnly(Side.CLIENT)
public class Radar extends Component {

    public Radar() {

        setH(60);
        setW(60);
    }

    @Override
    public void draw(Window window, ISkin skin, int mouseX, int mouseY) {

        final List<Entity> entities = Minecraft.getMinecraft().world.loadedEntityList;
        glPushMatrix();
        glTranslatef(getX() + (getW() / 2), getY() + (getH() / 2), 0);

        RenderUtils.drawCircle(0, 0, 1.5, 0xff000000);
        RenderUtils.drawCircle(0, 0, 1.25, 0xffffffff);

        glPopMatrix();

        for (final Entity entity : entities) {
            final double xdist = Minecraft.getMinecraft().player.posX - entity.posX;
            final double zdist = Minecraft.getMinecraft().player.posZ - entity.posZ;
            final double tdist = Math.sqrt((xdist * xdist) + (zdist * zdist));
            final double diffInAngle = MathHelper.wrapDegrees(Minecraft.getMinecraft().player.rotationYaw - ((Math.atan2(zdist, xdist) * 180.0D) / Math.PI));
            final double finalx = Math.cos(Math.toRadians(diffInAngle)) * tdist * 2;
            final double finalY = Math.sin(Math.toRadians(diffInAngle)) * tdist * 2;

            glPushMatrix();
            glTranslatef(getX() + (getW() / 2), getY() + (getH() / 2), 0);

            if (!(entity instanceof EntityPlayerMP)) {
                if (entity instanceof EntityPlayer) {
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xff0000ff);
                    glScalef(0.5f, 0.5f, 0.5f);
                    final EntityPlayer p = (EntityPlayer) entity;
                    final String username = p.getDisplayNameString();

                    getFontRenderer().drawString(username, (int) (finalx) - (getFontRenderer().getStringWidth(username) / 2), (int) finalY - 11, 0xffffff);
                    glScalef(2f,2f,2f);
                }
                if(entity instanceof EntityAnimal){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xff00ff00);
                }
                if(entity instanceof EntityMob){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xffff0000);
                }
                if(entity instanceof EntitySlime){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xffff88cc);
                }
                if(entity instanceof EntityVillager){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xff8b4513);
                }
                if(entity instanceof EntityBat){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xfff4a460);
                }
                if(entity instanceof EntitySquid){
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 1, 0xff000000);
                    RenderUtils.drawCircle((int) finalx / 2, (int) finalY / 2, 0.75, 0xff003399);
                }
            }

            glPopMatrix();

        }

        GuiUtils.drawBorderedRect(getX() - 1, getY() - 2, getW() + 4, getH() + 16, 1.5f,0xaa000000, 0x0 );
    }

    @Override
    public void update() {

    }

    @Override
    public void drawExtra() {

    }

    @Override
    public void mouseClicked(Window window, int mouseX, int mouseY, int button) {

    }

    @Override
    public void keyTyped(Window window, int key, char c) {

    }
}
