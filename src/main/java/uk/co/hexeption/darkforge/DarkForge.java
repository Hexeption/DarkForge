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

package uk.co.hexeption.darkforge;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.event.EventManager;
import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.other.KeyboardEvent;
import uk.co.hexeption.darkforge.gui.screen.DarkForgeInGameGui;
import uk.co.hexeption.darkforge.managers.*;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.notification.Notification;
import uk.co.hexeption.darkforge.ui.hud.Hud;
import uk.co.hexeption.darkforge.ui.tab.Tab;

@SideOnly(Side.CLIENT)
public enum DarkForge {
    INSTANCE;

    public final ModManager modManager = new ModManager();

    public final CommandManager commandManager = new CommandManager();

    public final FontManager fontManager = new FontManager();

    public final FileManager fileManager = new FileManager();

    public final GuiManager guiManager = new GuiManager();

    public final FriendManager friendManager = new FriendManager();

    public final NotificationManager notificationManager = new NotificationManager();

    public final Hud hud = new Hud();

    public final Tab tab = new Tab();

    public String commandPrefix = ".";

    DarkForge() {

        EventManager.register(this);
    }

    public void start() {

        Minecraft mc = Minecraft.getMinecraft();
        mc.ingameGUI = new DarkForgeInGameGui(mc);

        LogHelper.info("Loading Mods...");
        modManager.Initialization();

        LogHelper.info("Loading Commands...");
        commandManager.Initialization();

        LogHelper.info("Loading Fonts...");
        fontManager.Initialization();

        LogHelper.info("Loading Huds...");
        hud.Initialization();

        LogHelper.info("Loading Tabs...");
        tab.Initialization();

        LogHelper.info("Loading Configs...");
        fileManager.Initialization();

        LogHelper.info("Loading Guis...");
        guiManager.Initialization();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    public void end() {

        fileManager.saveFriends();
        fileManager.saveAlts();
        fileManager.saveModules();
    }

    @EventTarget
    private void EventKeyboard(KeyboardEvent event) {


        if (event.getKey() == Keyboard.KEY_H) {
            addNotification(Notification.Type.ERROR, "this", "Testing Error", 50000);

        }
        if (event.getKey() == Keyboard.KEY_J) {
            addNotification(Notification.Type.INFO, "this", "Testing Info", 50000);

        }
        if (event.getKey() == Keyboard.KEY_L) {
            addNotification(Notification.Type.QUESTION, "this", "Testing Question", 50000);

        }


        for (Mod m : modManager.getMods()) {
            if (Keyboard.getEventKey() == m.getBind()) {
                m.toggle();
            }
        }
    }

    public void addChatMessage(ITextComponent component) {

        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(component);
    }

    public void addChatMessage(String message) {

        addChatMessage(new TextComponentString(TextFormatting.DARK_RED + "[DF] " + TextFormatting.GRAY + message));
    }

    public void addNotification(Notification.Type type, String location, String message, int duration) {

        notificationManager.addNotification(new Notification(type, location, message, duration));
    }
}
