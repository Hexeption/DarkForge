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
import uk.co.hexeption.darkforge.ui.hud.Hud;

@SideOnly(Side.CLIENT)
public enum DarkForge {
    INSTANCE;

    public final ModManager modManager = new ModManager();

    public final CommandManager commandManager = new CommandManager();

    public final FontManager fontManager = new FontManager();

    public final FileManager fileManager = new FileManager();

    public final GuiManager guiManager = new GuiManager();

    public final Hud hud = new Hud();

    public String commandPrefix = "#";

    DarkForge() {

        EventManager.register(this);

    }

    public void start() {

        Minecraft mc = Minecraft.getMinecraft();
        mc.ingameGUI = new DarkForgeInGameGui(mc);

        LogHelper.info("Loading Modules...");
        modManager.Initialization();

        LogHelper.info("Loading Commands...");
        commandManager.Initialization();


        LogHelper.info("Loading Fonts...");
        fontManager.Initialization();

        LogHelper.info("Loading Hud...");
        hud.Initialization();

        guiManager.Initialization();

        LogHelper.info("Loading Config...");
        fileManager.Initialization();

    }

    public void end() {

    }

    @EventTarget
    private void EventKeyboard(KeyboardEvent event) {

        for (Mod m : modManager.getMods()) {
            if (Keyboard.getEventKey() == m.getBind()) {
                m.toggle();
                LogHelper.info("Toggle");
            }
        }
    }

}
