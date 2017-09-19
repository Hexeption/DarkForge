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

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/**
 * Created by Hexeption on 09/04/2017.
 */
public class GuiStatus extends GuiScreen {

    private GuiScreen lastScreen;

    private GuiStatusSlot slots;


    public GuiStatus(GuiScreen lastScreen) {

        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {

        this.slots = new GuiStatusSlot(this);
        this.slots.registerScrollButtons(7, 8);
        this.slots.elementClicked(-1, false, 0, 0);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, width / 2 - 67, height - 26, 65, 20, "Back"));
        this.buttonList.add(new GuiButton(1, width / 2 + 2, height - 26, 65, 20, "Refresh"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        this.slots.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, "Status", this.width / 2, 6, 16777215);
        this.drawCenteredString(this.fontRenderer, "Check if a certain services is online.", this.width / 2, 16, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);

    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {

        if (keyCode == 28 || keyCode == 156) {
            actionPerformed(buttonList.get(0));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        if (mouseY >= 36 && mouseY <= height - 46)
            if (mouseX >= width / 2 + 140 || mouseX <= width / 2 - 126) {
                this.slots.elementClicked(-1, false, 0, 0);
            }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void handleMouseInput() throws IOException {

        super.handleMouseInput();
        this.slots.handleMouseInput();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(lastScreen);
                break;
            case 1:
                this.slots.updateList();
                break;
        }
    }
}
