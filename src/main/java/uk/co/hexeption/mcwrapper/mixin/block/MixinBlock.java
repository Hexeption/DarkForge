package uk.co.hexeption.mcwrapper.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
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
@Mixin(Block.class)
public class MixinBlock {

    @Inject(method = "shouldSideBeRendered", at = @At("HEAD"), cancellable = true)
    public void shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        if (DarkForge.modManager.getModuleByClass(Xray.class).getState()) {

            callbackInfoReturnable.setReturnValue(Xray.INSTANCE.isXrayBlock(blockState.getBlock()));

        }
    }

    @Inject(method = "getAmbientOcclusionLightValue", at = @At("HEAD"), cancellable = true)
    public void getAmbientOcclusionLightValue(IBlockState state, CallbackInfoReturnable<Float> callbackInfoReturnable) {

        if (DarkForge.modManager.getModuleByClass(Xray.class).getState()) {

            callbackInfoReturnable.setReturnValue(10000F);

        }
    }

}
