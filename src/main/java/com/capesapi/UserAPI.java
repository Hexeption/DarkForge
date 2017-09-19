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

package com.capesapi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * A class to help communicate with the CapesAPI API.
 *
 * @author Matthew Hatcher
 * @version 2.2.1, February 2017
 */

public class UserAPI {

    private static String baseURL = "http://capesapi.com/api/v1/";

    public static boolean hasCape(UUID uuid, String capeId) throws IOException {

        URL url = new URL(baseURL + uuid.toString() + "/hasCape/" + capeId);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();

        boolean hasCape = false;
        int responseSays = Integer.parseInt(response.toString());

        if (responseSays == 1) {
            hasCape = true;
        }

        return hasCape;
    }

    public static void addCape(UUID uuid, String capeId) throws IOException {

        String data = "{\"capeId\": \"" + capeId + "\"}";
        URL url = new URL(baseURL + uuid.toString() + "/addCape");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(data.getBytes());
        out.flush();
        out.close();

        InputStream stream = con.getResponseCode() == 200 ? con.getInputStream() : con.getErrorStream();

        if (stream == null) {
            throw new IOException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();

        if (response.toString() != "1") {
            System.out.println(response.toString());
        }
    }
}