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
package uk.co.hexeption.darkforge.hook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventPlayerJump;
import uk.co.hexeption.darkforge.event.events.EventPlayerUpdate;
import uk.co.hexeption.darkforge.managers.EventManager;

/**
 * Created by Keir on 22/04/2017.
 */
public class HEntityPlayerSP extends EntityPlayerSP {

    public HEntityPlayerSP(Minecraft p_i47378_1_, World p_i47378_2_, NetHandlerPlayClient p_i47378_3_, StatisticsManager p_i47378_4_, RecipeBook p_i47378_5_) {

        super(p_i47378_1_, p_i47378_2_, p_i47378_3_, p_i47378_4_, p_i47378_5_);
    }

    @Override
    public void onUpdate() {

        EventPlayerUpdate event = new EventPlayerUpdate(Event.Type.PRE, this);
        EventManager.handleEvent(event);
        if (!event.isCancelled()) {
            super.onUpdate();
            event.setType(Event.Type.POST);
            EventManager.handleEvent(event);
        }
    }

    @Override
    public void sendChatMessage(String message) {

        if (message.startsWith(DarkForge.INSTANCE.commandPrefix)) {
            DarkForge.INSTANCE.commandManager.executeCommand(message.substring(DarkForge.INSTANCE.commandPrefix.length()));
        } else {
            super.sendChatMessage(message);
        }
    }

    @Override
    public void jump() {

        EventPlayerJump event = new EventPlayerJump(Event.Type.PRE, this);
        EventManager.handleEvent(event);
        if (!event.isCancelled()) {
            super.jump();
            event.setType(Event.Type.POST);
            EventManager.handleEvent(event);
        }
    }
}
