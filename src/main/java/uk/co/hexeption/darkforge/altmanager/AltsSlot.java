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

package uk.co.hexeption.darkforge.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.utils.LoginUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AltsSlot extends GuiSlot {
    public static int premiumAlts;
    public static int crackedAlts;
    public static String login;
    public static ArrayList<Alt> alts = new ArrayList<Alt>();
    public int selectedSlots;
    private AltManager lastScreen;

    public AltsSlot(Minecraft mc, AltManager lastScreen) {
        super(mc, lastScreen.width, lastScreen.height, 25, lastScreen.height - 45, 30);
        login = "";
        this.lastScreen = lastScreen;
    }

    public static void sortAlts() {
        Collections.sort(alts, new Comparator<Alt>() {
            @Override
            public int compare(Alt o1, Alt o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }

                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        ArrayList<Alt> newAlts = new ArrayList<Alt>();
        premiumAlts = 0;
        crackedAlts = 0;

        for (int i = 0; i < alts.size(); i++) {
            if (alts.get(i).isFavourites()) {
                newAlts.add(alts.get(i));
            }

            if (!alts.get(i).isCracked() && !alts.get(i).isFavourites()) {
                newAlts.add(alts.get(i));
            }

            if (alts.get(i).isCracked() && !alts.get(i).isFavourites()) {
                newAlts.add(alts.get(i));
            }
        }

        for (int i = 0; i < newAlts.size(); i++) {
            for (int y = 0; y < newAlts.size(); y++) {
                if (i != y && newAlts.get(i).getEmail().equals(newAlts.get(y).getEmail()) && newAlts.get(i).isCracked() == newAlts.get(y).isCracked()) {
                    newAlts.remove(y);
                }
            }

            if (newAlts.get(i).isCracked()) {
                crackedAlts++;
            } else {
                premiumAlts++;
            }
        }

        alts = newAlts;
    }

    @Override
    protected boolean isSelected(int slotIndex) {
        return selectedSlots == slotIndex;
    }

    protected int getSelectedSlots() {
        if (selectedSlots > alts.size()) {
            selectedSlots = alts.size();
        }

        return selectedSlots;
    }

    @Override
    protected int getSize() {
        return alts.size();
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
        selectedSlots = slotIndex;

        if (isDoubleClick) {
            Alt loginAlt = alts.get(slotIndex);

            if (loginAlt.isCracked()) {
                LoginUtils.changeCrackedName(loginAlt.getName());
                mc.displayGuiScreen(lastScreen);
            } else {
                login = LoginUtils.loginAlt(loginAlt.getEmail(), loginAlt.getPassword());

                if (login.equals("")) {
                    mc.displayGuiScreen(lastScreen);
                } else {
                    if (login.equals("§4§lWrong password!")) {
                        alts.remove(slotIndex);
                    }
                }
            }
        }
    }

    @Override
    protected void drawBackground() {
    }

    @Override
    protected void drawSlot(int entryID, int insideLeft, int yPos, int insideSlotHeight, int mouseXIn, int mouseYIn) {
        Alt alt = alts.get(entryID);

        DarkForge.FONT_MANAGER.hud.drawCenteredString(alt.getName(), width / 2, yPos + 3, 16777215);
        DarkForge.FONT_MANAGER.hud.drawCenteredString((alt.isCracked() ? "§8Cracked" : "§2Premium") + (alt.isFavourites() ? "§r & §eFavorited" : ""), width / 2, yPos + 15, 16777215);
    }
}