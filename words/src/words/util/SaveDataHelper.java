package words.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SaveDataHelper {
    private BufferedWriter bufWriter;
    private File file;
    private FileWriter fileWriter;

    public SaveDataHelper(String outputFile) {
        try {
            file = new File(outputFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            bufWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line) {
        try {
            bufWriter.write(line.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufWriter.flush();
            if (bufWriter != null) {
                bufWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
