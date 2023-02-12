package words.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor implements FileProcessorI {

    private BufferedReader br;

    public FileProcessor(String inputFilePath) throws FileNotFoundException {
        File file = new File(inputFilePath);
        br = new BufferedReader(new FileReader(file));
    }

    public String readLine() {
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
