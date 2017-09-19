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

package uk.co.hexeption.darkforge.mod.mods.render;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventBlockRenderSide;
import uk.co.hexeption.darkforge.event.events.EventSetOpaqueCube;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.value.FloatValue;

import java.util.ArrayList;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mod.ModInfo(name = "Xray", description = "See ores?", category = Mod.Category.RENDER, bind = Keyboard.KEY_X)
public class Xray extends Mod {

    /**
     * Need Brightness
     */

    public final ArrayList<Block> xrayBlocks = new ArrayList<Block>();

    private final FloatValue opacity = new FloatValue("Opacity", 30F, 0F, 100F);

    private transient int ambientOcclusion;

    public Xray() {

        addValue(opacity);

        //Ore Blocks
        xrayBlocks.add(Blocks.COAL_ORE);
        xrayBlocks.add(Blocks.DIAMOND_ORE);
        xrayBlocks.add(Blocks.EMERALD_ORE);
        xrayBlocks.add(Blocks.GOLD_ORE);
        xrayBlocks.add(Blocks.IRON_ORE);
        xrayBlocks.add(Blocks.LAPIS_ORE);
        xrayBlocks.add(Blocks.QUARTZ_ORE);
        xrayBlocks.add(Blocks.REDSTONE_ORE);
        xrayBlocks.add(Blocks.LIT_REDSTONE_ORE);
        xrayBlocks.add(Blocks.COAL_BLOCK);

        //Ingot Blocks + Glowstone
        xrayBlocks.add(Blocks.DIAMOND_BLOCK);
        xrayBlocks.add(Blocks.EMERALD_BLOCK);
        xrayBlocks.add(Blocks.GOLD_BLOCK);
        xrayBlocks.add(Blocks.IRON_BLOCK);
        xrayBlocks.add(Blocks.LAPIS_BLOCK);
        xrayBlocks.add(Blocks.QUARTZ_BLOCK);
        xrayBlocks.add(Blocks.REDSTONE_BLOCK);
        xrayBlocks.add(Blocks.GLOWSTONE);

        //Random Blocks
        xrayBlocks.add(Blocks.CRAFTING_TABLE);
        xrayBlocks.add(Blocks.TNT);
        xrayBlocks.add(Blocks.DROPPER);
        xrayBlocks.add(Blocks.DISPENSER);

    }

    @Override
    public void onEnable() {

        ambientOcclusion = mc.gameSettings.ambientOcclusion;
        mc.gameSettings.ambientOcclusion = 0;
        mc.renderGlobal.loadRenderers();
    }

    @Override
    public void onDisable() {

        mc.gameSettings.ambientOcclusion = ambientOcclusion;
        mc.renderGlobal.loadRenderers();

    }

    public int getOpacity() {

        return (int) ((opacity.getValue() / 100F) * 255F);
    }

    public boolean isXrayBlock(Block block) {

        return xrayBlocks.contains(block);
    }

    @Override
    public void onEvent(Event event) {

        if (getState() && event.getType() == Event.Type.PRE) {
            if (event instanceof EventSetOpaqueCube) {
                event.cancel();
            } else if (event instanceof EventBlockRenderSide) {
                EventBlockRenderSide eventBlockRenderSide = event.cast();
                if (isXrayBlock(eventBlockRenderSide.getState().getBlock())) {
                    eventBlockRenderSide.setToRender(true);
                } else {
                    eventBlockRenderSide.cancel();
                }
            }
        }
    }
}
