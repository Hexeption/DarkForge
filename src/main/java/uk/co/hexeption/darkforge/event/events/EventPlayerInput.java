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

/**
 * Created by Keir on 22/04/2017.
 */
public class EventPlayerInput extends AbstractEventPlayer {

    private float forawrd, strafe;

    public EventPlayerInput(Type type, EntityPlayerSP entity, float forawrd, float strafe) {
        super(type, entity);
        this.forawrd = forawrd;
        this.strafe = strafe;
    }

    public float getForawrd() {
        return forawrd;
    }

    public void setForawrd(float forawrd) {
        this.forawrd = forawrd;
    }

    public float getStrafe() {
        return strafe;
    }

    public void setStrafe(float strafe) {
        this.strafe = strafe;
    }
}
