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

package uk.co.hexeption.darkforge.utils.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by halalaboos.
 */
@SideOnly(Side.CLIENT)
public final class GLUtils {

    private static final Random random = new Random();

    public static List<Integer> vbos = new ArrayList<>(), textures = new ArrayList<>();

    public static void glScissor(int[] rect) {

        glScissor(rect[0], rect[1], rect[0] + rect[2], rect[1] + rect[3]);
    }

    public static void glScissor(float x, float y, float x1, float y1) {

        int scaleFactor = getScaleFactor();
        GL11.glScissor((int) (x * scaleFactor), (int) (Minecraft.getMinecraft().displayHeight - (y1 * scaleFactor)), (int) ((x1 - x) * scaleFactor), (int) ((y1 - y) * scaleFactor));
    }

    /**
     * @return The scale factor used by the play's screen gui scale
     */
    public static int getScaleFactor() {

        int scaleFactor = 1;
        boolean isUnicode = Minecraft.getMinecraft().isUnicode();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;

        if (guiScale == 0) {
            guiScale = 1000;
        }

        while (scaleFactor < guiScale && Minecraft.getMinecraft().displayWidth / (scaleFactor + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (scaleFactor + 1) >= 240) {
            scaleFactor++;
        }

        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            scaleFactor--;
        }

        return scaleFactor;

    }

    /**
     * @return Mouse X cord.
     */
    public static int getMouseX() {

        return (Mouse.getX() * getScreenWidth() / Minecraft.getMinecraft().displayWidth);
    }

    /**
     * @return Mouse Y cord.
     */
    public static int getMouseY() {

        return (getScreenHeight() - Mouse.getY() * getScreenHeight() / Minecraft.getMinecraft().displayWidth - 1);
    }

    /**
     * @return Screen width with gui scale.
     */
    public static int getScreenWidth() {

        return Minecraft.getMinecraft().displayWidth / getScaleFactor();
    }

    /**
     * @return Screen height with gui scale.
     */
    public static int getScreenHeight() {

        return Minecraft.getMinecraft().displayHeight / getScaleFactor();
    }

    public static int genVBO() {

        int id = GL15.glGenBuffers();
        vbos.add(id);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
        return id;
    }

    public static int getTexture() {

        int textureID = glGenTextures();
        textures.add(textureID);
        return textureID;
    }

    /**
     * @param filter determines how the texture will interpolate when scaling up / down. <br>
     *               GL_LINEAR - smoothest <br> GL_NEAREST - most accurate <br>
     * @param wrap   determines how the UV coordinates outside of the 0.0F ~ 1.0F range will be handled. <br>
     *               GL_CLAMP_TO_EDGE - samples edge color <br> GL_REPEAT - repeats the texture <br>
     */
    public static int applyTexture(int texId, File file, int filter, int wrap) throws IOException {

        applyTexture(texId, ImageIO.read(file), filter, wrap);
        return texId;
    }

    /**
     * @param filter determines how the texture will interpolate when scaling up / down. <br>
     *               GL_LINEAR - smoothest <br> GL_NEAREST - most accurate <br>
     * @param wrap   determines how the UV coordinates outside of the 0.0F ~ 1.0F range will be handled. <br>
     *               GL_CLAMP_TO_EDGE - samples edge color <br> GL_REPEAT - repeats the texture <br>
     */
    public static int applyTexture(int texId, BufferedImage image, int filter, int wrap) {

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();
        applyTexture(texId, image.getWidth(), image.getHeight(), buffer, filter, wrap);
        return texId;
    }

    /**
     * @param filter determines how the texture will interpolate when scaling up / down. <br>
     *               GL_LINEAR - smoothest <br> GL_NEAREST - most accurate <br>
     * @param wrap   determines how the UV coordinates outside of the 0.0F ~ 1.0F range will be handled. <br>
     *               GL_CLAMP_TO_EDGE - samples edge color <br> GL_REPEAT - repeats the texture <br>
     */
    public static int applyTexture(int texId, int width, int height, ByteBuffer pixels, int filter, int wrap) {

        glBindTexture(GL_TEXTURE_2D, texId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        glBindTexture(GL_TEXTURE_2D, 0);
        return texId;
    }

    public static void cleanup() {

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        glBindTexture(GL_TEXTURE_2D, 0);

        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }

        for (int texture : textures) {
            glDeleteTextures(texture);
        }

    }

    /**
     * Colors
     */

    public static void glColor(float red, float green, float blue, float alpha) {

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void glColor(Color color) {

        GlStateManager.color((float) color.getRed() / 255F, (float) color.getGreen() / 255F, (float) color.getBlue() / 255F, (float) color.getAlpha() / 255F);
    }

    public static void glColor(int color) {

        GlStateManager.color((float) (color >> 16 & 255) / 255F, (float) (color >> 8 & 255) / 255F, (float) (color & 255) / 255F, (float) (color >> 24 & 255) / 255F);
    }

    public static Color getHSBColor(float hue, float sturation, float luminance) {

        return Color.getHSBColor(hue, sturation, luminance);
    }

    public static Color getRandomColor(int saturationRandom, float luminance) {

        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(saturationRandom) + (float) saturationRandom) / (float) saturationRandom + (float) saturationRandom;
        return getHSBColor(hue, saturation, luminance);
    }

    public static Color getRandomColor() {

        return getRandomColor(1000, 0.6f);
    }


}