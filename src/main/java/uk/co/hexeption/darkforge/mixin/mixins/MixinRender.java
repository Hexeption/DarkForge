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

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderLabel;
import uk.co.hexeption.darkforge.managers.EventManager;

/**
 * Created by Keir on 21/04/2017.
 */
@Mixin(Render.class)
public class MixinRender {

    @Inject(method = "renderLivingLabel", at = @At("HEAD"), cancellable = true)
    public <T extends Entity> void IrenderLabelPre(T entityIn, String str, double x, double y, double z, int maxDistance, CallbackInfo callback) {
        EventRenderLabel event = new EventRenderLabel(Event.Type.PRE, entityIn, str, x, y, z, maxDistance);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method = "renderLivingLabel", at = @At("RETURN"))
    public <T extends Entity> void IrenderLabelPost(T entityIn, String str, double x, double y, double z, int maxDistance, CallbackInfo callback) {
        EventRenderLabel event = new EventRenderLabel(Event.Type.POST, entityIn, str, x, y, z, maxDistance);
        EventManager.handleEvent(event);
    }
}
