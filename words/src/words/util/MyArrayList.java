package words.util;

import words.visitor.VisitorI;
import java.util.ArrayList;

public class MyArrayList implements ComponentI {

    private static ComponentI myArrListObj = new MyArrayList();

    private static ArrayList<ComponentI> myArrList = new ArrayList<ComponentI>();

    public static class Builder {
        private String line = "";

        private String sentence = "";

        public Builder fileProcessor(FileProcessorI file) {
            while ((line = file.readLine()) != null) {
                if (line.indexOf(".") > 0) {
                    String[] lineList = line.split("\\.");
                    for (int index = 0; index < lineList.length - 1; index++) {
                        if (sentence.trim().length() > 0) {
                            lineList[index] = sentence.concat(lineList[index]);
                            sentence = "";
                        }
                        myArrList.add(new MyComponent(lineList[index].trim()));
                    }
                    sentence = sentence.concat(lineList[lineList.length - 1]);

                    if (line.lastIndexOf(".") == line.length() - 1) {
                        myArrList.add(new MyComponent(sentence.trim()));
                        sentence = "";
                    }
                } else {
                    sentence = sentence.concat(line);
                }
            }

            if (sentence.trim().length() > 0) {
                myArrList.add(new MyComponent(sentence.trim()));
            }

            file.close();

            return this;

        }

        public ComponentI build() {
            return myArrListObj;
        }
    }

    @Override
    public void accept(VisitorI visitor) {
        IteratorI iterator = this.getIterator();
        while (iterator.hasNext()) {
            ComponentI component = (ComponentI) iterator.next();
            component.accept(visitor);
        }

    }

    @Override
    public IteratorI getIterator() {
        // TODO Auto-generated method stub
        return new ComponentIterator(myArrList);
    }

}
