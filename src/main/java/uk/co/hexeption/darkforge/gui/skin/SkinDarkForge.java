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

package uk.co.hexeption.darkforge.gui.skin;

import mezz.jei.util.MathUtil;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.utils.GuiUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;

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
