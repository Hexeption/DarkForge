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

package uk.co.hexeption.darkforge.notification;

import uk.co.hexeption.darkforge.utils.TimerUtils;

/**
 * Created by Hexeption on 21/03/2017.
 */
public class Notification {

    private final Type type;

    private final String location;

    private final String message;

    private int duration;

    private long time;

    private TimerUtils timer = new TimerUtils();

    public Notification(Type type, String location, String message, int duration) {

        this.type = type;
        this.location = location;
        this.message = message;
        this.duration = duration;
        this.time = timer.getSystemTime();
    }

    public String getLocation() {

        return location;
    }

    public String getMessage() {

        return message;
    }

    public int getDuration() {

        return duration;
    }

    public long getTime() {

        return time;
    }

    public Type getType() {

        return type;
    }

    public enum Type {
        INFO("!", 0xff403BFF), ERROR("X", 0xff6C0100), QUESTION("?", 0xFFFFDA00);

        public final String type;

        public final int color;

        Type(String type, int color) {

            this.type = type;
            this.color = color;
        }
    }
}
