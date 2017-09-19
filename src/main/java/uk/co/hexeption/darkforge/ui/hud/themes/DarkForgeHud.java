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
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import uk.co.hexeption.darkforge.ClientInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.gui.ClickGui;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.notification.Notification;
import uk.co.hexeption.darkforge.ui.hud.IGameHud;
import uk.co.hexeption.darkforge.utils.TimerUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;
import uk.co.hexeption.darkforge.utils.render.Texture;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DarkForgeHud implements IGameHud {

    public Texture info = new Texture("textures/info.png");

    public Texture error = new Texture("textures/error.png");

    public Texture question = new Texture("textures/question.png");


    private int y;

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {

        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        ClickGui.renderPinned();

        DarkForge.INSTANCE.fontManager.hud_big.drawString("D", 1, 1, -1);
        DarkForge.INSTANCE.fontManager.hud.drawString("ark", 14, 8, -1);

        DarkForge.INSTANCE.fontManager.hud_big.drawString("F", 32, 1, -1);
        DarkForge.INSTANCE.fontManager.hud.drawString("orge", 42, 8, -1);
        DarkForge.INSTANCE.fontManager.hud_small.drawString(ClientInfo.BUILD, 45, 4, -1);

        DarkForge.INSTANCE.fontManager.hud_small.drawString("Time : " + new SimpleDateFormat("hh:mm a").format(new Date()) + " | FPS : " + Minecraft.getDebugFPS(), 1, 22, -1);

        //Array List
        y = 2;
        DarkForge.INSTANCE.modManager.getMods().stream().filter(Mod::getState).sorted(Comparator.comparingInt(m -> -DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(m.getName()))).forEach(mod -> {
            DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(mod.getName(), (scaledResolution.getScaledWidth() - 3) - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(mod.getName()), y, mod.getCategory().color);
            y += 10;
        });


        drawNotifications(scaledResolution);

        GlStateManager.color(255, 255, 255);
    }

    private void drawNotifications(ScaledResolution scaledResolution) {

        TimerUtils timerUtils = new TimerUtils();
        int ycount = 0;
        for (Notification notification : DarkForge.INSTANCE.notificationManager.getNotifications()) {
            if (notification != null) {
                if (timerUtils.getSystemTime() - notification.getTime() >= notification.getDuration()) {
//                    DarkForge.INSTANCE.notificationManager.getNotifications().remove(notification);
                    ycount -= 15;
                } else {
                    switch (notification.getType()) {
                        case INFO:
                            GLUtils.glColor(Color.WHITE);
                            info.render(scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 25, scaledResolution.getScaledHeight() - 22 - ycount, 16, 16);
                            break;
                        case ERROR:
                            GLUtils.glColor(Color.WHITE);
                            error.render(scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 25, scaledResolution.getScaledHeight() - 22 - ycount, 16, 16);
                            break;
                        case QUESTION:
                            GLUtils.glColor(Color.WHITE);
                            question.render(scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 25, scaledResolution.getScaledHeight() - 22 - ycount, 16, 16);
                            break;
                    }

                    DarkForge.INSTANCE.fontManager.arraylist.drawStringWithShadow(notification.getMessage(), scaledResolution.getScaledWidth() - DarkForge.INSTANCE.fontManager.arraylist.getStringWidth(notification.getMessage()) - 10, scaledResolution.getScaledHeight() - 18 - ycount, Color.white.hashCode());
                }
                ycount += 15;
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
