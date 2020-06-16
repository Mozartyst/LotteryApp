package algorithm;

import dataSupport.FileService;
import entity.Number;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Algorithm implements Serializable {

    private final ArrayList<Integer> propositionList = new ArrayList<>();
    private final TreeMap<Integer, ArrayList<Double>> results = new TreeMap<>();


    public ArrayList<Integer> getPropositionList(Integer index) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject("AlgorithmFile");
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
        TreeMap<Integer, TreeMap<Integer, Integer>> test = FileService.loadObject("QuantityOfAppearedNumbers");
        Double counter = 100.0;

        for (int i = lotteryNumbers.size() - 1; i > 0; i--) {
            counter++;
            ArrayList<Integer> tempPropositionList = new Proposition(i).forMultiCombination();
            ArrayList<Integer> weeklyNumbers = lotteryNumbers.get(i - 1);
            for (Integer number : weeklyNumbers) {
                if (tempPropositionList.contains(number)) {
                    Integer appearedValue = test.get(i).get(number);
                    addResult(number, (appearedValue / counter));
                } else if (tempPropositionList.contains(number + 1)) {

                } else if (tempPropositionList.contains(number - 1)) {

                } else {

                }
            }
        }

        return propositionList;
    }

    private void addResult(Integer number, Double value) {
        if (results.containsKey(number)) {
            ArrayList<Double> doubles = results.get(number);
            doubles.add(value);
        } else {
            ArrayList<Double> doubles = new ArrayList<>();
            doubles.add(value);
            results.put(number, doubles);
        }
    }
}
