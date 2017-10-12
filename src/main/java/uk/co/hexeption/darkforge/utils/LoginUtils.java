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

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.hexeption.darkforge.MC;

import java.lang.reflect.Field;
import java.net.Proxy;

public class LoginUtils implements MC{

    private static final Logger logger = LogManager.getLogger();

    public static String loginAlt(String email, String password) {

        YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        String displayText = null;

        try {
            authentication.logIn();
            mixMC.setSession(new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
            displayText = "";
        } catch (AuthenticationUnavailableException e) {
            displayText = "§4§lCannot contact authentication server!";
        } catch (AuthenticationException e)    // wrong password account migrated
        {
            if (e.getMessage().contains("Invalid username or password.") || e.getMessage().toLowerCase().contains("account migrated")) {
                displayText = "§4§lWrong password!";
            } else {
                displayText = "§4§lCannot contact authentication server!";
            }

            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            displayText = "§4§lWrong password!";
        }

        return displayText;
    }

    public static String getName(String email, String password) {

        YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);

        try {
            authentication.logIn();
            return authentication.getSelectedProfile().getName();
        } catch (Exception e) {
            return null;
        }
    }

    public static void changeCrackedName(String name) {

        mixMC.setSession(new Session(name, "", "", "mojang"));
    }


}
