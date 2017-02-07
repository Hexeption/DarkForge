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

package uk.co.hexeption.darkforge.module.modules;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
@Module.ModInfo(name = "Fullbright", description = "Brightens up the game", category = Module.Category.RENDER, bind = Keyboard.KEY_V)
public class Fullbright extends Module {

    @Override
    public void onWorldRender() {

        if (getState())
            if (getGameSettings().gammaSetting < 16) {
                getGameSettings().gammaSetting += 0.5;
            } else if (getGameSettings().gammaSetting > 0.5) {
                if (getGameSettings().gammaSetting < 1f)
                    getGameSettings().gammaSetting = 0.5f;
                else
                    getGameSettings().gammaSetting -= 0.5;
            }
    }

    @Override
    public void onDisable() {

        getGameSettings().gammaSetting = 0.5f;
    }
}
