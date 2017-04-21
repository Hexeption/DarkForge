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
package uk.co.hexeption.darkforge.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.MC;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.events.EventRenderScreen;
import uk.co.hexeption.darkforge.event.events.EventTick;
import uk.co.hexeption.darkforge.event.events.EventWorld;
import uk.co.hexeption.darkforge.managers.EventManager;
import uk.co.hexeption.darkforge.mixin.imp.IMixinMinecraft;
import uk.co.hexeption.darkforge.utils.InputHandler;

import javax.annotation.Nullable;

/**
 * Created by Keir on 21/04/2017.
 */
@Mixin(Minecraft.class)
public class MixinMinecraft implements IMixinMinecraft, MC {


    @Shadow
    @Final
    private Timer timer;

    @Mutable
    @Shadow
    @Final
    private Session session;

    /**
     * Injections
     */

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void IstartGame(CallbackInfo callback) {
        DarkForge.INSTANCE.start();
    }

    @Inject(method = "runGameLoop", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;startSection(Ljava/lang/String;)V", args = "ldc=tick", shift = At.Shift.AFTER))
    private void IrunGameLoop(CallbackInfo callback) {
        EventTick event = new EventTick(Event.Type.PRE);
        EventManager.handleEvent(event);
    }

    @Inject(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender(FJ)V", shift = At.Shift.AFTER))
    private void IrenderScreenPost(CallbackInfo callback) {
        if (!((Minecraft) (Object) this).skipRenderWorld) {
            GlStateManager.pushMatrix();
            ScaledResolution resolution = new ScaledResolution((Minecraft) (Object) this);
            double scale = resolution.getScaleFactor() / Math.pow(resolution.getScaleFactor(), 2);
            GlStateManager.scale(scale, scale, scale);
            EventRenderScreen event = new EventRenderScreen(Event.Type.POST);
            EventManager.handleEvent(event);
            GlStateManager.popMatrix();
        }
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
    public void IrunTickKeyboard(CallbackInfo callback) {
        InputHandler.handleKeyboard();
    }

    @Inject(method = "runTickMouse", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Mouse;getEventButton()I", ordinal = 0, shift = At.Shift.BEFORE))
    public void IrunTickMouse(CallbackInfo callback) {
        InputHandler.handleKeyboard();
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    public void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo callback) {
        Event event;
        if (worldClientIn != null) {
            event = new EventWorld.Load(Event.Type.PRE, worldClientIn);
        } else {
            event = new EventWorld.Unload(Event.Type.PRE, null);
        }
        EventManager.handleEvent(event);
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }
}
