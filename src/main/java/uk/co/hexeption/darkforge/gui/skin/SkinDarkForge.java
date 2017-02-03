package uk.co.hexeption.darkforge.gui.skin;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.utils.GuiUtils;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class SkinDarkForge implements ISkin {

    @Override
    public void drawComponent(double x, double y, double width, double height, boolean isOver) {

        GuiUtils.drawRect((float) x, (float) y, (float) width, (float) height, isOver ? 0xff008888 : 0xff888888);
    }

    @Override
    public void drawButton(double x, double y, double width, double height, boolean isOver) {

//        GuiUtils.drawRect((float) x, (float) y, (float) width, (float) height, isOver ? 0x77000000 : 0x0);
    }

    @Override
    public void drawLabel(double x, double y, double width, double height, boolean isOver) {

    }

    @Override
    public void drawRadioButton(double x, double y, double width, double height, boolean isActive) {

        GuiUtils.drawRect(2, 2, 12, 12, isActive ? 0x77000000 : 0x0);
    }

    @Override
    public void drawWindow(double x, double y, double width, double height, boolean isOver) {

        GuiUtils.drawGradientRect((int) x, (int) y, (int) width, (int) height, 0x98989898, 0x98787878);
    }

    @Override
    public void drawControls(double x, double y, double width, double height, boolean isOver) {

        GuiUtils.drawRect((float) x, (float) y, (float) width, (float) height, isOver ? 0x77000000 : 0x33000000);

    }

    @Override
    public void drawSlider(int x, int y, int width, int height, boolean b) {
        if (b){
            GuiUtils.drawRect(x,y,width,height, 0xaa000000);
        }else {
            GuiUtils.drawRect(x,y,width, height, 0x77000000);
        }
    }

    @Override
    public int getTextColor(boolean window) {

        return -1;
    }
}
