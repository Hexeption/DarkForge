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

package uk.co.hexeption.darkforge;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.api.APIModuleSetup;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.config.FileManager;
import uk.co.hexeption.darkforge.events.EventManager;
import uk.co.hexeption.darkforge.module.ModuleManager;
import uk.co.hexeption.darkforge.ttf.FontManager;

@SideOnly(Side.CLIENT)
@Mod(modid = ClientInfo.MOD_ID, name = ClientInfo.MOD_NAME, version = ClientInfo.VERSION_BUILD)
public class DarkForge {

    @Mod.Instance(ClientInfo.MOD_ID)
    public static DarkForge instance;

    /**
     * TODO: Fix Static Crash Bug!
     */
    public static final EventManager EVENT_MANAGER = new EventManager();

    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    public static final FontManager FONT_MANAGER = new FontManager();

    public static final FileManager FILE_MANAGER = new FileManager();

    public String commandPrefix = "#";


    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {

        LogHelper.info(String.format("Starting up %s v%s", ClientInfo.MOD_NAME, ClientInfo.VERSION_BUILD));
        LogHelper.info(String.format("Running %s in Minecraft \"%s\", Forge \"%s\"", ClientInfo.MOD_NAME, MinecraftForge.MC_VERSION, ForgeVersion.getVersion()));
        instance = new DarkForge();

        LogHelper.info("Loading Modules...");
        MODULE_MANAGER.Initialization();

        LogHelper.info("Registering Forge Events");
        MinecraftForge.EVENT_BUS.register(EVENT_MANAGER);

        APIModuleSetup.setupModules();

        LogHelper.info("Loading Fonts...");
        FONT_MANAGER.loadFonts();

        LogHelper.info("Loading config..");
        FILE_MANAGER.initialization();

    }

    @Mod.EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {

    }

}
