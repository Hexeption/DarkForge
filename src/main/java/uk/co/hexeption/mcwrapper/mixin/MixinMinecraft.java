package uk.co.hexeption.mcwrapper.mixin;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.events.other.EventKeyboard;

/**
 * Created by Hexeption on 12/03/2017.
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;init()V", shift = At.Shift.AFTER))
    public void init(CallbackInfo callbackInfo) {

        DarkForge.INSTANCE.start();
    }

    @Inject(method = "runTickKeyboard()V", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z"))
    public void onKeyPressed(CallbackInfo callbackInfo){
        if(Keyboard.getEventKeyState()){
            EventKeyboard eventKeyboard = new EventKeyboard(Keyboard.getEventKey());
            eventKeyboard.call();
        }
    }

}
