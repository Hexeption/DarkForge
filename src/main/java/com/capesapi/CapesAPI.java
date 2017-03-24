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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A simple CapesAPI implementation for Minecraft client developers.
 *
 * @author Matthew Hatcher
 * @author Marco MC
 * @version 2.1.0, February 2017
 */
public class CapesAPI {

    private static Map<UUID, ResourceLocation> capes = new HashMap<>();

    /**
     * Load cape from the webserver and put the cape as resourcelocation to the capes hashmap
     *
     * @param uuid
     */
    public static void loadCape(final UUID uuid) {

        String url = "http://capesapi.com/api/v1/" + uuid.toString() + "/getCape";
        final ResourceLocation resourceLocation = new ResourceLocation("capeapi/capes/" + uuid.toString() + ".png");
        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

        IImageBuffer iImageBuffer = new IImageBuffer() {

            @Override
            public BufferedImage parseUserSkin(BufferedImage image) {

                return image;
            }

            @Override
            public void skinAvailable() {

                capes.put(uuid, resourceLocation);
            }
        };

        ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData(null, url, null, iImageBuffer);
        textureManager.loadTexture(resourceLocation, threadDownloadImageData);
    }

    /**
     * Remove the cape of the user from the cape hashmap
     *
     * @param uuid
     */
    public static void deleteCape(UUID uuid) {

        capes.remove(uuid);
    }

    /**
     * Get the cape of the user from the cape hashmap
     *
     * @param uuid
     * @return
     */
    public static ResourceLocation getCape(UUID uuid) {

        return capes.getOrDefault(uuid, null);
    }

    public static boolean hasCape(UUID uuid) {

        return capes.containsKey(uuid);
    }
}
