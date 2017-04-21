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

package uk.co.hexeption.darkforge.mod.mods.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StringUtils;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.api.annotation.NoKeyBind;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.other.MouseEvent;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 22/03/2017.
 */
@NoKeyBind
@Mod.ModInfo(name = "Middle Click Friend", category = Mod.Category.MISC, description = "Middle click a player to add it to your friend list", visable = false)
public class MiddleClickFriend extends Mod {

    @EventTarget
    public void onMouseEvent(MouseEvent event) {

        if (event.getButton() == 2) {
            if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit != null && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer) mc.objectMouseOver.entityHit;
                final String name = StringUtils.stripControlCodes(player.getDisplayName().getUnformattedText());

                if (DarkForge.INSTANCE.friendManager.isFriend(name)) {
                    DarkForge.INSTANCE.friendManager.removeFriend(name);
                    DarkForge.INSTANCE.fileManager.saveFriends();
                    LogHelper.info("Removed");
                } else {
                    DarkForge.INSTANCE.friendManager.addFriend(name);
                    DarkForge.INSTANCE.fileManager.saveFriends();
                    LogHelper.info("added");
                }
            }
        }
    }

    @Override
    public void onEvent(Event event) {

    }
}
