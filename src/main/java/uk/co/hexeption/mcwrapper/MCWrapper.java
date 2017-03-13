package uk.co.hexeption.mcwrapper;

import net.minecraft.client.Minecraft;
import uk.co.hexeption.mcwrapper.base.MinecraftClient;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

/**
 * Created by Hexeption on 13/03/2017.
 */
public class MCWrapper {

    private static MinecraftClient mc;

    public static void setMinecraft(Minecraft mc) {

        MCWrapper.mc = ((MinecraftClient) mc);
    }

    public static MinecraftClient getMinecraft() {

        return mc;
    }

    public static Controller getController() {

        return getMinecraft().getController();
    }


}
