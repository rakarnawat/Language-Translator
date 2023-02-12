package words.visitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import words.result.ResultI;
import words.util.ComponentI;
import words.util.IteratorI;

public class KmostAnalysis implements VisitorI {

    private int k;
    private ResultI topKFreqResult;

    private Map<String, Integer> wordFreqMap = new HashMap<String, Integer>();

    private TreeMap<String, Integer> sortedWordFreqMap;

    ArrayList<String> tempList = new ArrayList<>();

    private Map<Integer, String> temp;

    private String word;

    private int counter = 0;

    private ArrayList<String> topKWordsList = new ArrayList<String>();

    Map<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();

    public KmostAnalysis(int k, ResultI topKFreqResult) {
        this.k = k;
        this.topKFreqResult = topKFreqResult;
    }

    @Override
    public void visit(ComponentI myComponent) {
        temp = sortWordsByFreq(myComponent.getIterator());
        // getTopKWords(temp);
        // clearContents();
    }

    private void getTopKWords(Map<Integer, String> temp2temp) {
        int count = 0;

        for (Entry<Integer, String> frequency : temp2temp.entrySet()) {
            word = frequency.getValue();
            count++;
            if (count > k) {
                break;
            }
            topKWordsList.add(word);
        }

        if (topKWordsList.size() >= k) {
            // List<String> subList = topKWordsList.subList(topKWordsList.size() - k,
            // topKWordsList.size());
            topKWordsList.retainAll(topKWordsList.subList(topKWordsList.size() - k, topKWordsList.size()));
        } else {
            topKWordsList.clear();
        }
        topKFreqResult.storeNewResult(topKWordsList.toString().concat("\n"));
    }

    public Map<Integer, String> sortWordsByFreq(IteratorI iterator) {
        while (iterator.hasNext()) {
            String word = ((String) iterator.next()).trim().toLowerCase();
            if (wordFreqMap.containsKey(word)) {
                wordFreqMap.put(word, wordFreqMap.get(word) + 1);
            } else {
                wordFreqMap.put(word, 1);
            }

        }
        Map<Integer, String> sortedWordFreqMap = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
            sortedWordFreqMap.put(entry.getValue(), entry.getKey());
        }

        topKFreqResult.storeNewResult(sortedWordFreqMap.toString().concat("\n"));
        return sortedWordFreqMap;

    }

    private void clearContents() {
        wordFreqMap.clear();
        // if (sortedWordFreqMap != null) {
        // sortedWordFreqMap.clear();
        // }
        topKWordsList.clear();
    }

}
