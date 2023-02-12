package words.result;

public interface ResultI {

    public void storeNewResult(String topKWordsList);

    public void writeToFile();

    public void printToStdout();
}
