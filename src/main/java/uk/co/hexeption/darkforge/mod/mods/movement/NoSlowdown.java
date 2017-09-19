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

import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShield;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventPlayerSlowDown;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mod.ModInfo(name = "No Slowdown", description = "Stops items from slowing you down.", category = Mod.Category.MOVEMENT)
public class NoSlowdown extends Mod {

    @Override
    public void onEvent(Event event) {

        if (getState()) {
            if (event instanceof EventPlayerSlowDown) {
                EventPlayerSlowDown eventPlayerSlowDown = event.cast();


                if (eventPlayerSlowDown.getType() == Event.Type.POST && eventPlayerSlowDown.getEntity().getHeldItemMainhand().getItem() instanceof ItemBow ||
                        eventPlayerSlowDown.getEntity().getHeldItemOffhand().getItem() instanceof ItemBow ||
                        eventPlayerSlowDown.getEntity().getHeldItemMainhand().getItem() instanceof ItemFood ||
                        eventPlayerSlowDown.getEntity().getHeldItemOffhand().getItem() instanceof ItemFood ||
                        eventPlayerSlowDown.getEntity().getHeldItemMainhand().getItem() instanceof ItemPotion ||
                        eventPlayerSlowDown.getEntity().getHeldItemOffhand().getItem() instanceof ItemPotion ||
                        eventPlayerSlowDown.getEntity().getHeldItemMainhand().getItem() instanceof ItemShield ||
                        eventPlayerSlowDown.getEntity().getHeldItemOffhand().getItem() instanceof ItemShield) {
//                    eventPlayerSlowDown.getEntity().movementInput.moveForward /= 0.2;
                    eventPlayerSlowDown.getEntity().movementInput.moveStrafe /= 0.2;
                }
            }
        }
    }
}
