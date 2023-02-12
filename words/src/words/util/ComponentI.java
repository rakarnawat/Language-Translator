package words.util;

import words.visitor.VisitorI;

public interface ComponentI {
    public void accept(VisitorI visitor);

    public IteratorI getIterator();

}
