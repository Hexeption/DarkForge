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
import net.minecraft.client.gui.GuiIngame;
import uk.co.hexeption.darkforge.event.events.render.Render2DEvent;
import uk.co.hexeption.darkforge.utils.ReflectionHelper;

/**
 * Created by Hexeption on 12/03/2017.
 */
public class DarkForgeInGameGui extends GuiIngame {

    private final Minecraft mc;

    public DarkForgeInGameGui(Minecraft mc) {

        super(mc);
        this.mc = mc;
        ReflectionHelper.setPersistantChatGUI(this, new DarkForgeChat(mc));
    }

    @Override
    public void renderGameOverlay(float partialTicks) {

        super.renderGameOverlay(partialTicks);

        if (!mc.gameSettings.showDebugInfo) {
            Render2DEvent eventRender2D = new Render2DEvent(mc.displayWidth, mc.displayHeight);
            eventRender2D.call();
        }
    }
}
