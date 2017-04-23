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
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.*;
import uk.co.hexeption.darkforge.managers.EventManager;

/**
 * Created by Keir on 22/04/2017.
 */
public class HEntityPlayerSP extends EntityPlayerSP {

    private HEntityPlayerSP(World world) {
        this(Minecraft.getMinecraft(), world, Minecraft.getMinecraft().getConnection(), new StatisticsManager());
    }

    public HEntityPlayerSP(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatisticsManager statFile) {
        super(mcIn, worldIn, netHandler, statFile);
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
    public void onUpdateWalkingPlayer() {
        EventPlayerWalking event = new EventPlayerWalking(Event.Type.PRE, this);
        EventManager.handleEvent(event);
        if (!event.isCancelled()) {
            super.onUpdateWalkingPlayer();
            event.setType(Event.Type.POST);
            EventManager.handleEvent(event);
        }
    }

    @Override
    public void moveEntityWithHeading(float strafe, float forward) {
        EventPlayerInput event = new EventPlayerInput(Event.Type.PRE, this, forward, strafe);
        EventManager.handleEvent(event);
        if (!event.isCancelled()) {
            super.moveEntityWithHeading(event.getStrafe(), event.getForawrd());
            event.setType(Event.Type.POST);
            EventManager.handleEvent(event);
        }
    }

    @Override
    public void moveRelative(float strafe, float forward, float friction) {
        EventPlayerMoveRelative event = new EventPlayerMoveRelative(Event.Type.PRE, this, forward, strafe, friction);
        EventManager.handleEvent(event);
        if (!event.isCancelled()) {
            super.moveRelative(event.getStrafe(), event.getForawrd(), event.getFrication());
            event.setType(Event.Type.POST);
            EventManager.handleEvent(event);
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
