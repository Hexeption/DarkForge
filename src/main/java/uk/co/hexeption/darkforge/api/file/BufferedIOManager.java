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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class BufferedIOManager implements IOManager {

    /**
     * The File that gets read/writter from/to
     */
    protected final File file;

    protected BufferedReader reader;

    protected BufferedWriter writer;

    public BufferedIOManager(File file) {

        this.file = file;
    }

    public static BufferedIOManager getInstance(final File file) {

        return new BufferedIOManager(file);
    }


    @Override
    public synchronized void setupRead() {

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void setupWrite() {

        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void closeStream() {

        try {
            if (reader != null)
                reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        try {
            if (writer != null)
                writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<String> read() {

        final List<String> readLines = new ArrayList<String>();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
            return readLines;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized void write(List<String> data) {

        for (final String s : data) {
            try {
                writer.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
