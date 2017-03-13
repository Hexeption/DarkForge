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
package uk.co.hexeption.mcwrapper.mixin.entity.living.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.event.events.movement.MoveEvent;
import uk.co.hexeption.darkforge.event.events.movement.PostMotionUpdateEvent;
import uk.co.hexeption.darkforge.event.events.movement.PreMotionUpdateEvent;

/**
 * Created by Keir on 13/03/2017.
 */
@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntityPlayer {

    private PreMotionUpdateEvent preMotionUpdate = new PreMotionUpdateEvent();

    private PostMotionUpdateEvent postMotionUpdate = new PostMotionUpdateEvent();

    private MoveEvent eventMove = new MoveEvent(0, 0, 0);

    @Shadow
    protected abstract void updateAutoJump(float p_189810_1_, float p_189810_2_);

    @Inject(method = "onUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;onUpdateWalkingPlayer()V", shift = At.Shift.BEFORE), cancellable = true)
    private void updateEvents(CallbackInfo callbackInfo) {

        preMotionUpdate.setCancelled(false);
        preMotionUpdate.call();

        if (preMotionUpdate.isCancelled()) {
            callbackInfo.cancel();
        }

        postMotionUpdate.call();
    }

    /**
     * @author
     */
    @Overwrite
    public void move(MoverType type, double x, double y, double z) {

        double d0 = this.posX;
        double d1 = this.posZ;
        eventMove.setMotionX(x);
        eventMove.setMotionY(y);
        eventMove.setMotionZ(z);
        eventMove.call();
        super.move(type, eventMove.getMotionX(), eventMove.getMotionY(), eventMove.getMotionZ());
        this.updateAutoJump((float) (this.posX - d0), (float) (this.posZ - d1));
    }
}
