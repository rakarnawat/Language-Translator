package words.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClearResults {

    public static void clearResults(String inputFilePath, int k) {
        try {
            List<String> distinctList = new ArrayList<String>();
            // Create a FileReader object to read the file "output.txt"
            File file = new File(inputFilePath);
            // Create a BufferedReader object to wrap the FileReader object,
            // so we can read the file line by line
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            // Read and print the contents of the file line by line
            String line = bufferedReader.readLine();
            List<String> lines = new ArrayList<String>();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            int lastIndex = lines.size() - 1;

            String lastLine = lines.get(lastIndex);

            Map<Integer, String> res = Arrays.stream(lastLine.replace("{", "").replace("}", "").split(", "))
                    .map(entry -> entry.split("="))
                    .collect(Collectors.toMap(entry -> Integer.parseInt(entry[0]), entry -> entry[1]));

            Map<Integer, String> sortedMap = res.entrySet().stream()
                    .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFilePath))) {
                Integer secondValueKey = null;
                int counter = 0;
                for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
                    if (counter == 0) {
                        bw.write(entry.getValue() + ":" + entry.getKey());
                        bw.newLine();
                    }
                    if (counter == 1) {

                        bw.write(entry.getValue() + ":" + entry.getKey());
                        bw.newLine();
                    }
                    if (counter == k) {
                        bw.write(entry.getValue() + ":" + entry.getKey());
                        bw.close();
                    }
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufferedReader.close();
        } catch (IOException e) {
            // Handle any errors that occurred while reading the file
            e.printStackTrace();
        }
    }

}
