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

package uk.co.hexeption.darkforge.gui.gui.theme;

import uk.co.hexeption.darkforge.font.MinecraftFontRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;

import java.util.HashMap;

/**
 * Created by Hexeption on 27/02/2017.
 */
public class Theme {

    public MinecraftFontRenderer fontRenderer;

    private HashMap<ComponentType, ComponentRenderer> rendererHashMap = new HashMap<>();

    private String themeName;

    private int frameHeight = 15;

    public Theme(String themeName) {

        this.themeName = themeName;
    }

    public void addRenderer(ComponentType componentType, ComponentRenderer componentRenderer) {

        this.rendererHashMap.put(componentType, componentRenderer);
    }

    public HashMap<ComponentType, ComponentRenderer> getRenderer() {

        return rendererHashMap;
    }

    public String getThemeName() {

        return themeName;
    }

    public MinecraftFontRenderer getFontRenderer() {

        return fontRenderer;
    }

    public int getFrameHeight() {

        return frameHeight;
    }
}
