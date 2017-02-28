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

package uk.co.hexeption.darkforge.ui.hud.themes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.ClientInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.ui.hud.IGameHud;

import java.text.SimpleDateFormat;
import java.util.Date;

@SideOnly(Side.CLIENT)
public class DarkForgeHud implements IGameHud {

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {

        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        DarkForge.CLICK_GUI.renderPinned();

        DarkForge.FONT_MANAGER.hud.drawStringWithShadow(ClientInfo.MOD_NAME + " for " + ClientInfo.MINECRAFT_VERISON, 1, 5, -1);
        DarkForge.FONT_MANAGER.hud.drawStringWithShadow("§7Time:§r " + new SimpleDateFormat("hh:mm a").format(new Date()), 1, 17, -1);
        DarkForge.FONT_MANAGER.hud.drawStringWithShadow("§7FPS:§r " + minecraft.getDebugFPS(), 1, 29, -1);

        if (!minecraft.isSingleplayer()) {
            int version = 15, ping = 25;
            if (minecraft.currentScreen instanceof GuiChat) {
                version += 15;
                ping += 15;
            }

            DarkForge.FONT_MANAGER.hud.drawStringWithShadow("§7Version:§r " + minecraft.getCurrentServerData().gameVersion, 1, scaledResolution.getScaledHeight() - version, -1);
            DarkForge.FONT_MANAGER.hud.drawStringWithShadow("§7Ping:§r " + minecraft.getCurrentServerData().pingToServer + "ms", 1, scaledResolution.getScaledHeight() - ping, -1);
        }

        drawArrayList(scaledResolution);
    }

    void drawArrayList(ScaledResolution scaledResolution) {

        int yCount = 5;
        for (Module module : DarkForge.MODULE_MANAGER.getModules()) {
            if (module.getState() && module.getCategory() != Module.Category.GUI) {
                DarkForge.FONT_MANAGER.arraylist.drawStringWithShadow(module.getName(), (scaledResolution.getScaledWidth() - 3) - DarkForge.FONT_MANAGER.arraylist.getStringWidth(module.getName()), yCount, module.getCategory().color);
                yCount += 10;
            }
        }
    }

    @Override
    public String name() {

        return "DarkForge";
    }

    @Override
    public void onKeyPressed(int key) {

    }
}
