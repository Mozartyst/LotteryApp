package creators;

import dataSupport.FileService;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class PropositionReducerCreator implements Runnable {
    private final TreeMap<Integer, ArrayList<Double>> results;
    private final ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
    private final TreeMap<Integer, TreeMap<Integer, Integer>> appearedNumbers = FileService.loadObject("QuantityOfAppearedNumbers");
    private final int start;
    private final int end;

    public PropositionReducerCreator(int start, int end, TreeMap<Integer, ArrayList<Double>> results) throws IOException, ClassNotFoundException {
        this.start = start;
        this.end = end;
        this.results = results;
    }

    @Override
    public void run() {
        for (int i = start; i >= end; i--) {
            ArrayList<Integer> tempPropositionList = null;
            try {
                tempPropositionList = new Proposition(i).forMultiCombination();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> weeklyNumbers = lotteryNumbers.get(i - 1);
            for (Integer number : weeklyNumbers) {
                if (tempPropositionList.contains(number)) {
                    Integer appearedValue = appearedNumbers.get(i).get(number);
                    addResult(number, (appearedValue / (double)((i - 1) * 100)));
                } else if (tempPropositionList.contains(number + 1)) {

                } else if (tempPropositionList.contains(number - 1)) {

                } else {

                }
            }
        }
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addResult(Integer number, Double value) {
        if (results.containsKey(number)) {
            results.get(number).add(value);
        } else {
            ArrayList<Double> doubles = new ArrayList<>();
            doubles.add(value);
            results.put(number, doubles);
        }
    }

    private synchronized void save() throws IOException {
        FileService.saveObject(results, "Results");
    }
}
