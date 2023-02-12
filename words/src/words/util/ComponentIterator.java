package words.util;

import java.util.ArrayList;

public class ComponentIterator implements IteratorI {

    private int index;

    private ArrayList<ComponentI> arrayList;

    public ComponentIterator(ArrayList<ComponentI> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        if (index < arrayList.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return arrayList.get(index++);
        }
        return null;
    }

}
