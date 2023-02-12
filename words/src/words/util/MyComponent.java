package words.util;

import java.util.ArrayList;
import java.util.Arrays;

import words.visitor.VisitorI;

public class MyComponent implements ComponentI {

    private ArrayList<String> wordsList;

    public MyComponent(String inputSentence) {
        wordsList = new ArrayList<>(Arrays.asList(inputSentence.split("\\s"))); // split on whiteSpace
    }

    @Override
    public void accept(VisitorI visitor) {
        visitor.visit(this);
    }

    @Override
    public IteratorI getIterator() {

        return new ComponentWordIterator(wordsList);
    }

}
