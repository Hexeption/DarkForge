package uk.co.hexeption.mcwrapper.mixin.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.mod.mods.Xray;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(BlockRendererDispatcher.class)
public abstract class MixinBlockModelRenderer {

    @Inject(method = "renderBlock(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/VertexBuffer;)Z", at = @At("HEAD"), cancellable = true)
    public void renderBlock(IBlockState state, BlockPos pos, IBlockAccess blockAccess, VertexBuffer worldRendererIn, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        if (DarkForge.INSTANCE.modManager.getModuleByClass(Xray.class).getState()) {

            if (Xray.INSTANCE.shouldIgnore(state.getBlock())) {
                callbackInfoReturnable.setReturnValue(false);
            }

        }
    }


}
