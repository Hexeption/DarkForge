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

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.GuiModList;
import uk.co.hexeption.darkforge.ClientInfo;
import uk.co.hexeption.darkforge.gui.alt.AltManager;
import uk.co.hexeption.darkforge.gui.screen.utils.Panorama;
import uk.co.hexeption.darkforge.utils.render.Texture;

import java.io.IOException;

public class DarkForgeMainMenu extends GuiScreen {

    private final Texture title = new Texture("textures/title.png");

    private Panorama panorama = new Panorama(width, height);

    @Override
    public void initGui() {

        panorama.init();
        panorama.updateSize(width, height);
        int y = this.height / 4 + 48;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, y, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, y + 24 * 1, I18n.format("menu.multiplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, y + 24 * 2, 98, 20, "Mods"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, y + 24 * 2, 98, 20, "Alt Manager"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, y + 72, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 2, y + 72, 98, 20, I18n.format("menu.quit")));
        this.buttonList.add(new GuiButtonLanguage(6, this.width / 2 - 124, y + 72));
    }

    @Override
    public void updateScreen() {

        super.updateScreen();
        panorama.update();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiWorldSelection(this));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiModList(this));
                break;
            case 3:
                this.mc.displayGuiScreen(new AltManager(this));
                break;
            case 4:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 5:
                this.mc.shutdown();
                break;
            case 6:
                this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        GlStateManager.disableAlpha();
        panorama.renderSkybox(mouseX, mouseY, partialTicks);
        GlStateManager.enableAlpha();

        float titleX = width / 2 - 150, titleY = 20;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        title.render(titleX, titleY + 10, 300, 100);
        drawString(fontRendererObj, ClientInfo.VERSION_BUILD, width - fontRendererObj.getStringWidth(ClientInfo.VERSION_BUILD) - 2, height - 12, 0xffffff);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
