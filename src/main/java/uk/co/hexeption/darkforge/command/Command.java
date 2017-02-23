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

package uk.co.hexeption.darkforge.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Command {

    private String[] name = getClass().getAnnotation(CMDInfo.class).name();

    private String help = getClass().getAnnotation(CMDInfo.class).help();

    private String description = getClass().getAnnotation(CMDInfo.class).descrption();

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void execute(String input, String[] args) throws Exception;

    @Retention(RetentionPolicy.RUNTIME)
    public @interface CMDInfo {
        String[] name();

        String help();

        String descrption();

    }
}
