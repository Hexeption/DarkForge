package uk.co.hexeption.darkforge.module.modules;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Module.ModInfo(name = "Fly", description = "Be like SuperGirl <3", category = Module.Category.MOVEMENET, bind = Keyboard.KEY_F)
public class Fly extends Module {

    @Override
    @SideOnly(Side.CLIENT)
    public void onEnable() {

        getPlayer().capabilities.isFlying = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDisable() {

        getPlayer().capabilities.isFlying = false;
    }

    @Override
    public void onWorldTick() {

        if(!getPlayer().capabilities.isFlying){
            getPlayer().capabilities.isFlying = true;
        }
    }
}
