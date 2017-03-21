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

package uk.co.hexeption.mcwrapper.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.util.Timer;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
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
import uk.co.hexeption.darkforge.utils.OutdatedJavaException;
import uk.co.hexeption.mcwrapper.MCWrapper;
import uk.co.hexeption.mcwrapper.base.MinecraftClient;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;
import uk.co.hexeption.mcwrapper.base.renderer.RenderManager;

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
    private ModelManager modelManager;

    @Shadow
    private net.minecraft.client.renderer.entity.RenderManager renderManager;

    @Shadow
    public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

    @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;init()V", shift = At.Shift.AFTER))
    public void init(CallbackInfo callbackInfo) {

        MCWrapper.setMinecraft((Minecraft) (Object) this);
        if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8)) {
            DarkForge.INSTANCE.start();
        } else {
            throw new OutdatedJavaException("Darkforge requires Java 8 or newer, Please update your java to the latest version");
        }
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

    @Override
    public ModelManager getModelManager() {

        return modelManager;
    }

    @Override
    public RenderManager getRenderManager() {

        return ((RenderManager) renderManager);
    }
}
