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

package uk.co.hexeption.darkforge.gui.alt;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.alt.Alt;
import uk.co.hexeption.darkforge.utils.LoginUtils;

import java.io.IOException;

public class AltManager extends GuiScreen {

    private static AltsSlot altslot;

    private GuiScreen lastScreen;

    private String login;

    public AltManager(GuiScreen lastScreen) {

        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {

        super.initGui();
        login = "";
        altslot = new AltsSlot(this.mc, this);
        altslot.registerScrollButtons(7, 8);
        altslot.elementClicked(-1, false, 0, 0);
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 111, height - 25, 46, 20, I18n.format("gui.done", new Object[0])));
        buttonList.add(new GuiButton(1, width / 2 - 60, height - 25, 120, 20, "Direct Login"));
        buttonList.add(new GuiButton(2, width / 2 + 65, height - 25, 46, 20, "Add"));
        buttonList.add(new GuiButton(3, width / 2 + 65 + 50, height - 25, 46, 20, "Delete"));
        buttonList.add(new GuiButton(4, width / 2 - 65 - 97, height - 25, 46, 20, "Login"));
        buttonList.add(new GuiButton(5, width / 2 - 65 - 147, height - 25, 46, 20, "Proxy"));
        buttonList.add(new GuiButton(6, width / 2 + 65 + 100, height - 25, 46, 20, "Star"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case 0:
                mc.displayGuiScreen(lastScreen);
                break;

            case 1:
                mc.displayGuiScreen(new GuiAltDirectLogin(this));
                break;

            case 2:
                mc.displayGuiScreen(new GuiAltAdd(this));
                break;

            case 3:
                Alt deletedalt = AltsSlot.alts.get(altslot.getSelectedSlots());
                String question = "Are you sure you would like to remove this alt?";
                String warning = "[" + deletedalt.getName() + "] will be deleted forever";
                mc.displayGuiScreen(new GuiYesNo(this, question, warning, "Delete", "Cancel", 0));
                break;

            case 4:
                Alt loginAlt = AltsSlot.alts.get(altslot.getSelectedSlots());

                if (loginAlt.isCracked()) {
                    LoginUtils.changeCrackedName(loginAlt.getName());
                    this.mc.displayGuiScreen(lastScreen);
                    DarkForge.FILE_MANAGER.saveAlts();
                } else {
                    login = LoginUtils.loginAlt(loginAlt.getEmail(), loginAlt.getPassword());

                    if (login.equals("")) {
                        this.mc.displayGuiScreen(lastScreen);
                        DarkForge.FILE_MANAGER.saveAlts();
                    } else {
                        if (login.equals("§4§lWrong password!")) {
                            AltsSlot.alts.remove(altslot.getSelectedSlots());
                        }
                    }
                }

                break;
            case 5:
//                mc.displayGuiScreen(new GuiProxy(this));
                break;
            case 6:
                Alt alt = AltsSlot.alts.get(altslot.getSelectedSlots());
                alt.setFavourites(!alt.isFavourites());
                AltsSlot.sortAlts();
                DarkForge.FILE_MANAGER.saveAlts();
                break;
        }
    }

    @Override
    public void confirmClicked(boolean result, int id) {

        if (id == 0) {
            if (result) {
                AltsSlot.alts.remove(altslot.getSelectedSlots());
            }
        }

        mc.displayGuiScreen(this);
    }

    @Override
    public void updateScreen() {

        ((GuiButton) buttonList.get(3)).enabled = !AltsSlot.alts.isEmpty() && altslot.getSelectedSlots() != -1;
        ((GuiButton) buttonList.get(4)).enabled = !AltsSlot.alts.isEmpty() && altslot.getSelectedSlots() != -1;
        ((GuiButton) buttonList.get(6)).enabled = !AltsSlot.alts.isEmpty() && altslot.getSelectedSlots() != -1;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {

        if (keyCode == 28 || keyCode == 156) {
            actionPerformed((GuiButton) buttonList.get(0));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        if (mouseY >= 36 && mouseY <= height - 46)
            if (mouseX >= width / 2 + 140 || mouseX <= width / 2 - 126) {
                altslot.elementClicked(-1, false, 0, 0);
            }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void handleMouseInput() throws IOException {

        super.handleMouseInput();
        altslot.handleMouseInput();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        super.drawDefaultBackground();
        altslot.drawScreen(mouseX, mouseY, partialTicks);
        DarkForge.FONT_MANAGER.hud.drawCenteredString("Account Manager", width / 2, 2, 16777215);
        DarkForge.FONT_MANAGER.hud.drawCenteredString(login + AltsSlot.login, width / 2, 14, 16777215);
        DarkForge.FONT_MANAGER.hud.drawCenteredString("§aCurrently signed in as " + this.mc.getSession().getUsername(), width / 2, height - 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
