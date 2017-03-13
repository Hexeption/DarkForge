package uk.co.hexeption.mcwrapper.mixin.multiplayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mixin(net.minecraft.client.multiplayer.PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP implements Controller {

    @Shadow
    private float curBlockDamageMP;

    @Override
    public float getBlockDamage() {

        return curBlockDamageMP;
    }

    @Override
    public void setBlockDamage(float damage) {

        this.curBlockDamageMP = damage;
    }
}
