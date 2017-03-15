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

import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.movement.PostMotionUpdateEvent;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mod.ModInfo(name = "No Slowdown", description = "Stops items from slowing you down.", category = Mod.Category.MOVEMENT)
public class NoSlowdown extends Mod {

    @EventTarget
    public void PostMotionUpdate(PostMotionUpdateEvent event) {

    }


}
