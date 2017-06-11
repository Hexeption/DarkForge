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

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.hexeption.darkforge.mixin.imp.IMixinPlayerControllerMP;


/**
 * Created by Keir on 21/04/2017.
 */
@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP implements IMixinPlayerControllerMP {

    @Shadow
    @Final
    private Minecraft mc;

    @Shadow
    private ItemStack currentItemHittingBlock;

    @Shadow
    @Final
    private NetHandlerPlayClient connection;

    @Override
    public void setCurrentItemHittingBlock(ItemStack stack) {
        this.currentItemHittingBlock = stack == null ? ItemStack.EMPTY : stack;
    }

    @Override
    public EntityPlayerSP getEntityPlayer() {
        return mc.player;
    }

//    @Inject(method = "createClientPlayer", at = @At("HEAD"), cancellable = true)
//    public void IcreateClientPlayer(World worldIn, StatisticsManager statWriter, CallbackInfoReturnable<EntityPlayerSP> callback) {
//        callback.setReturnValue(new HEntityPlayerSP(mc, worldIn, connection, statWriter));
//    }

}
