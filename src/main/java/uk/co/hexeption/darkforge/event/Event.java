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
package uk.co.hexeption.darkforge.event;

/**
 * Created by Keir on 21/04/2017.
 */
public class Event {

    private Type type;
    private boolean cancelled;

    public Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Event setType(Type type) {
        this.type = type;
        return this;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }

    public <T extends Event> T cast() {
        return (T) this;
    }

    public enum Type {
        PRE, POST
    }
}
