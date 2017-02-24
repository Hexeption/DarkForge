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
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.ui.hud.IGameHud;

public class DarkForgeHud implements IGameHud {

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {
        DarkForge.FONT_MANAGER.hud.drawString("TEST", 100, 100, 0xffffff);
    }

    @Override
    public String name() {
        return "DarkForge";
    }

    @Override
    public void onKeyPressed(int key) {

    }
}
