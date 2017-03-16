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

import net.minecraft.block.Block;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.command.Command;

/**
 * Created by Hexeption on 16/03/2017.
 */
@Command.CMDInfo(name = {"xray"}, descrption = "Add blocks to Xray", help = "[<add> <block-id>] [<del> <block-id>] [<list>]")
public class Xray extends Command {

    @Override
    @SideOnly(Side.CLIENT)
    public void execute(String input, String[] args) throws Exception {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("add")) {
                int blockname = Integer.valueOf(args[1]);
                if (DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).isXrayBlock(Block.getBlockById(Integer.valueOf(args[1])))) {
                    DarkForge.INSTANCE.addChatMessage(blockname + " is already in the list");
                    return;
                }

                DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).xrayBlocks.add(Block.getBlockById(Integer.valueOf(args[1])));
                DarkForge.INSTANCE.addChatMessage("'" + Block.getBlockById(blockname).getLocalizedName() + "' has been added.");
                mc.renderGlobal.loadRenderers();
                return;
            }

            if (args[0].equalsIgnoreCase("del")) {
                for (int i = 0; i < DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).xrayBlocks.size(); i++) {
                    if (Integer.toString(Block.getIdFromBlock(DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).xrayBlocks.get(i))).toLowerCase().equals(args[1])) {
                        DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).xrayBlocks.remove(i);
                        DarkForge.INSTANCE.addChatMessage("'" + Block.getBlockById(i).getLocalizedName() + "' has been removed.");
                        mc.renderGlobal.loadRenderers();
                        return;
                    }
                }

                int blockname = Integer.valueOf(args[1]);
                DarkForge.INSTANCE.addChatMessage("'" + Block.getBlockById(blockname).getLocalizedName() + "' is not in the list.");
            }

            if (args[0].equalsIgnoreCase("list")) {
                for (int i = 0; i < DarkForge.INSTANCE.modManager.getModuleByClass(uk.co.hexeption.darkforge.mod.mods.Xray.class).xrayBlocks.size(); i++) {
                    LogHelper.info(Block.getBlockById(i).getLocalizedName());
                }
            }
        } else {
            DarkForge.INSTANCE.addChatMessage(TextFormatting.RED + "Invalid arguments.");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "xray add " + TextFormatting.RED + "<block-id>");
            DarkForge.INSTANCE.addChatMessage(DarkForge.INSTANCE.commandPrefix + "xray del" + TextFormatting.RED + " <block-id>");

        }

    }
}
