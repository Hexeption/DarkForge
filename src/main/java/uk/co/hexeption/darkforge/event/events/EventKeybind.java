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

import uk.co.hexeption.darkforge.event.Event;

/**
 * Created by Keir on 21/04/2017.
 */
public class EventKeybind extends Event {

    private final boolean state;
    private final int key;
    private final char character;

    public EventKeybind(Type type, boolean state, int key, char character) {
        super(type);
        this.state = state;
        this.key = key;
        this.character = character;
    }

    public boolean getState() {
        return state;
    }

    public int getKey() {
        return key;
    }

    public char getCharacter() {
        return character;
    }
}
