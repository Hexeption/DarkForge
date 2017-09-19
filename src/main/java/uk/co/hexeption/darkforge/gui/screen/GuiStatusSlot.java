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

package uk.co.hexeption.darkforge.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Hexeption on 09/04/2017.
 */
public class GuiStatusSlot extends GuiSlot {

    private ArrayList<StatusItem> statusItems = new ArrayList();

    public GuiStatusSlot(GuiStatus gui) {

        super(Minecraft.getMinecraft(), gui.width, gui.height, 32, gui.height - 45, 35);

        this.statusItems.add(new StatusItem("Minecraft.net", "minecraft.net"));
        this.statusItems.add(new StatusItem("Mojang.com", "mojang.com"));
        this.statusItems.add(new StatusItem("Sessions", "session.minecraft.net"));
        this.statusItems.add(new StatusItem("Accounts", "account.mojang.com"));
        this.statusItems.add(new StatusItem("Auth", "auth.mojang.com"));
        this.statusItems.add(new StatusItem("Auth Server", "authserver.mojang.com"));
        this.statusItems.add(new StatusItem("Session Server", "sessionserver.mojang.com"));
        this.statusItems.add(new StatusItem("Skins", "skins.minecraft.net"));
        this.statusItems.add(new StatusItem("CDN", "textures.minecraft.net"));
        this.statusItems.add(new StatusItem("API", "api.mojang.com"));
        this.statusItems.add(new StatusItem("Capes", "capesapi.com", "http://capesapi.com/api/ping", "pong"));

        updateList();

    }

    public void updateList() {

        for (StatusItem item : this.statusItems)
            item.check();
    }

    @Override
    protected int getSize() {

        return this.statusItems.size();
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {

        if (isDoubleClick) {
            this.statusItems.get(slotIndex).check();
        }
    }

    @Override
    protected boolean isSelected(int slotIndex) {

        return false;
    }

    @Override
    protected void drawBackground() {

    }

    @Override
    protected void func_192637_a(int i, int i1, int i2, int i3, int i4, int i5, float v) {
        StatusItem item = this.statusItems.get(i);
        mc.ingameGUI.drawCenteredString(mc.fontRendererObj, item.getName(), width / 2, i2 + 2, Color.white.hashCode());
        i2 += 10;
        mc.ingameGUI.drawCenteredString(mc.fontRendererObj, "Status: " + item.getStatus(), width / 2, i2 + 2, Color.white.hashCode());

        i2 += 10;
        mc.ingameGUI.drawCenteredString(mc.fontRendererObj, "Ping: " + item.getPing(), width / 2, i2 + 2, Color.white.hashCode());

    }

}
