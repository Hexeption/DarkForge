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
