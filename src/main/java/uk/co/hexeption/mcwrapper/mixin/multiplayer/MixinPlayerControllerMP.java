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
