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

package uk.co.hexeption.darkforge.config;

import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.Module;

import java.io.*;
import java.util.Map;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
public class FileManager {

    public final File DARKFORGE_DIR = new File(String.format("%s%sdarkforge%s", Minecraft.getMinecraft().mcDataDir, File.separator, File.separator));

    private final File MODULE = new File(DARKFORGE_DIR, "modules.json");

    private static Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

    private static JsonParser jsonParser = new JsonParser();

    public void initialization() {

        if (!DARKFORGE_DIR.exists())
            DARKFORGE_DIR.mkdir();

        if (!MODULE.exists())
            saveModules();
        else
            loadModules();
    }

    public void loadModules() {

        try {
            BufferedReader loadJson = new BufferedReader(new FileReader(MODULE));
            JsonObject moduleJason = (JsonObject) jsonParser.parse(loadJson);
            loadJson.close();

            for (Map.Entry<String, JsonElement> entry : moduleJason.entrySet()) {
                Module mods = DarkForge.instance.MODULE_MANAGER.getModuleByName(entry.getKey());

                if (mods != null && mods.getCategory() != Module.Category.GUI) {
                    JsonObject jsonMod = (JsonObject) entry.getValue();
                    boolean enabled = jsonMod.get("enabled").getAsBoolean();

                    if (enabled) {
                        mods.setState(true);
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

            for (Module module : DarkForge.instance.MODULE_MANAGER.getModules()) {
                JsonObject jsonModules = new JsonObject();
                jsonModules.addProperty("enabled", module.getState());
                jsonModules.addProperty("bind", module.getBind());

                json.add(module.getName(), jsonModules);
            }

            PrintWriter saveJson = new PrintWriter(new FileWriter(MODULE));
            saveJson.println(gsonPretty.toJson(json));
            saveJson.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogHelper.error(e.getMessage());
        }
    }

}
