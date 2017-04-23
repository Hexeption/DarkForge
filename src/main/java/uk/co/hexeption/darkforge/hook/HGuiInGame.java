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
package uk.co.hexeption.darkforge.hook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderScreen;
import uk.co.hexeption.darkforge.gui.screen.DarkForgeChat;
import uk.co.hexeption.darkforge.managers.EventManager;
import uk.co.hexeption.darkforge.utils.ReflectionHelper;

/**
 * Created by Keir on 22/04/2017.
 */
public class HGuiInGame extends GuiIngame {

    private final Minecraft mc;

    public HGuiInGame(Minecraft mc) {

        super(mc);
        this.mc = mc;
        ReflectionHelper.setPersistantChatGUI(this, new DarkForgeChat(mc));

    }

    @Override
    public void renderGameOverlay(float partialTicks) {

        super.renderGameOverlay(partialTicks);

        GlStateManager.pushMatrix();
        EventRenderScreen event = new EventRenderScreen(Event.Type.POST, mc.displayWidth, mc.displayHeight);
        EventManager.handleEvent(event);
        GlStateManager.popMatrix();
    }

    @Override
    protected void renderPotionEffects(ScaledResolution resolution) {

    }
}
