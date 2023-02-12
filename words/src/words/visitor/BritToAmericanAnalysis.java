package words.visitor;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import words.result.ResultI;
import words.util.ComponentI;
import words.util.FileProcessor;
import words.util.FileProcessorI;
import words.util.IteratorI;

public class BritToAmericanAnalysis implements VisitorI {

    private String BritToAmericanAnalysisResult;
    private FileProcessor fileProcessor;
    private ArrayList<String> inputWordList = new ArrayList<String>();
    private ArrayList<String> checkList = new ArrayList<String>();
    private ArrayList<String> outputWordCheckList = new ArrayList<String>();
    private IteratorI wordIterator;
    private String wordToReplace = "";

    private String inputSentence = "";
    private String tempWord;
    private String line;
    private ResultI BritAnalysis;
    private ArrayList<String> BritWords = new ArrayList<String>();
    private FileProcessor britFile;
    private String op;

    public BritToAmericanAnalysis(String inputFileSentences, String file2, ResultI britRes, String outputFile) {
        BritAnalysis = britRes;
        op = processWordsFromFile2(inputFileSentences, file2);
        writeToFile(op, outputFile);

    }

    private void writeToFile(String outputFile, String outputFile2) {
        // outputFile to newline after .
        outputFile = outputFile.replace(".", ".\n");
        // System.out.println(outputFile);
        BritAnalysis.storeNewResult(outputFile);
        // BritAnalysis.storeNewResult(outputFile);

    }

    // BritAnalysis.writeToFile();

    private String processWordsFromFile2(String inputFileSentences, String file2) {
        try {
            fileProcessor = new FileProcessor(inputFileSentences);
            britFile = new FileProcessor(file2);
            while ((line = britFile.readLine()) != null) {

                BritWords.add(line);
            }
            BritWords.replaceAll(String::toLowerCase);
            Map<String, String> map = new HashMap<>();
            for (String item : BritWords) {
                String[] parts = item.split(":");
                map.put(parts[0], parts[1]);
            }

            // replace(write) fileProcessor the words in the input file with the american
            // words ie key with value
            while ((line = fileProcessor.readLine()) != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    // lower case line
                    line = line.toLowerCase();
                    line = line.replaceAll(entry.getKey(), entry.getValue());
                }
                inputSentence = inputSentence.concat(line);
            }

            fileProcessor.close();
            britFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputSentence;

    }

    @Override
    public void visit(ComponentI myComponent) {
        // TODO Auto-generated method stub
        performScanner(myComponent.getIterator());

    }

    private void performScanner(IteratorI iterator) {
        while (iterator.hasNext()) {
            wordToReplace = "";

            tempWord = ((String) iterator.next()).trim().toLowerCase();

            for (String correctWord : inputWordList) {
                correctWord = correctWord.toLowerCase();

                if (!tempWord.equals(correctWord) && tempWord.length() == correctWord.length()) {
                    int count = 0;

                    for (int index = 0; index < tempWord.length(); index++) {
                        if (tempWord.charAt(index) != correctWord.charAt(index)) {
                            count++;
                        }
                        if (count > 1) {
                            break;
                        }
                    }
                    if (count == 1) {
                        wordToReplace = tempWord;
                        outputWordCheckList.add(correctWord);

                    }
                }
            }
            if (outputWordCheckList.size() > 0) {
                checkList.add(wordToReplace + "::" + outputWordCheckList.toString() + "\n");

                outputWordCheckList.clear();
            }

        }

    }

}
