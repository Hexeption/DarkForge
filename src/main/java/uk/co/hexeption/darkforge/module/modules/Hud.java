package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.api.annotation.Enabled;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.module.ModuleManager;

/**
 * Created by Hexeption on 15/01/2017.
 */
@Enabled
@Module.ModInfo(name = "Hud", description = "UI", category = Module.Category.GUI, bind = 0)
public class Hud extends Module {



    @Override
    public void onGuiRender() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        getFontRenderer().drawStringWithShadow(DarkForge.MOD_NAME + " v" + DarkForge.VERSION, 1,1, 0xffffffff);
        getFontRenderer().drawStringWithShadow("Minecraft v" + MinecraftForge.MC_VERSION +" Forge v" + ForgeVersion.getVersion(), 1,10, 0xffffffff);

        arrayList(scaledResolution);
    }

    private void arrayList(ScaledResolution scaledResolution) {

        int offset = -getFontRenderer().FONT_HEIGHT + 2;
        if (!getGameSettings().showDebugInfo) {
            for (final Module module : ModuleManager.getInstance().getModules()) {
                if (module.equals(this)) {
                    continue;
                }
                if (module.getState()) {
                    getFontRenderer().drawStringWithShadow(module.getName(), (scaledResolution.getScaledWidth() - 3) - getFontRenderer().getStringWidth(module.getName()), offset += getFontRenderer().FONT_HEIGHT + 1, module.getCategory().color);
                }
            }
        }
    }
}
