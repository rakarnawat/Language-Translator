package words.result;

import words.util.SaveDataHelper;

public class ResultKmostFreq implements ResultI {

    private String output = "";
    private SaveDataHelper dataSaver;
    private String outputFile;

    public ResultKmostFreq(String inputFile) {

        outputFile = inputFile;

        dataSaver = new SaveDataHelper(outputFile);
    }

    @Override
    public void storeNewResult(String resultIn) {

        output = output.concat(resultIn);

    }

    @Override
    public void writeToFile() {
        dataSaver.writeLine(output);
        dataSaver.close();
    }

    @Override
    public void printToStdout() {
        System.out.println(output.trim());
    }

}
