package uk.co.hexeption.darkforge.ttf;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;

import java.awt.*;

/**
 * Created by Hexeption on 18/12/2016.
 */
@SideOnly(Side.CLIENT)
public class FontManager {

    public MinecraftFontRenderer hud = null;
    public MinecraftFontRenderer arraylist = null;
    public MinecraftFontRenderer mainMenu = null;
    public MinecraftFontRenderer button = null;
    public MinecraftFontRenderer buttonHoverd = null;
    public MinecraftFontRenderer chat = null;


    private static String fontName = "Comfortaa";

    public FontManager() {
        loadFonts();

    }

    public void loadFonts(){
        hud = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 22) , true, true);
        arraylist = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 18) , true, true);
        mainMenu = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 50) , true, true);
        button = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 22) , true, true);
        buttonHoverd = new MinecraftFontRenderer(new Font(fontName, Font.PLAIN, 25) , true, true);
        chat = new MinecraftFontRenderer(new Font("Verdana", Font.PLAIN, 17) , true, true);
    }

    public static String getFontName() {

        return fontName;
    }

    public static void setFontName(String fontName) {
        FontManager.fontName = fontName;
        DarkForge.getInstance().getFontManager().loadFonts();
    }
}
