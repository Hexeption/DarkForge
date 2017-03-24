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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.ClientInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.gui.ClickGui;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.notification.Notification;
import uk.co.hexeption.darkforge.ui.hud.IGameHud;
import uk.co.hexeption.darkforge.utils.TimerUtils;
import uk.co.hexeption.darkforge.utils.render.Texture;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

@SideOnly(Side.CLIENT)
public class DarkForgeHud implements IGameHud {

    public Texture icons = new Texture("textures/icons.png");

    private ArrayList<Mod> orderdMods = new ArrayList<>();

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {

        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        ClickGui.renderPinned();

        DarkForge.INSTANCE.fontManager.hud.drawStringWithShadow(ClientInfo.MOD_NAME + " for " + ClientInfo.MINECRAFT_VERISON, 1, 5, -1);
        DarkForge.INSTANCE.fontManager.hud.drawStringWithShadow("§7Time:§r " + new SimpleDateFormat("hh:mm a").format(new Date()), 1, 17, -1);
        DarkForge.INSTANCE.fontManager.hud.drawStringWithShadow("§7FPS:§r " + Minecraft.getDebugFPS(), 1, 29, -1);

        if (!minecraft.isSingleplayer()) {
            int version = 15, ping = 25;
            if (minecraft.currentScreen instanceof GuiChat) {
                version += 15;
                ping += 15;
            }

            DarkForge.INSTANCE.fontManager.hud.drawStringWithShadow("§7Version:§r " + minecraft.getCurrentServerData().gameVersion, 1, scaledResolution.getScaledHeight() - version, -1);
            DarkForge.INSTANCE.fontManager.hud.drawStringWithShadow("§7Ping:§r " + minecraft.getCurrentServerData().pingToServer + "ms", 1, scaledResolution.getScaledHeight() - ping, -1);
        }

        reorderMods(0);
        drawArrayList(scaledResolution);
        drawNotifcations(scaledResolution);

        GlStateManager.color(255, 255, 255);
    }

    private void drawArrayList(ScaledResolution scaledResolution) {

        if (this.orderdMods.isEmpty()) {
            this.orderdMods.addAll(DarkForge.INSTANCE.modManager.getMods());
        }

        int yCount = 5;
        for (Mod mod : this.orderdMods) {
            if (mod.getState() && mod.getCategory() != Mod.Category.GUI && mod.isVisable()) {
                DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(mod.getName(), (scaledResolution.getScaledWidth() - 3) - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(mod.getName()), yCount, mod.getCategory().color);
                yCount += 10;
            }
        }
    }

    private void drawNotifcations(ScaledResolution scaledResolution) {

        TimerUtils timerUtils = new TimerUtils();
        int ycount = 0;
        for (Notification notification : DarkForge.INSTANCE.notificationManager.getNotifications()) {
            if (notification != null) {
                if (timerUtils.getSystemTime() - notification.getTime() >= notification.getDuration()) {
                    DarkForge.INSTANCE.notificationManager.getNotifications().remove(notification);
                    ycount -= 15;
                } else {
                    DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(notification.getType().type, scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 20, scaledResolution.getScaledHeight() - 20 - ycount, notification.getType().color);
                    DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(notification.getMessage(), scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 10, scaledResolution.getScaledHeight() - 20 - ycount, Color.white.hashCode());
                }
                ycount += 15;
            }
        }
    }

    private void reorderMods(int sort) {

        ArrayList<Mod> mods = this.orderdMods;

        switch (sort) {
            case 0:
                mods.sort((o1, o2) -> {

                    if (DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(o1.getName()) > DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(o2.getName())) {
                        return -1;
                    }
                    if (DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(o1.getName()) < DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(o2.getName())) {
                        return 1;
                    }

                    return 0;
                });
                break;
            case 1:
                mods.sort(Comparator.comparing(Mod::getName));
                break;
            case 2:
                mods.sort(Comparator.comparingInt(o -> o.getCategory().ordinal()));

                break;
        }

        this.orderdMods = mods;
    }

    @Override
    public String name() {

        return "DarkForge";
    }

    @Override
    public void onKeyPressed(int key) {

    }
}
