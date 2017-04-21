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
package uk.co.hexeption.darkforge.event.events;

import net.minecraft.client.entity.EntityPlayerSP;
import uk.co.hexeption.darkforge.event.Event;

/**
 * Created by Keir on 21/04/2017.
 */
public class EventChat extends Event {

    private String message;

    public EventChat(Type type, String message) {
        super(type);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Send extends EventChat {
        private EntityPlayerSP entity;

        public Send(Type type, String message, EntityPlayerSP entity) {
            super(type, message);
            this.entity = entity;
        }

        public EntityPlayerSP getEntity() {
            return entity;
        }
    }

    public static class Recive extends EventChat {
        public Recive(Type type, String message) {
            super(type, message);
        }
    }
}
