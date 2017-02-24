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

/**
 * Renders strings at given x,y positions with the given hexadecimal color.
 * Created by halalaboos.
 */
public interface FontRenderer {

    /**
     * @return The width (in pixels) of the text rendered.
     */
    int drawString(FontData fontData, String text, int x, int y, int color);

    /**
     * @return The width (in pixels) of the text rendered.
     */
    int drawString(String text, int x, int y, int color);

    /**
     * @return The {@link FontData} used by this FontRenderer.
     */
    FontData getFontData();

}
