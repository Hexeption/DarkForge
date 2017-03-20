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

package uk.co.hexeption.darkforge.managers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hexeption on 20/03/2017.
 */
public class FriendManager {

    private final Map<String, String> friends = new HashMap<>();


    public void addFriend(String username, String alias) {

        friends.put(username.toLowerCase(), alias);

    }

    public void addFriend(String username) {

        addFriend(username.toLowerCase(), "");
    }

    public void removeFriend(String username) {

        friends.remove(username.toLowerCase());
    }

    public boolean hasAlias(String username) {

        return !friends.get(username.toLowerCase()).isEmpty();
    }

    public boolean isFriend(String username) {

        return friends.containsKey(username.toLowerCase());
    }

    public String getAlias(String username) {

        String alias = friends.get(username.toLowerCase());
        return alias == null ? username : alias;
    }

    public Map<String, String> getFriends() {

        return friends;
    }
}
