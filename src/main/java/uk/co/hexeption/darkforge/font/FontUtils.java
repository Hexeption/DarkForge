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

package uk.co.hexeption.darkforge.font;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

public class FontUtils {

    private static final Tessellator tessellator = Tessellator.getInstance();

    public static void drawTextureRect(float x, float y, float width, float height, float u, float v, float t, float s) {

        VertexBuffer renderer = tessellator.getBuffer();
        renderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
        renderer.pos(x + width, y, 0F).tex(t, v).endVertex();
        renderer.pos(x, y, 0F).tex(u, v).endVertex();
        renderer.pos(x, y + height, 0F).tex(u, s).endVertex();
        renderer.pos(x, y + height, 0F).tex(u, s).endVertex();
        renderer.pos(x + width, y + height, 0F).tex(t, s).endVertex();
        renderer.pos(x + width, y, 0F).tex(t, v).endVertex();
        tessellator.draw();
    }

    public static void drawTextureRect(float x, float y, float width, float height, float u, float v, float t, float s, float z) {

        VertexBuffer renderer = tessellator.getBuffer();
        renderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
        renderer.pos(x + width, y, z).tex(t, v).endVertex();
        renderer.pos(x, y, z).tex(u, v).endVertex();
        renderer.pos(x, y + height, z).tex(u, s).endVertex();
        renderer.pos(x, y + height, z).tex(u, s).endVertex();
        renderer.pos(x + width, y + height, z).tex(t, s).endVertex();
        renderer.pos(x + width, y, z).tex(t, v).endVertex();
        tessellator.draw();
    }

    /**
     * Renders a line from the given x, y positions to the second x1, y1 positions.
     */
    public static void drawLine(float size, float x, float y, float x1, float y1) {

        glLineWidth(size);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        VertexBuffer vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(GL_LINES, DefaultVertexFormats.POSITION);
        vertexBuffer.pos(x, y, 0F).endVertex();
        vertexBuffer.pos(x1, y1, 0F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
