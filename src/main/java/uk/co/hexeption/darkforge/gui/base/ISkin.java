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
