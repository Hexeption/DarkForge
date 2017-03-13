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

package uk.co.hexeption.darkforge.mod.mods;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.mod.Mod;

@SideOnly(Side.CLIENT)
@Mod.ModInfo(name = "Auto Sprint", description = "Automatically Sprints for you.", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_S)
public class AutoSprint extends Mod {

    @Override
    public void onEnable() {

        getPlayer().setSprinting(true);
    }

    @Override
    public void onDisable() {

        getPlayer().setSprinting(false);
    }

//    @Override
//    public void onWorldTick() {
//
//        if ((!mc.player.isCollidedHorizontally) && (mc.player.moveForward > 0.0F) && (!mc.player.isSneaking())) {
//            mc.player.setSprinting(true);
//        }
//
//    }
}
