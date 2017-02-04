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

package uk.co.hexeption.darkforge.gui.windows;

import uk.co.hexeption.darkforge.gui.base.ISkin;
import uk.co.hexeption.darkforge.gui.base.Window;
import uk.co.hexeption.darkforge.gui.components.Radar;
import uk.co.hexeption.darkforge.gui.skin.SkinDarkForge;

/**
 * Created by Hexeption on 16/01/2017.
 */
public class WindowRadar extends Window {

    public WindowRadar( int x, int y) {

        super("Radar",new SkinDarkForge(), x, y,0,0 , true);
    }

    @Override
    public void addComponents() {
        addChild(new Radar());
    }
}
