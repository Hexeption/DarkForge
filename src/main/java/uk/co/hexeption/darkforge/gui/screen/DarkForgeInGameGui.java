package uk.co.hexeption.darkforge.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import uk.co.hexeption.darkforge.event.events.render.Render2DEvent;
import uk.co.hexeption.darkforge.utils.ReflectionHelper;

/**
 * Created by Hexeption on 12/03/2017.
 */
public class DarkForgeInGameGui extends GuiIngame {

    private final Minecraft mc;

    public DarkForgeInGameGui(Minecraft mc) {

        super(mc);
        this.mc = mc;
        ReflectionHelper.setPersistantChatGUI(this, new DarkForgeChat(mc));
    }

    @Override
    public void renderGameOverlay(float partialTicks) {

        super.renderGameOverlay(partialTicks);

        if (!mc.gameSettings.showDebugInfo) {
            Render2DEvent eventRender2D = new Render2DEvent(mc.displayWidth, mc.displayHeight);
            eventRender2D.call();
        }
    }
}
