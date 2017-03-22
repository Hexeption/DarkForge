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

package uk.co.hexeption.darkforge.mod.mods.movement;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.movement.PreMotionUpdateEvent;
import uk.co.hexeption.darkforge.mod.Mod;

import java.util.Objects;

/**
 * Created by Hexeption on 22/03/2017.
 */
@Deprecated
@Mod.ModInfo(name = "Inventory Move", description = "Allows movement in inventorys", category = Mod.Category.MOVEMENT)
public class InventoryMove extends Mod {

    @EventTarget
    public void onPreMotionEvent(PreMotionUpdateEvent event) {

        final KeyBinding[] keys = {mc.gameSettings.keyBindRight, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindForward, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint};

        KeyBinding[] arrayKeys;
        int lenghtOfKeys = (arrayKeys = keys).length;
        if (mc.currentScreen instanceof GuiContainer) {
            for (int i = 0; i < lenghtOfKeys; i++) {
                KeyBinding key = arrayKeys[i];


            }
        } else if (Objects.isNull(mc.currentScreen)) {
            for (int i = 0; i < lenghtOfKeys; i++) {
                KeyBinding bind = arrayKeys[i];
                if (!Keyboard.isKeyDown(bind.getKeyCode())) {
                    KeyBinding.setKeyBindState(bind.getKeyCode(), false);
                }
            }

        }
    }

}
