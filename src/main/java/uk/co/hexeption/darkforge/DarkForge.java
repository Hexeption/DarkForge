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
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.gui.gui.ClickGuiScreen;
import uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge.DarkForgeTheme;
import uk.co.hexeption.darkforge.hook.HGuiInGame;
import uk.co.hexeption.darkforge.managers.*;
import uk.co.hexeption.darkforge.notification.Notification;
import uk.co.hexeption.darkforge.ui.hud.Hud;
import uk.co.hexeption.darkforge.ui.tab.Tab;

public enum DarkForge {
    INSTANCE;

    public final ModManager modManager = new ModManager();

    public final CommandManager commandManager = new CommandManager();

    public final FontManager fontManager = new FontManager();

    public final FileManager fileManager = new FileManager();

    public final FriendManager friendManager = new FriendManager();

    public final NotificationManager notificationManager = new NotificationManager();

    public final Hud hud = new Hud();

    public final Tab tab = new Tab();

    public GuiManager guiManager;

    public ClickGuiScreen guiScreen;

    public String commandPrefix = ".";

    public void start() {

        Minecraft mc = Minecraft.getMinecraft();
        mc.ingameGUI = new HGuiInGame(mc);

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

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    public void end() {

        fileManager.saveFriends();
        fileManager.saveAlts();
        fileManager.saveModules();
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

    public ClickGuiScreen getGui() {

        if (DarkForge.INSTANCE.guiManager == null) {
            this.guiManager = new GuiManager();
            this.guiScreen = new ClickGuiScreen();
            ClickGuiScreen.clickGui = this.guiManager;
            DarkForge.INSTANCE.guiManager.Initialization();
            DarkForge.INSTANCE.guiManager.setTheme(new DarkForgeTheme());
        }

        return this.guiManager;
    }


}
