package uk.co.hexeption.darkforge.gui.components;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import uk.co.hexeption.darkforge.gui.base.Component;
import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.gui.base.Window;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class Scrollbar extends Component {

    private float curval = 0;

    private boolean dragging = false;

    public Scrollbar() {

        this(8, 90, 0.0f, 10f);
    }

    private Scrollbar(final int w, final int h, final float min, final float max) {

        super();
    }

    @Override
    public void draw(Window window, ISkin skin, int mouseX, int mouseY) {

        setW(8);
        setH(getParent().getWindowHeight() + getW() + (getParent().getSpacer() ? 0 : -getParent().getSpacerHeight()));
        final DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        curval = Float.parseFloat(decimalFormat.format(curval));
        skin.drawSlider(getX(), getY(), getW(), getH() + (getW() / 2), false);
        float s1 = (getY() + ((curval) * getH())) - (getW() / 2);
        float s2 = getH();
        if (s2 > ((getY()) + getH())) {
            s2 = (getY() + getH());
        }
        if (s1 < getY()) {
            s1 = getY();
        }
        skin.drawSlider(getX(), (int) s1, getW(), (int) s2, true);
    }

    @Override
    public void update() {

        if (!Mouse.isButtonDown(0) && dragging) {
            dragging = false;
        }

        if (dragging) {
            if (!isMouseOver()) {
                dragging = false;
                return;
            } else {
                final Point point = calculateMouseLocation();
                mouseClicked(getParent(), point.x, point.y, 0);
            }
        } else {
            if (isMouseOver()) {
                if (Mouse.isButtonDown(0)) {
                    final Point point = calculateMouseLocation();
                    mouseClicked(getParent(), point.x, point.y, 0);
                }
            } else {
                dragging = false;
                return;
            }
        }

        getParent().setScrollOffset(curval);
    }

    @Override
    public void drawExtra() {

    }

    @Override
    public void mouseClicked(Window window, int mouseX, int mouseY, int button) {

        if (!getParent().getDragging()) {

            final Point point = new Point(mouseX, mouseY);
            if(isMouseOver()){
                dragging = true;
                curval = (float) (point.y - getY()) / (float) getH();
                if(curval < 0.04){
                    curval = 0;
                }
                if(curval > 0.96){
                    curval = 1;
                }
            }else{
                dragging = false;
            }
        }
    }

    @Override
    public void keyTyped(Window window, int key, char c) {

    }
}
