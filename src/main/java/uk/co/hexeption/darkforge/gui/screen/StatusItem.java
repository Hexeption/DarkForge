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

package uk.co.hexeption.darkforge.gui.screen;

import net.minecraft.util.text.TextFormatting;
import uk.co.hexeption.darkforge.utils.URLUtils;

import java.net.URL;

/**
 * Created by Hexeption on 09/04/2017.
 */
public class StatusItem {

    private String name;

    private String service;

    private String customAPI;

    private String statusName;

    private int status;

    private long ping;

    public StatusItem(String name, String service) {

        this.name = name;
        this.service = service;
        this.status = 3;
        this.ping = -1L;
    }

    public StatusItem(String name, String service, String customAPI, String statusName) {

        this.name = name;
        this.service = service;
        this.customAPI = customAPI;
        this.statusName = statusName;
        this.status = 3;
        this.ping = -1L;
    }

    public void check() {
        if (customAPI == null) {
            new Thread(() -> {

                try {
                    long startTime = System.currentTimeMillis();
                    this.status = 3;
                    if (((String) URLUtils.getWebsiteContents(new URL("http://status.mojang.com/check?service=" + this.service)).get(0)).contains("green")) {
                        this.status = 1;
                        this.ping = (System.currentTimeMillis() - startTime);
                    } else {
                        this.status = 0;
                    }
                } catch (Exception e) {
                    this.status = 2;
                }

            }).start();
        } else {
            new Thread(() -> {

                try {
                    long startTime = System.currentTimeMillis();
                    this.status = 3;
                    if (((String) URLUtils.getWebsiteContents(new URL(this.customAPI)).get(0)).contains(this.statusName)) {
                        this.status = 1;
                        this.ping = (System.currentTimeMillis() - startTime);
                    } else {
                        this.status = 0;
                    }
                } catch (Exception e) {
                    this.status = 2;
                }

            }).start();
        }

    }

    public String getStatus() {

        if (this.status == 0) {
            return TextFormatting.RED + "Offline";

        }
        if (this.status == 1) {
            return TextFormatting.GREEN + "Online";

        }
        if (this.status == 2) {
            return TextFormatting.RED + "Error";

        }
        return TextFormatting.GOLD + "Loading..";
    }

    public String getPing() {

        if (this.ping == -1L) {
            return "N/A";
        }
        if (this.ping >= 1000L) {
            return Long.toString(this.ping / 1000L) + "s";
        }

        return Long.toString(this.ping) + "ms";
    }

    public String getName() {

        return name;
    }

    public String getService() {

        return service;
    }
}
