package uk.co.hexeption.darkforge.mod.mods;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.value.FloatValue;

import java.util.ArrayList;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mod.ModInfo(name = "Xray", description = "See ores?", category = Mod.Category.RENDER, bind = Keyboard.KEY_X)
public class Xray extends Mod {

    public static final Xray INSTANCE = new Xray();

    private static ArrayList<Block> xrayBlocks;

    private final FloatValue opacity = new FloatValue("Opacity", 30F, 0F, 100F);

    public Xray() {

        addValue(opacity);

        xrayBlocks = new ArrayList<Block>();


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
    public void toggle() {

        super.toggle();
        mc.renderGlobal.loadRenderers();
    }

    public int getOpacity() {

        return (int) ((opacity.getValue() / 100F) * 255F);
    }

    public boolean isXrayBlock(Block block) {

        return xrayBlocks.contains(block);
    }

    public boolean shouldIgnore(Block block) {

        return !isXrayBlock(block) || block instanceof BlockBush;
    }
}
