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

package uk.co.hexeption.darkforge.alt;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.utils.LoginUtils;

@SideOnly(Side.CLIENT)
public class Alt {

    private String email;

    private String name;

    private String password;

    private boolean cracked;

    private boolean favourites;

    public Alt(String email, String password, boolean favourites) {

        this.email = email;
        this.favourites = favourites;

        if (password == null || password.isEmpty()) {
            name = email;
            this.password = null;
            cracked = true;
        } else {
            name = LoginUtils.getName(email, password);
            this.password = password;
            this.cracked = false;
        }
    }

    public Alt(String email, String password) {

        this(email, password, false);
    }

    public Alt(String email, String name, String password, boolean favourites) {

        this.email = email;
        this.favourites = favourites;

        if (password == null || password.isEmpty()) {
            name = email;
            this.password = null;
            cracked = true;
        } else {
            this.name = name;
            this.password = password;
            this.cracked = false;
        }
    }

    public Alt(String name, boolean favourites) {

        this.email = name;
        this.name = name;
        this.password = null;
        this.cracked = true;
        this.favourites = favourites;
    }

    public Alt(String name) {

        this(name, false);
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;

        if (password == null || password.isEmpty()) {
            name = email;
            password = null;
            cracked = true;
        } else {
            name = LoginUtils.getName(email, password);
            cracked = false;
        }
    }

    public String getName() {

        if (name != null) {
            return name;
        } else if (email != null) {
            return email;
        } else {
            return "";
        }
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        if (password == null || password.isEmpty()) {
            cracked = true;
            return "";
        } else {
            return password;
        }
    }

    public void setPassword(String password) {

        this.password = password;

        if (password == null || password.isEmpty()) {
            name = email;
            password = null;
            cracked = true;
        } else {
            name = LoginUtils.getName(email, password);
            this.password = password;
            cracked = false;
        }
    }

    public boolean isCracked() {

        return cracked;
    }

    public boolean isFavourites() {

        return favourites;
    }

    public void setFavourites(boolean favourites) {

        this.favourites = favourites;
    }

    public void setCracked() {

        name = email;
        password = null;
        cracked = true;
    }
}