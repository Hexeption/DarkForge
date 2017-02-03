package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Module.ModInfo(name = "Fullbright", description = "Brightens up the game", category = Module.Category.RENDER, bind = Keyboard.KEY_V)
public class Fullbright extends Module {

    @Override
    public void onWorldRender() {

        if (getState())
            if (getGameSettings().gammaSetting < 16) {
                getGameSettings().gammaSetting += 0.5;
            } else if (getGameSettings().gammaSetting > 0.5) {
                if (getGameSettings().gammaSetting < 1f)
                    getGameSettings().gammaSetting = 0.5f;
                else
                    getGameSettings().gammaSetting -= 0.5;
            }
    }

    @Override
    public void onDisable() {

        getGameSettings().gammaSetting = 0.5f;
    }
}
