package words.util;

import java.util.ArrayList;

public class ComponentWordIterator implements IteratorI {

    private int index;

    private ArrayList<String> words;

    public ComponentWordIterator(ArrayList<String> words) {
        this.words = words;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < words.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return words.get(index++);
        }
        return null;
    }

}