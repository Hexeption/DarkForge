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
package uk.co.hexeption.darkforge.mixin.mixins;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.gui.screen.GuiStatus;

/**
 * Created by Keir on 23/04/2017.
 */
@Mixin(GuiMultiplayer.class)
public class MixinGuiMultiplayer extends GuiScreen {

    @Inject(method = "initGui", at = @At(value = "RETURN"))
    public void Iinit(CallbackInfo callback) {

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int right = scaledResolution.getScaledWidth() - 3;
        this.buttonList.add(new GuiButton(100, 5, 3, 100, 20, "MC Status"));
        this.buttonList.add(new GuiButton(101, right - 103, 3, 100, 20, "Proxy"));
    }

    @Inject(method = "actionPerformed", at = @At(value = "HEAD"))
    public void actionPerformed(GuiButton button, CallbackInfo callback) {

        if (button.id == 100) {
            mc.displayGuiScreen(new GuiStatus(this));
        }

        if (button.id == 101) {


        }
    }

}
