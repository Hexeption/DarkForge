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

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;

import java.awt.*;

/**
 * Created by Hexeption on 18/12/2016.
 */
@SideOnly(Side.CLIENT)
public class FontManager {

    private static String fontName = "Comfortaa";
    public MinecraftFontRenderer hud = null;
    public MinecraftFontRenderer arraylist = null;
    public MinecraftFontRenderer mainMenu = null;
    public MinecraftFontRenderer button = null;
    public MinecraftFontRenderer buttonHoverd = null;
    public MinecraftFontRenderer chat = null;
    public MinecraftFontRenderer guiTitle = null;

    public FontManager() {

        loadFonts();

    }

    public static String getFontName() {

        return fontName;
    }

    public static void setFontName(String fontName) {

        FontManager.fontName = fontName;
        DarkForge.instance.FONT_MANAGER.loadFonts();
    }

    public void loadFonts() {

        hud = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 22), true, true);
        arraylist = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 18), true, true);
        mainMenu = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 50), true, true);
        button = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 22), true, true);
        buttonHoverd = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 25), true, true);
        chat = new MinecraftFontRenderer(new Font("Verdana", Font.PLAIN, 17), true, true);
        guiTitle = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 17), true, true);

    }
}
