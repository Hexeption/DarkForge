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

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.alt.Alt;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.gui.alt.AltsSlot;
import uk.co.hexeption.darkforge.mod.Mod;
import uk.co.hexeption.darkforge.value.BooleanValue;
import uk.co.hexeption.darkforge.value.DoubleValue;
import uk.co.hexeption.darkforge.value.FloatValue;
import uk.co.hexeption.darkforge.value.Value;

import java.io.*;
import java.util.Map;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class FileManager {

    private static Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

    private static JsonParser jsonParser = new JsonParser();

    public final File DARKFORGE_DIR = new File(String.format("%s%sdarkforge%s", Minecraft.getMinecraft().mcDataDir, File.separator, File.separator));

    private final File MODULE = new File(DARKFORGE_DIR, "mods.json");

    private final File ALTS = new File(DARKFORGE_DIR, "alts.json");

    private final File FRIENDS = new File(DARKFORGE_DIR, "friends.json");

    public void Initialization() {

        if (!DARKFORGE_DIR.exists())
            DARKFORGE_DIR.mkdir();

        if (!MODULE.exists())
            saveModules();
        else
            loadModules();

        if (!ALTS.exists())
            saveAlts();
        else
            loadAlts();

        if (!FRIENDS.exists())
            saveFriends();
        else
            loadFriends();
    }


    public void loadModules() {

        try {
            BufferedReader loadJson = new BufferedReader(new FileReader(MODULE));
            JsonObject moduleJason = (JsonObject) jsonParser.parse(loadJson);
            loadJson.close();

            for (Map.Entry<String, JsonElement> entry : moduleJason.entrySet()) {
                Mod mods = DarkForge.INSTANCE.modManager.getModuleByName(entry.getKey());

                if (mods != null && mods.getCategory() != Mod.Category.GUI) {
                    JsonObject jsonMod = (JsonObject) entry.getValue();
                    boolean enabled = jsonMod.get("enabled").getAsBoolean();

                    if (enabled) {
                        mods.setState(true);
                    }

                    if (!mods.getValues().isEmpty()) {
                        for (Value value : mods.getValues()) {
                            if (value instanceof BooleanValue) {
                                boolean bvalue = jsonMod.get(value.getName()).getAsBoolean();
                                value.setValue(bvalue);
                            }
                            if (value instanceof DoubleValue) {
                                double dvalue = jsonMod.get(value.getName()).getAsDouble();
                                value.setValue(dvalue);
                            }
                            if (value instanceof FloatValue) {
                                float fvalue = jsonMod.get(value.getName()).getAsFloat();
                                value.setValue(fvalue);
                            }

                        }
                    }

                    mods.setBind(jsonMod.get("bind").getAsInt());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            LogHelper.error(e.getMessage());
        }
    }

    public void saveModules() {

        try {
            JsonObject json = new JsonObject();

            for (Mod mod : DarkForge.INSTANCE.modManager.getMods()) {
                JsonObject jsonModules = new JsonObject();
                jsonModules.addProperty("enabled", mod.getState());
                jsonModules.addProperty("bind", mod.getBind());

                if (!mod.getValues().isEmpty()) {
                    for (Value value : mod.getValues()) {
                        if (value instanceof BooleanValue) {
                            jsonModules.addProperty(value.getName(), (Boolean) value.getValue());
                        }
                        if (value instanceof DoubleValue) {
                            jsonModules.addProperty(value.getName(), (Number) value.getValue());
                        }
                        if (value instanceof FloatValue) {
                            jsonModules.addProperty(value.getName(), (Number) value.getValue());
                        }

                    }
                }

                json.add(mod.getName(), jsonModules);
            }

            PrintWriter saveJson = new PrintWriter(new FileWriter(MODULE));
            saveJson.println(gsonPretty.toJson(json));
            saveJson.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogHelper.error(e.getMessage());
        }
    }

    public void saveAlts() {

        try {
            JsonObject json = new JsonObject();

            for (Alt alt : AltsSlot.alts) {
                JsonObject jsonalts = new JsonObject();
                jsonalts.addProperty("name", alt.getName());
                jsonalts.addProperty("password", alt.getPassword());
                jsonalts.addProperty("cracked", alt.isCracked());
                jsonalts.addProperty("favourites", alt.isFavourites());
                json.add(alt.getEmail(), jsonalts);
            }

            PrintWriter savedJson = new PrintWriter(new FileWriter(ALTS));
            savedJson.println(gsonPretty.toJson(json));
            savedJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAlts() {

        try {
            BufferedReader loadjson = new BufferedReader(new FileReader(ALTS));
            JsonObject altsjson = (JsonObject) jsonParser.parse(loadjson);
            AltsSlot.alts.clear();

            for (Map.Entry<String, JsonElement> entry : altsjson.entrySet()) {
                JsonObject altzz = entry.getValue().getAsJsonObject();
                String email = entry.getKey();
                String name = altzz.get("name") == null ? "" : altzz.get("name").getAsString();
                String password = altzz.get("password") == null ? "" : altzz.get("password").getAsString();
                boolean cracked = altzz.get("cracked") == null || altzz.get("cracked").getAsBoolean();
                boolean favourites = altzz.get("favourites") != null && altzz.get("favourites").getAsBoolean();

                if (cracked) {
                    AltsSlot.alts.add(new Alt(email, favourites));
                } else {
                    AltsSlot.alts.add(new Alt(email, name, password, favourites));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFriends() {

        try {
            JsonObject jsonObject = new JsonObject();
            for (String username : DarkForge.INSTANCE.friendManager.getFriends().keySet()) {
                JsonObject object = new JsonObject();
                object.addProperty("alias", DarkForge.INSTANCE.friendManager.getFriends().get(username));
                jsonObject.add(username, object);
            }

            PrintWriter savedJson = new PrintWriter(new FileWriter(FRIENDS));
            savedJson.println(gsonPretty.toJson(jsonObject));
            savedJson.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadFriends() {

        try {
            BufferedReader loadjson = new BufferedReader(new FileReader(FRIENDS));
            JsonObject altsjson = (JsonObject) jsonParser.parse(loadjson);
            DarkForge.INSTANCE.friendManager.getFriends().clear();

            for (Map.Entry<String, JsonElement> entry : altsjson.entrySet()) {
                JsonObject friend = entry.getValue().getAsJsonObject();

                String username = entry.getKey();
                String alias = friend.get("alias").getAsString();
                DarkForge.INSTANCE.friendManager.addFriend(username, alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
