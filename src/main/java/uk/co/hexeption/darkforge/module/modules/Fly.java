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
@Module.ModInfo(name = "Fly", description = "Be like SuperGirl <3", category = Module.Category.MOVEMENT, bind = Keyboard.KEY_F)
public class Fly extends Module {

    @Override
    @SideOnly(Side.CLIENT)
    public void onEnable() {

        getPlayer().capabilities.isFlying = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDisable() {

        getPlayer().capabilities.isFlying = false;
    }

    @Override
    public void onWorldTick() {

        if (!getPlayer().capabilities.isFlying) {
            getPlayer().capabilities.isFlying = true;
        }
    }
}
