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

package uk.co.hexeption.darkforge.gui.gui.elements;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 18/03/2017.
 */
public class KeybindMods extends Component {

    private Mod mod;

    private boolean editing;

    public KeybindMods(int xPos, int yPos, int width, int height, Component component, Mod mod) {

        super(xPos, yPos, width, height, ComponentType.KEYBIND, component, "");
        this.mod = mod;
    }

    @Override
    public void onUpdate() {

        if (Keyboard.getEventKeyState()) {

            if (editing) {
                if (Keyboard.getEventKey() == Keyboard.KEY_DELETE)
                    mod.setBind(0);
                else
                    mod.setBind(Keyboard.getEventKey());
                editing = false;
            }
        }
    }


    @Override
    public void onMousePress(int x, int y, int buttonID) {

        if (x > this.getX() + DarkForge.INSTANCE.fontManager.clickGui.getStringWidth("KeybindMods") + 6 && x < this.getX() + this.getDimension().width && y > this.getY() && y < this.getY() + this.getDimension().height) {
            editing = !editing;
        }


    }

    public Mod getMod() {

        return mod;
    }

    public boolean isEditing() {

        return editing;
    }

    public void setEditing(boolean editing) {

        this.editing = editing;
    }
}
