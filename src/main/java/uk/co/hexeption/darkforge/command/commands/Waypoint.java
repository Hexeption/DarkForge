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
package uk.co.hexeption.darkforge.command.commands;

import net.minecraft.util.text.TextFormatting;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.command.Command;
import uk.co.hexeption.darkforge.notification.Notification;

import javax.vecmath.Vector3d;

/**
 * Created by Keir on 23/04/2017.
 */
@Command.CMDInfo(name = {"waypoint"}, descrption = "Adds a waypoint to a place", help = "[<add> <name>] [<add> <name> <x> <y> <z>] [<remove> <name>] [<list>] [<clear>]")
public class Waypoint extends Command {

    @Override
    public void execute(String input, String[] args) throws Exception {

        if (args.length < 0) {
            DarkForge.INSTANCE.addChatMessage(TextFormatting.RED + "Invalid arguments.");
            return;
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length > 3) {
                    String name = args[1];
                    double x = Double.parseDouble(args[2]);
                    double y = Double.parseDouble(args[3]);
                    double z = Double.parseDouble(args[4]);
                    if (DarkForge.INSTANCE.waypointManager.addWaypoint(new uk.co.hexeption.darkforge.waypoint.Waypoint(name, new Vector3d(x, y, z), mc.getCurrentServerData()))) {
                        DarkForge.INSTANCE.addNotification(Notification.Type.INFO, "Waypoint", "Waypoint Added", 5000);
                    }
                } else {
                    String name = args[1];
                    double x = mc.player.posX;
                    double y = mc.player.posY;
                    double z = mc.player.posZ;
                    if (DarkForge.INSTANCE.waypointManager.addWaypoint(new uk.co.hexeption.darkforge.waypoint.Waypoint(name, new Vector3d(x, y, z), mc.getCurrentServerData()))) {
                        DarkForge.INSTANCE.addNotification(Notification.Type.INFO, "Waypoint", "Waypoint Added", 5000);
                    }
                }
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length > 1) {
                    if (DarkForge.INSTANCE.waypointManager.removeWaypoint(args[1])) {
                        DarkForge.INSTANCE.addNotification(Notification.Type.INFO, "Waypoint", "Waypoint Removed", 5000);
                    }
                } else {
                    DarkForge.INSTANCE.addChatMessage(TextFormatting.RED + "Invalid arguments.");
                    DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "waypoint remove " + TextFormatting.RED + "<name>");
                }
            }

            if (args[0].equalsIgnoreCase("list")) {
                for (uk.co.hexeption.darkforge.waypoint.Waypoint waypoint : DarkForge.INSTANCE.waypointManager.getWaypoints()) {
                    DarkForge.INSTANCE.addChatMessage(waypoint.getDescription());
                }
            }
            if (args[0].equalsIgnoreCase("clear")) {
                DarkForge.INSTANCE.addNotification(Notification.Type.INFO, "Waypoint", "Waypoints Cleared", 5000);
                DarkForge.INSTANCE.waypointManager.getWaypoints().clear();
                DarkForge.INSTANCE.fileManager.saveWaypoints();
            }
        }
    }
}
