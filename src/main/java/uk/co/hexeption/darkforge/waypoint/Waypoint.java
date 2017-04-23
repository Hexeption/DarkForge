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

package uk.co.hexeption.darkforge.waypoint;

import net.minecraft.client.multiplayer.ServerData;
import uk.co.hexeption.darkforge.MC;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import javax.vecmath.Vector3d;

/**
 * Created by Keir on 23/04/2017.
 */
public class Waypoint implements MC {

    public String name;

    public String server;

    public Vector3d position;

    public int dimension;

    public int color;

    public Waypoint(String name, Vector3d position, ServerData server, int dimension, int color) {

        setName(name).setPosition(position).setServer(server).setDimension(dimension).setColor(color);
    }

    public Waypoint(String name, Vector3d position, ServerData server) {

        this(name, position, server, mc.player.dimension, GLUtils.getRandomColor().hashCode());
    }

    public Waypoint setName(String name) {

        this.name = name;
        return this;
    }

    public Waypoint setPosition(Vector3d position) {

        this.position = position;
        return this;
    }

    public Waypoint setColor(int color) {

        this.color = color;
        return this;
    }

    public Waypoint setDimension(int dimension) {

        this.dimension = dimension;
        return this;
    }

    public Waypoint setServer(String server) {

        this.server = server;
        return this;
    }

    public Waypoint setServer(ServerData server) {

        if (server != null) {
            setServer(server.serverIP);
        }
        return this;
    }

    public String getDescription() {

        return String.format("%s @ (%s, %s, %s)", name, MathUtils.round(position.getX(), 3), MathUtils.round(position.getY(), 3), MathUtils.round(position.getZ(), 3));
    }

}
