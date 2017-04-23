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
