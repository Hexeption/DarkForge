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
