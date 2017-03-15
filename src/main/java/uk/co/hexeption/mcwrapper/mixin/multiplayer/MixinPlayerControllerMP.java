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

package uk.co.hexeption.mcwrapper.mixin.multiplayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.darkforge.mixin.DarkForgeEntityPlayer;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(net.minecraft.client.multiplayer.PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP implements Controller {

    @Shadow
    private float curBlockDamageMP;

    @Shadow
    @Final
    private NetHandlerPlayClient connection;

    @Shadow
    @Final
    private Minecraft mc;

    @Override
    public float getBlockDamage() {

        return curBlockDamageMP;
    }

    @Override
    public void setBlockDamage(float damage) {

        this.curBlockDamageMP = damage;
    }

    @Inject(method = "createClientPlayer", at = @At("HEAD"), cancellable = true)
    public void createClientPlayer(World world, StatisticsManager statisticsManager, CallbackInfoReturnable<EntityPlayerSP> callbackInfoReturnable) {

        callbackInfoReturnable.setReturnValue(new DarkForgeEntityPlayer(mc, world, connection, statisticsManager));
        callbackInfoReturnable.cancel();
    }
}
