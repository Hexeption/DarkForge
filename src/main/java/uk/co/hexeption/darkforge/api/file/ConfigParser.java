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

package uk.co.hexeption.darkforge.api.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class ConfigParser extends BufferedIOManager {

    public ConfigParser(File file) {

        super(file);
    }

    public static ConfigParser getInstance(final File file) {

        return new ConfigParser(file);
    }

    @Override
    public synchronized List<String> read() {

        final List<String> readLines = new ArrayList<String>();
        String line;

        try {
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("#"))
                    continue;

                readLines.add(line);
            }
            return readLines;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
