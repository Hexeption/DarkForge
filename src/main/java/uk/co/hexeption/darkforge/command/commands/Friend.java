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
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.command.Command;

/**
 * Created by Hexeption on 20/03/2017.
 */
@Command.CMDInfo(name = {"friend"}, descrption = "Adds a player for your friends list", help = "[<add> <username> <alias>] [<add> <username>] [<del> <username>] [<list>] [<clear>]")
public class Friend extends Command {

    @Override
    public void execute(String input, String[] args) throws Exception {

        if (args.length < 0) {
            DarkForge.INSTANCE.addChatMessage(TextFormatting.RED + "Invalid arguments.");
            return;
        }
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("add")) {
                boolean successful = !DarkForge.INSTANCE.friendManager.isFriend(args[1]);
                if (args.length > 2) {
                    DarkForge.INSTANCE.friendManager.addFriend(args[1], args[2]);
                    DarkForge.INSTANCE.addChatMessage("'" + args[1] + "' " + (successful ? "added to your friend list" : "already in your friends list"));
                    DarkForge.INSTANCE.addChatMessage("'" + args[1] + "' will now be called " + args[2]);


                } else {
                    DarkForge.INSTANCE.friendManager.addFriend(args[1]);
                    DarkForge.INSTANCE.addChatMessage("'" + args[1] + "' " + (successful ? "added to your friend list" : "already in your friends list"));

                }
                if (successful)
                    DarkForge.INSTANCE.fileManager.saveFriends();
            }

            if (args[0].equalsIgnoreCase("del")) {
                if (DarkForge.INSTANCE.friendManager.isFriend(args[1])) {
                    DarkForge.INSTANCE.friendManager.removeFriend(args[1]);
                    DarkForge.INSTANCE.addChatMessage(args[1] + " has been removed from your friends list");
                    DarkForge.INSTANCE.fileManager.saveFriends();
                } else {
                    DarkForge.INSTANCE.addChatMessage(args[1] + " is not in your friends list");

                }
            }

            if (args[0].equalsIgnoreCase("list")) {
                for (String username : DarkForge.INSTANCE.friendManager.getFriends().keySet()) {
                    LogHelper.info(username);
                }
            }

            if (args[0].equalsIgnoreCase("clear")) {
                DarkForge.INSTANCE.friendManager.getFriends().clear();
                DarkForge.INSTANCE.addChatMessage("Cleared Friends List");

            }

        } else {
            DarkForge.INSTANCE.addChatMessage(TextFormatting.RED + "Invalid arguments.");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "friend add " + TextFormatting.RED + "<username>");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "friend add " + TextFormatting.RED + "<username> <alias>");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "friend del" + TextFormatting.RED + " <username>");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "friend list");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "friend clear");
        }
    }
}
