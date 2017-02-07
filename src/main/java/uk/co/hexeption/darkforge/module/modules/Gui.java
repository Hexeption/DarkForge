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

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.APIModuleSetup;
import uk.co.hexeption.darkforge.gui.GuiModule;
import uk.co.hexeption.darkforge.module.Module;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
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
