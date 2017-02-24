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

/**
 * Basic implementation of the FontRenderer interface.
 * <p>
 * Created by halalaboos.
 */
public class BasicFontRenderer implements FontRenderer {

    protected final FontData fontData = new FontData();
    protected int kerning = 0;

    public BasicFontRenderer() {
    }

    @Override
    public int drawString(FontData fontData, String text, int x, int y, int color) {
        if (!fontData.hasFont())
            return 0;
        GlStateManager.enableBlend();
        fontData.bind();
        GLUtils.glColor(color);
        int size = text.length();
        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);
            if (fontData.hasBounds(character)) {
                FontData.CharacterData area = fontData.getCharacterBounds(character);
                FontUtils.drawTextureRect(x, y, area.width, area.height,
                        (float) area.x / fontData.getTextureWidth(),
                        (float) area.y / fontData.getTextureHeight(),
                        (float) (area.x + area.width) / fontData.getTextureWidth(),
                        (float) (area.y + area.height) / fontData.getTextureHeight());
                x += area.width + kerning;
            }
        }
        return x;
    }

    public int getHeight() {
        return fontData.getFontHeight();
    }

    @Override
    public int drawString(String text, int x, int y, int color) {
        return drawString(fontData, text, x, y, color);
    }

    public int getKerning() {
        return kerning;
    }

    public void setKerning(int kerning) {
        this.kerning = kerning;
    }

    @Override
    public FontData getFontData() {
        return fontData;
    }
}
