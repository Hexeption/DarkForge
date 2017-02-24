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

    public MinecraftFontRenderer hud = new MinecraftFontRenderer();

    public MinecraftFontRenderer arraylist = new MinecraftFontRenderer();

    public MinecraftFontRenderer mainMenu = new MinecraftFontRenderer();

    public MinecraftFontRenderer button = new MinecraftFontRenderer();

    public MinecraftFontRenderer buttonHoverd = new MinecraftFontRenderer();

    public MinecraftFontRenderer chat = new MinecraftFontRenderer();

    public MinecraftFontRenderer guiTitle = new MinecraftFontRenderer();


    public FontManager() {

        Initialization();

    }

    public static String getFontName() {

        return fontName;
    }

    public static void setFontName(String fontName) {

        FontManager.fontName = fontName;
        DarkForge.instance.FONT_MANAGER.Initialization();
    }

    public void Initialization() {

        hud.setFont(new Font(fontName, Font.PLAIN, 22), true);
        arraylist.setFont(new Font(fontName, Font.PLAIN, 18), true);
        mainMenu.setFont(new Font(fontName, Font.PLAIN, 50), true);
        button.setFont(new Font(fontName, Font.PLAIN, 22), true);
        buttonHoverd.setFont(new Font(fontName, Font.PLAIN, 25), true);
        chat.setFont(new Font("Verdana", Font.PLAIN, 17), true);
        guiTitle.setFont(new Font(fontName, Font.PLAIN, 17), true);

    }
}
