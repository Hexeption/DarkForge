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

package uk.co.hexeption.darkforge.module.modules;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.TimerUtils;
import uk.co.hexeption.darkforge.value.BooleanValue;
import uk.co.hexeption.darkforge.value.DoubleValue;
import uk.co.hexeption.darkforge.value.FloatValue;

/**
 * Created by Hexeption on 28/02/2017.
 */
@Module.ModInfo(name = "Kill Aura", description = "Kill them kids", category = Module.Category.COMBAT, bind = Keyboard.KEY_R)
public class Killaura extends Module {

    private BooleanValue player;

    private BooleanValue mob;

    private BooleanValue locked;

    private BooleanValue invisiableEntitys;

    private BooleanValue packetCrit;

    private BooleanValue jumpCrit;

    private BooleanValue multiAura;

    private BooleanValue autoDelay;

    private DoubleValue range;

    private FloatValue delay;

    public TimerUtils time = new TimerUtils();

    public Killaura() {

        player = new BooleanValue("Players", true);
        mob = new BooleanValue("Mobs", false);
        locked = new BooleanValue("Locked View", false);
        invisiableEntitys = new BooleanValue("Invisable Entitys", true);
        packetCrit = new BooleanValue("Packet Crit", false);
        jumpCrit = new BooleanValue("Jump Crit", false);
        multiAura = new BooleanValue("Multi Aura", false);
        autoDelay = new BooleanValue("Auto Delay", false);
        range = new DoubleValue("Range", 4D, 1D, 10D);
        delay = new FloatValue("Delay Time", 100F, 1F, 500F);

        addValue(range, delay, player, mob, locked, invisiableEntitys, packetCrit, jumpCrit, multiAura, autoDelay);
    }
}