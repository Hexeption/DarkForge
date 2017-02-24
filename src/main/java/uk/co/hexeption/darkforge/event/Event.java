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

package uk.co.hexeption.darkforge.event;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Hexeption on 18/12/2016.
 */
public abstract class Event {

    /**
     * Main events you may need:
     * <p>
     * Minecraft:
     * - EventKeyboard
     * - EventMiddleClick
     * - EventTick
     * <p>
     * EntityPlayerSP:
     * - EventUpdate
     * - EventPreMotionUpdates
     * - EventPostMotionUpdates
     * <p>
     * GuiIngame:
     * - EventRender2D
     * <p>
     * EntityRenderer:
     * - EventRender3D
     */

    private boolean cancelled;

    private static final void call(final Event event) {

        final ArrayHelper<Data> dataList = EventManager.get(event.getClass());

        if (dataList != null) {
            for (final Data data : dataList) {

                try {
                    data.target.invoke(data.source, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Event call() {

        this.cancelled = false;
        call(this);
        return this;
    }

    public boolean isCancelled() {

        return cancelled;
    }

    public void setCancelled(boolean cancelled) {

        this.cancelled = cancelled;
    }

    public enum State {
        PRE("PRE", 0),

        POST("POST", 1);

        private State(final String string, final int number) {

        }
    }
}
