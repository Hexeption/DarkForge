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
package uk.co.hexeption.darkforge.mod.mods.render;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderWorld;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.waypoint.Waypoint;

/**
 * Created by Keir on 23/04/2017.
 */
@Mod.ModInfo(name = "Waypoints", description = "TODO", category = Mod.Category.RENDER, bind = Keyboard.KEY_B)
public class Waypoints extends Mod {

    @Override
    public void onEvent(Event event) {

        if (getState() && event.getType() == Event.Type.PRE) {
            if (event instanceof EventRenderWorld) {
                for (int i = 0; i < DarkForge.INSTANCE.waypointManager.getWaypoints().size(); i++) {
                    Waypoint waypoint = DarkForge.INSTANCE.waypointManager.getWaypoints().get(i);
                    if (waypoint.dimension != mc.player.dimension) {
                        continue;
                    }
                    RenderUtils.drawTag(waypoint.getDescription(), waypoint.position.getX() - mc.getRenderManager().viewerPosX, waypoint.position.getY() - mc.getRenderManager().viewerPosY, waypoint.position.getZ() - mc.getRenderManager().viewerPosZ, 0.02f, waypoint.color);
                }
            }
        }
    }
}
