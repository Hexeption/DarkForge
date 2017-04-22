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

package uk.co.hexeption.darkforge.ui.hud;

import net.minecraft.client.Minecraft;
import uk.co.hexeption.darkforge.event.Event;
import uk.co.hexeption.darkforge.event.EventListener;
import uk.co.hexeption.darkforge.event.events.EventKeyboard;
import uk.co.hexeption.darkforge.event.events.EventRenderScreen;
import uk.co.hexeption.darkforge.managers.EventManager;
import uk.co.hexeption.darkforge.ui.hud.themes.DarkForgeHud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hud implements EventListener {

    private final List<IGameHud> themes = new CopyOnWriteArrayList<>();

    private int themeIndex = 0;

    public Hud() {

        EventManager.register(this);
    }

    public void Initialization() {

        this.themes.add(new DarkForgeHud());
    }

    public IGameHud getCurrentTheme() {

        return this.themes.get(this.themeIndex);
    }

    public IGameHud getTheme(String name) {

        for (IGameHud theme : this.themes) {
            if (theme.name().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public void onNextTheme() {

        int index = this.themeIndex;
        int maxSize = this.themes.size();

        if (index != -1) {
            index++;

            if (index >= maxSize) {
                index = 0;
            }

            this.themeIndex = index;
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRenderScreen) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo || Minecraft.getMinecraft().currentScreen != null)
                return;

            IGameHud currentTheme = getCurrentTheme();
            currentTheme.render(Minecraft.getMinecraft(), ((EventRenderScreen) event).getWidth(), ((EventRenderScreen) event).getHeight());
        } else if (event instanceof EventKeyboard) {
            getCurrentTheme().onKeyPressed(((EventKeyboard) event).getKey());
        }
    }
}
