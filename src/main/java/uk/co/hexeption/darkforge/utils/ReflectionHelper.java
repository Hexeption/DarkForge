package uk.co.hexeption.darkforge.utils;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;

import java.lang.reflect.Field;

/**
 * Huzuni
 */
public class ReflectionHelper {

    private static Field persistantChatGUI;

    static {
        persistantChatGUI = setAccessible(GuiIngame.class, "field_73840_e", "persistantChatGUI");
    }

    private static Field setAccessible(Class clazz, String obfName, String name) {

        try {
            Field field = net.minecraftforge.fml.relauncher.ReflectionHelper.findField(clazz, obfName, name);
            field.setAccessible(true);
            return field;
        } catch (net.minecraftforge.fml.relauncher.ReflectionHelper.UnableToFindFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setPersistantChatGUI(GuiIngame gui, GuiNewChat chatGUI) {

        try {
            persistantChatGUI.set(gui, chatGUI);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
