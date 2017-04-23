package uk.co.hexeption.darkforge.module.modules;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;

@Module.ModInfo(name = "Step", description = "Automatically Sprints for you.", category = Module.Category.MOVEMENT, bind = Keyboard.KEY_S)
public class Step extends Module {

    private float stepHeight = 1.5f;

    /**
     * One and a half block
     */

    @Override
    public void onEnable() {

        getPlayer().stepHeight = stepHeight;
    }

    @Override
    public void onDisable() {

        getPlayer().stepHeight = 0.5f;
    }
}
