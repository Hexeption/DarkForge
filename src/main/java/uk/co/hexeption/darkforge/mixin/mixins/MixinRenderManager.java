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

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderEntities;
import uk.co.hexeption.darkforge.event.events.EventRenderEntity;
import uk.co.hexeption.darkforge.managers.EventManager;
import uk.co.hexeption.darkforge.mixin.imp.IMixinRenderManager;

/**
 * Created by Keir on 21/04/2017.
 */
@Mixin(RenderManager.class)
public class MixinRenderManager implements IMixinRenderManager {

    @Shadow
    private double renderPosX;

    @Shadow
    private double renderPosY;

    @Shadow
    private double renderPosZ;

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    public void IrenderEntityStaticPre(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callback) {

        EventRenderEntities event = new EventRenderEntities(Event.Type.PRE, entityIn, partialTicks);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.AFTER))
    public void IrenderEntityStaticPost(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callback) {

        EventRenderEntities event = new EventRenderEntities(Event.Type.POST, entityIn, partialTicks);
        EventManager.handleEvent(event);
    }

    @Inject(method = "doRenderEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render;doRender(Lnet/minecraft/entity/Entity;DDDFF)V", shift = At.Shift.BEFORE), cancellable = true)
    public void IdoRenderEntity(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo callback) {

        EventRenderEntity event = new EventRenderEntity(Event.Type.PRE, entityIn, x, y, z, yaw, partialTicks);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Override
    public double getRenderPosX() {

        return renderPosX;
    }

    @Override
    public double getRenderPosY() {

        return renderPosY;
    }

    @Override
    public double getRenderPosZ() {

        return renderPosZ;
    }
}
