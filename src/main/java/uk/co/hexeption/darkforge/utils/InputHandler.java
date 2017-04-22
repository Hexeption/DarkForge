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
package uk.co.hexeption.darkforge.utils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventKeybind;
import uk.co.hexeption.darkforge.event.events.EventKeyboard;
import uk.co.hexeption.darkforge.managers.EventManager;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Keir on 21/04/2017.
 */
public class InputHandler {

    public static void handleBind(boolean state, int key, char character) {
        EventKeybind event = new EventKeybind(Event.Type.PRE, state, key, character);
        EventManager.handleEvent(event);

        for (Mod mod : DarkForge.INSTANCE.modManager.getMods()) {
            if (Keyboard.getEventKey() == mod.getBind()) {
                mod.toggle();
            }
        }

        event.setType(Event.Type.POST);
        EventManager.handleEvent(event);
    }

    public static void handleKeyboard() {
        boolean state = Keyboard.getEventKeyState();
        int key = Keyboard.getEventKey();
        char character = Keyboard.getEventCharacter();
        if (key != Keyboard.KEY_NONE) {
            handleBind(state, key, character);
        }
        EventKeyboard event = new EventKeyboard(Event.Type.PRE, key);
        EventManager.handleEvent(event);

    }

    public static void handleMouse() {
        int button = Mouse.getEventButton();
        boolean state = Mouse.getEventButtonState();

        if (button >= 0) {
            handleBind(state, button - 100, '\0');
        }
    }
}
