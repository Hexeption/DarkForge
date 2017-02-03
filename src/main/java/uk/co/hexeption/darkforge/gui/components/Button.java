package uk.co.hexeption.darkforge.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.tools.nsc.settings.Final;
import uk.co.hexeption.darkforge.gui.base.Component;
import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.GuiUtils;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class Button extends Component {

    private final Module module;

    public Button(final String text, final Module module) {

        super(text, (getFontRenderer().getStringWidth(text)) + 6, 14);
        this.module = module;
    }

    @Override
    public void draw(Window window, ISkin skin, int mouseX, int mouseY) {

        skin.drawButton(getX(), getY(), getW(), getH(), isMouseOver() || module.getState());
        getFontRenderer().drawString((module.getState() ? "\u00a7a" : "") + getText(), getX() + 3, getY() + 3, skin.getTextColor(false));

        if (isMouseOver()) {
            GuiUtils.disableScissoring();
            renderTooltip();
            GuiUtils.enableScissoring();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void drawExtra() {

    }

    @Override
    public void mouseClicked(Window window, int mouseX, int mouseY, int button) {

        playSound(SoundEvents.UI_BUTTON_CLICK);
        module.toggle();
    }

    private void renderTooltip() {

        final int h = GuiUtils.getHeight();
        final int w = getFontRenderer().getStringWidth(module.getDescription());

        Gui.drawRect(0, h - 11, w + 3, h, 0x77000000);
        getFontRenderer().drawString(module.getDescription(), 1, h - 7, 0xffffffff);
    }

    @Override
    public void keyTyped(Window window, int key, char c) {

    }

    protected void playSound(final SoundEvent sound) {

        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sound, 1.0f));
    }
}
