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

package uk.co.hexeption.darkforge.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class GuiUtils {


    public static ScaledResolution getScaledResolution() {

        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return scaledResolution;
    }

    public static int getWidth() {

        final ScaledResolution sr = getScaledResolution();
        return sr.getScaledWidth();
    }

    public static int getHeight() {

        final ScaledResolution sr = getScaledResolution();
        return sr.getScaledHeight();
    }

    public static void enableScissoring() {

        glEnable(GL_SCISSOR_TEST);
    }

    public static void scissor(final float x, final float y, final float x2, final float y2) {

        final ScaledResolution sr = getScaledResolution();
        final int factor = sr.getScaleFactor();
        glScissor((int) (x * factor), (int) ((sr.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor), (int) (y2 - y) * factor);
    }

    public static void disableScissoring() {

        glDisable(GL_SCISSOR_TEST);
    }

    public static void drawRect(double x, double y, double w, double h, int color) {

        float alpha = (float) (color >> 24 & 0xff) / 255F;
        float red = (float) (color >> 16 & 0xff) / 255F;
        float green = (float) (color >> 8 & 0xff) / 255F;
        float blue = (float) (color & 0xff) / 255F;

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_LINE_SMOOTH);
        glPushMatrix();
        glColor4f(red, green, blue, alpha);
        glBegin(GL_QUADS);
        glVertex2d(x, y);
        glVertex2d(x, y + h);
        glVertex2d(x + w, y + h);
        glVertex2d(x + w, y);
        glEnd();
        glPopMatrix();
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_LINE_SMOOTH);
    }


    public static void drawGradientRect(int x, int y, int w, int h, int startColor, int endColor) {

        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double) x + w, (double) y, (double) 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double) x, (double) y, (double) 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double) x, (double) y + h, (double) 0).color(f5, f6, f7, f4).endVertex();
        vertexbuffer.pos((double) x + w, (double) y + h, (double) 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawBorderedRect(final double x, final double y, final double w, final double h, final float lineWidth, final int color, final int color1) {

        drawRect(x, y, w, h, color1);

        float f = (float) (color >> 24 & 255) / 255.0F;
        float f1 = (float) (color >> 16 & 255) / 255.0F;
        float f2 = (float) (color >> 8 & 255) / 255.0F;
        float f3 = (float) (color & 255) / 255.0F;

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glLineWidth(lineWidth);

        glBegin(GL_LINE_LOOP);

        glColor4f(f1, f2, f3, f);

        glVertex3d(x, y, 1);
        glVertex3d(x + w, y, 1);
        glVertex3d(x + w, y + h, 1);
        glVertex3d(x, y + h, 1);

        glEnd();

//        glDisable(GL_BLEND);
//        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);

    }

    public static void drawTri(double x1, double y1, double x2, double y2, double x3, double y3, double width, Color c) {

        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_LINE_SMOOTH);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        setColor(c);
        glLineWidth((float) width);
        glBegin(GL_LINE_STRIP);
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glVertex2d(x3, y3);
        glEnd();
        glDisable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    public static void setColor(Color c) {

        glColor4d(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
    }


}
