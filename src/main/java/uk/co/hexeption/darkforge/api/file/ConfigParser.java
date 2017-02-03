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
