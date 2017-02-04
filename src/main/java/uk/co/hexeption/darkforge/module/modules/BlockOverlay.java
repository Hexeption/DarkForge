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

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.annotation.TestClass;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@TestClass
@Module.ModInfo(name = "Block Overlay", description = "highlights a block", category = Module.Category.RENDER, bind = Keyboard.KEY_O)
public class BlockOverlay extends Module {

    /**
     * TODO: Later
     */

    @Override
    @SideOnly(Side.CLIENT)
    public void onWorldRender() {

//        final RayTraceResult pos = getMinecraft().objectMouseOver;
//        final Block block = getWorld().getBlockState(pos.getBlockPos()).getBlock();
//
//        if (Block.getIdFromBlock(block) != 0) {
//            RenderUtils.drawESP(block.getSelectedBoundingBox(block.getDefaultState(), getWorld() ,pos.getBlockPos()), 0.2, 0.9, 0.2);
//        }
    }
}
