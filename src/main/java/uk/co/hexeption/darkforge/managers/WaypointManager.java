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
package uk.co.hexeption.darkforge.managers;

import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.waypoint.Waypoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keir on 23/04/2017.
 */
public class WaypointManager {

    private final List<Waypoint> waypoints = new ArrayList<>();

    public boolean addWaypoint(Waypoint waypoint) {

        boolean added = waypoints.add(waypoint);
        if (added) {
            DarkForge.INSTANCE.fileManager.saveWaypoints();
        }
        return added;

    }

    public boolean removeWaypoint(Waypoint waypoint) {

        boolean removed = waypoints.remove(waypoint);
        if (removed) {
            DarkForge.INSTANCE.fileManager.saveWaypoints();

        }

        return removed;
    }

    public boolean removeWaypoint(String name) {

        Waypoint waypoint = find(name);
        if (waypoint != null) {
            return removeWaypoint(waypoint);
        }
        return false;
    }

    public Waypoint find(String name) {

        for (Waypoint waypoint : waypoints) {
            if (waypoint.name.equalsIgnoreCase(name)) {
                return waypoint;
            }
        }

        return null;
    }

    public List<Waypoint> getWaypoints() {

        return waypoints;
    }
}
