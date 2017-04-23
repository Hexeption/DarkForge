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

package uk.co.hexeption.darkforge.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class GuiUtils {


    public static ScaledResolution getScaledResolution() {

        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return scaledResolution;
    }

    public static int getWidth() {

        final ScaledResolution sr = getScaledResolution();
        return sr.getScaledWidth();
    }

    public static int getHeight() {

        final ScaledResolution sr = getScaledResolution();
        return sr.getScaledHeight();
    }
}
