package uk.co.hexeption.darkforge.gui.base;

/**
 * Created by Hexeption on 15/01/2017.
 */

/**
 * Theme for the client gui
 */
public interface ISkin {

    void drawComponent(double x, double y, double width, double height, boolean isOver);

    void drawButton(double x, double y, double width, double height, boolean isOver);

    void drawLabel(double x, double y, double width, double height, boolean isOver);

    void drawRadioButton(double x, double y, double width, double height, boolean isActive);

    void drawWindow(double x, double y, double width, double height, boolean isOver);

    void drawControls(double x, double y, double width, double height, boolean isOver);

    void drawSlider(int x, int y, int width, int height, boolean b);

    int getTextColor(boolean window);

}
