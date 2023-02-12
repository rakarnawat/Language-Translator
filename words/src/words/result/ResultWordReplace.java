package words.result;

import words.util.SaveDataHelper;

public class ResultWordReplace implements ResultI {

    private String output = "";
    private SaveDataHelper dataSaver;
    private String outputFile;

    public ResultWordReplace(String inputFile) {

        outputFile = inputFile;

        dataSaver = new SaveDataHelper(outputFile);
    }

    @Override
    public void storeNewResult(String resultIn) {
        // TODO Auto-generated method stub
        output = output.concat(resultIn);

    }

    @Override
    public void writeToFile() {
        // TODO Auto-generated method stub
        dataSaver.writeLine(output);
        dataSaver.close();

    }

    @Override
    public void printToStdout() {
        // TODO Auto-generated method stub
        System.out.println(output.trim());

    }

}
