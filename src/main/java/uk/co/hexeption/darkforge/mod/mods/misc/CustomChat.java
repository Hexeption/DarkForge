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

package uk.co.hexeption.darkforge.mod.mods.misc;

import uk.co.hexeption.darkforge.api.annotation.NoKeyBind;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 15/03/2017.
 */
@NoKeyBind
@Mod.ModInfo(name = "Custom Chat", description = "Custom font in chat", category = Mod.Category.MISC, visable = false)
public class CustomChat extends Mod {

    @Override
    public void onEvent(Event event) {

    }
}
