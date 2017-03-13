package uk.co.hexeption.mcwrapper.mixin.renderer.chunk;

import net.minecraft.client.renderer.chunk.SetVisibility;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.mod.mods.Xray;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(VisGraph.class)
public class MixinVisGraph {

    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void setOpaqueCube(BlockPos pos, CallbackInfo callbackInfo) {

        if (DarkForge.modManager.getModuleByClass(Xray.class).getState()) {

            callbackInfo.cancel();
        }
    }

    @Inject(method = "computeVisibility", at = @At("HEAD"), cancellable = true)
    public void computeVisibility(CallbackInfoReturnable<SetVisibility> callbackInfoReturnable) {

        if (DarkForge.modManager.getModuleByClass(Xray.class).getState()) {
            SetVisibility setVisibility = new SetVisibility();
            setVisibility.setAllVisible(true);
            callbackInfoReturnable.setReturnValue(setVisibility);
        }
    }


}
