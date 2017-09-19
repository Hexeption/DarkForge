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
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.darkforge.hook.HEntityPlayerSP;
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

    @Inject(method = "func_192830_a", at = @At("HEAD"), cancellable = true)
    public void IcreateClientPlayer(World p_192830_1_, StatisticsManager p_192830_2_, RecipeBook p_192830_3_, CallbackInfoReturnable<EntityPlayerSP> callback) {

        callback.setReturnValue(new HEntityPlayerSP(mc, p_192830_1_, this.connection, p_192830_2_, p_192830_3_));
    }

}
