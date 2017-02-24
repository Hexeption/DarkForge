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
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

/**
 * Stores font information which is used within a font renderer.
 * Created by Brandon on 9/26/2016.
 */
public final class FontData {

    private final CharacterData[] characterBounds = new CharacterData[256];

    private int texId = -1;

    private int fontHeight = 0;

    private int textureWidth, textureHeight;

    /**
     * Creates a font image and the character locations within the font image.
     */
    public FontData setFont(Font font, boolean antialias) {
        return setFont(font, antialias, antialias, 16, 2);
    }

    /**
     * Creates a font image and the character locations within the font image.
     */
    private FontData setFont(Font font, boolean antialias, boolean fractionalmetrics, int characterCount, int padding) {
        if (texId == -1)
            texId = GLUtils.getTexture();

        // Font metrics can be created from the font without having to create a graphics object.
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);

        int charHeight = 0, positionX = 0, positionY = 0; //, textureWidth = 0, textureHeight = 0

        // We'll be generating the character bounds as well as an appropriate texture width and height for the font to be rendered onto.
        for (int i = 0; i < characterBounds.length; i++) {
            char character = (char) i;

            int height = fontMetrics.getHeight();
            int width = fontMetrics.charWidth(character);

            if (i != 0 && i % characterCount == 0) {
                positionX = padding;
                positionY += charHeight + padding;
                charHeight = 0;
            }

            if (height > charHeight) {
                charHeight = height;
                if (charHeight > fontHeight)
                    fontHeight = charHeight;
            }

            characterBounds[i] = new CharacterData(positionX, positionY, width, height);

            positionX += width + padding;

            // Ensure that our texture can fit the characters.
            if (positionX + width + padding > textureWidth)
                textureWidth = positionX + width + padding;

            if (positionY + height + padding > textureHeight)
                textureHeight = positionY + height + padding;
        }

        // Image we'll use to store our font onto for rendering.
        BufferedImage bufferedImage = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setFont(font);
        fontMetrics = graphics2D.getFontMetrics(font);

        // Give blank background
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, textureWidth, textureHeight);

        // Set color to white for rendering the font onto the texture.
        graphics2D.setColor(Color.WHITE);

        // Set render hints
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalmetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antialias ? RenderingHints.VALUE_TEXT_ANTIALIAS_GASP : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, antialias ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY : RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);

        for (int i = 0; i < characterBounds.length; i++) {
            // Draw the char onto the final image we'll be using to render this font.
            graphics2D.drawString(String.valueOf((char) i), characterBounds[i].x, characterBounds[i].y + fontMetrics.getAscent());
        }

        GLUtils.applyTexture(texId, bufferedImage, antialias ? GL_LINEAR : GL_NEAREST, GL_REPEAT);
        return this;
    }

    /**
     * Binds the font texture.
     */
    public void bind() {
        GlStateManager.bindTexture(texId);
    }

    /**
     * @return The bounds of the character within the font image.
     */
    public CharacterData getCharacterBounds(char character) {
        return characterBounds[character];
    }

    /**
     * @return The total width of each character within the string.
     */
    public int getStringWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += characterBounds[c].width;
        }
        return width;
    }

    /**
     * @return True if the character has been mapped in this font.
     */
    public boolean hasBounds(char character) {
        return character >= 0 && character < 256;
    }

    /**
     * @return True if the font has not been set.
     */
    public boolean hasFont() {
        return texId != -1;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    /**
     * Character information regarding it's position within the font texture and the character's width/height within the font.
     */
    public class CharacterData {

        public final int x;
        public final int y;
        public final int width;
        public final int height;

        public CharacterData(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

}
