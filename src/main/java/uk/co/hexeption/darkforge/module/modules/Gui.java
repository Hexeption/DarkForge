package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.APIModuleSetup;
import uk.co.hexeption.darkforge.gui.GuiModule;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Module.ModInfo(name = "Gui", description = "Gui", category = Module.Category.GUI, bind = Keyboard.KEY_RCONTROL)
public class Gui extends Module {

    private GuiModule gui;

    public Gui() {


        APIModuleSetup.addModuleToSetupQueue(this);
    }

    @Override
    public void initializeLater() {

        gui = new GuiModule();
    }

    @Override
    public void onEnable() {

        Minecraft.getMinecraft().displayGuiScreen(gui);

    }

    public GuiModule getGui() {

        return gui;
    }
}
