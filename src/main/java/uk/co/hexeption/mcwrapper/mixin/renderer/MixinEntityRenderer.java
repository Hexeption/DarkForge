package uk.co.hexeption.mcwrapper.mixin.renderer;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.event.events.render.EventRender3D;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    private EventRender3D eventRender3D = new EventRender3D(0);

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {

        eventRender3D.setPartialTicks(partialTicks);
        eventRender3D.call();
    }

    @Shadow
    private  void setupCameraTransform(float partialTicks, int pass){
    }
}
