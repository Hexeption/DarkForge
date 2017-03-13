package uk.co.hexeption.mcwrapper.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.events.other.KeyboardEvent;
import uk.co.hexeption.darkforge.event.events.update.EventUpdate;
import uk.co.hexeption.darkforge.gui.screen.DarkForgeMainMenu;
import uk.co.hexeption.mcwrapper.MCWrapper;
import uk.co.hexeption.mcwrapper.base.MinecraftClient;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

import javax.annotation.Nullable;

/**
 * Created by Hexeption on 12/03/2017.
 */
@Mixin(net.minecraft.client.Minecraft.class)
public abstract class MixinMinecraft implements MinecraftClient {

    @Shadow
    private static int debugFPS;

    @Shadow
    public PlayerControllerMP playerController;

    @Shadow
    @Nullable
    public GuiScreen currentScreen;

    private EventUpdate eventUpdate = new EventUpdate();

    @Shadow
    @Final
    private Timer timer;

    @Shadow
    public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

    @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;init()V", shift = At.Shift.AFTER))
    public void init(CallbackInfo callbackInfo) {

        MCWrapper.setMinecraft((Minecraft) (Object) this);
        DarkForge.INSTANCE.start();
    }

    @Inject(method = "runTickKeyboard()V", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z"))
    public void onKeyPressed(CallbackInfo callbackInfo) {

        if (Keyboard.getEventKeyState()) {
            KeyboardEvent eventKeyboard = new KeyboardEvent(Keyboard.getEventKey());
            eventKeyboard.call();
        }
    }

    @Inject(method = "runTick()V", at = @At("RETURN"))
    public void runTick(CallbackInfo callbackInfo) {

        if (this.currentScreen instanceof GuiMainMenu) {
            displayGuiScreen(new DarkForgeMainMenu());
        }

        eventUpdate.call();
    }

    @Override
    public Controller getController() {

        return ((Controller) playerController);
    }

    @Override
    public float getTimerSpeed() {

        return timer.timerSpeed;
    }

    @Override
    public void setTimerSpeed(float timerSpeed) {

        timer.timerSpeed = timerSpeed;
    }

    @Override
    public int getFPS() {

        return debugFPS;
    }
}
