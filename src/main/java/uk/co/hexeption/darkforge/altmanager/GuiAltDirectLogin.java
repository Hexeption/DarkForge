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

package uk.co.hexeption.darkforge.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.screen.gui.DarkForgeMainMenu;
import uk.co.hexeption.darkforge.utils.GuiPasswordField;
import uk.co.hexeption.darkforge.utils.LoginUtils;

import java.io.IOException;

public class GuiAltDirectLogin extends GuiScreen {
    public static String login = "";
    private GuiScreen lastScreen;
    private GuiTextField email;
    private GuiPasswordField passwordField;

    public GuiAltDirectLogin(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.clear();
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 60 + 10, "Login"));
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 72 + 22, "Back"));
        email = new GuiTextField(0, fontRendererObj, width / 2 - 100, 60, 200, 20);
        email.setMaxStringLength(60);
        email.setFocused(true);
        email.setText(Minecraft.getMinecraft().getSession().getUsername());
        passwordField = new GuiPasswordField(fontRendererObj, width / 2 - 100, 100, 200, 20);
        passwordField.setFocused(false);
        passwordField.func_146203_f(150);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        email.textboxKeyTyped(typedChar, keyCode);
        passwordField.textboxKeyTyped(typedChar, keyCode);

        if (keyCode == 28 || keyCode == 156) {
            actionPerformed((GuiButton) buttonList.get(1));
        }
    }

    @Override
    public void updateScreen() {
        email.updateCursorCounter();
        passwordField.updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        email.mouseClicked(mouseX, mouseY, mouseButton);
        passwordField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                if (passwordField.getText().length() == 0) {
                    LoginUtils.changeCrackedName(email.getText());
                    login = "";
                } else {
                    login = LoginUtils.loginAlt(email.getText(), passwordField.getText());

                    if (login.equals("")) {
                        mc.displayGuiScreen(new DarkForgeMainMenu());
                    }
                }

            case 1:
                mc.displayGuiScreen(lastScreen);
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        DarkForge.FONT_MANAGER.hud.drawCenteredString("Direct Login", width / 2, 15, 16777215);
        DarkForge.FONT_MANAGER.hud.drawCenteredString(login, width / 2, 30, 16777215);
        DarkForge.FONT_MANAGER.hud.drawString("Name or E-Mail", width / 2 - 100, 47, 10526880);
        DarkForge.FONT_MANAGER.hud.drawString("Password", width / 2 - 100, 87, 10526880);
        email.drawTextBox();
        passwordField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}