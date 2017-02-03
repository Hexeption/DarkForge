package uk.co.hexeption.darkforge.api.file;

/**
 * Created by Hexeption on 15/01/2017.
 */
public interface IFile {

    void readFile();

    void writeFile();

    void parseLine(String line);

}
