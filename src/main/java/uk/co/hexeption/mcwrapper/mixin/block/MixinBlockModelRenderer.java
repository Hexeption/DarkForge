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
import uk.co.hexeption.darkforge.mod.mods.render.Xray;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(BlockRendererDispatcher.class)
public abstract class MixinBlockModelRenderer {

    @Inject(method = "renderBlock(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/VertexBuffer;)Z", at = @At("HEAD"), cancellable = true)
    public void renderBlock(IBlockState state, BlockPos pos, IBlockAccess blockAccess, VertexBuffer worldRendererIn, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        if (DarkForge.INSTANCE.modManager.getModuleByClass(Xray.class).getState()) {

            if (DarkForge.INSTANCE.modManager.getModuleByClass(Xray.class).shouldIgnore(state.getBlock())) {
                callbackInfoReturnable.setReturnValue(false);
            }

        }
    }


}
