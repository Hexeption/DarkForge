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
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.ModInfo;
import uk.co.hexeption.darkforge.api.annotation.Enabled;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Enabled
@Module.ModInfo(name = "Hud", description = "UI", category = Module.Category.GUI, bind = 0)
public class Hud extends Module {



    @Override
    public void onGuiRender() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        getFontRenderer().drawStringWithShadow(uk.co.hexeption.darkforge.ModInfo.MOD_NAME + " v" + uk.co.hexeption.darkforge.ModInfo.VERSION_BUILD, 1,5, 0xffffffff);
        getFontRenderer().drawStringWithShadow("Minecraft v" + MinecraftForge.MC_VERSION +" Forge v" + ForgeVersion.getVersion(), 1,20, 0xffffffff);

        arrayList(scaledResolution);
    }

    private void arrayList(ScaledResolution scaledResolution) {

        int offset = -DarkForge.getInstance().getFontManager().arraylist.getHeight() + 5;
        if (!getGameSettings().showDebugInfo) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (module.equals(this)) {
                    continue;
                }
                if (module.getState() && !module.isCategory(Category.GUI)) {
                    DarkForge.getInstance().getFontManager().arraylist.drawStringWithShadow(module.getName(), (scaledResolution.getScaledWidth() - 3) - DarkForge.getInstance().getFontManager().arraylist.getStringWidth(module.getName()), offset += DarkForge.getInstance().getFontManager().arraylist.getHeight() + 1, module.getCategory().color);
                }
            }
        }
    }
}
