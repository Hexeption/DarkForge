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

import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
public interface IOManager {

    /**
     * Sets up the input stream
     */
    void setupRead();

    /**
     * Sets up the output Stream
     */
    void setupWrite();

    /**
     * Closes all streams
     */
    void closeStream();

    /**
     * Reads all the lines in the file, returns them as a list
     *
     * @return
     */
    List<String> read();

    /**
     * Write the list to the file
     *
     * @param data
     */
    void write(List<String> data);

}
