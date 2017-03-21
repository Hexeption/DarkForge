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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.net.Proxy;

@SideOnly(Side.CLIENT)
public class LoginUtils {

    private static final Logger logger = LogManager.getLogger();

    public static void setSession(Session s) throws IllegalAccessException {

        Class<? extends Minecraft> mc = Minecraft.getMinecraft().getClass();
        try {
            Field session = null;

            for (Field f : mc.getDeclaredFields()) {
                if (f.getType().isInstance(s)) {
                    session = f;
                }
            }

            if (session == null) {
                throw new IllegalStateException("no field of type " + Session.class.getCanonicalName() + " declared.");
            }

            session.setAccessible(true);
            session.set(Minecraft.getMinecraft(), s);
            session.setAccessible(false);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String loginAlt(String email, String password) {

        YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) authenticationService.createUserAuthentication(Agent.MINECRAFT);
        authentication.setUsername(email);
        authentication.setPassword(password);
        String displayText = null;

        try {
            authentication.logIn();
            setSession(new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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

        try {
            setSession(new Session(name, "", "", "mojang"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
