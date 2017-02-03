package uk.co.hexeption.darkforge.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;
import uk.co.hexeption.darkforge.module.modules.Gui;
import uk.co.hexeption.darkforge.module.modules.Tracers;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class EventManager {

    private final boolean[] keyStates = new boolean[256];

    public EventManager() {

        LogHelper.info("Setting up Forge Events...");
    }

    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent serverTickEvent) {

        if (Minecraft.getMinecraft().world != null) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (module.getState()) {
                    module.onWorldTick();
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {

        if (Minecraft.getMinecraft().world != null) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (module.getState()) {
                    module.onWorldRender();
                }
            }
        }
    }

    @SubscribeEvent
    public void onClientEvent(final TickEvent.ClientTickEvent clientTickEvent) {

        if (Minecraft.getMinecraft().world != null) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (checkKey(module.getBind())) {
                    LogHelper.info(String.format("Found Module %s for key %s", module.getName(), module.getBind()));
                    module.toggle();
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public void onChatSend(final ServerChatEvent serverChatEvent) {
        //TODO: Commands Send
    }

    @SubscribeEvent
    public void onGuiRender(final RenderGameOverlayEvent.Chat eventrender) {

        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;

        if (Minecraft.getMinecraft().world != null) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (module.getState()) {
                    module.onGuiRender();
                }
            }

            for (final Window window : ModuleManager.getInstance().getModuleByClass(Gui.class).getGui().getWindowList()) {
                if (window.getPinned()) {
                    window.drawWindow(0, 0);
                }
            }

        }
    }

    private boolean checkKey(int bind) {

        if (Minecraft.getMinecraft().currentScreen != null) {
            return false;
        }

        try {
            if (Keyboard.getEventKey() > -1) {
                if (Keyboard.isKeyDown(bind) != keyStates[bind]) {
                    return keyStates[bind] = !keyStates[bind];
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (final IndexOutOfBoundsException e) {
            LogHelper.error(e.getMessage());
            return false;
        }
    }

    @SubscribeEvent
    public void EntityViewRenderEvent(RenderPlayerEvent event) {

        Tracers.ticks = event.getPartialRenderTick();

    }


}
