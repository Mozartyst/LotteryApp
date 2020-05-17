package lottoPropositions;


import dataSupport.FileService;
import entity.CombinationNumbers;
import support.Auxiliary;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAfterPairs {
    private ArrayList<ArrayList<Integer>> lotteryNumbers;
    private TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterPairs =
            (TreeMap<CombinationNumbers, TreeMap<Integer, Integer>>) FileService.loadObject("NumbersAfterPairs").getObject();

    public NumbersAfterPairs(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<Integer, Integer> getPropositionNumbersAfterPairs(int index) {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersForWeek = new TreeMap<>();
        TreeMap<Integer, Integer> propositionNumbers = new TreeMap<>();

        for (Integer firstNumber : lotteryNumbers.get(index)) {
            for (Integer secondNumber : lotteryNumbers.get(index)) {
                if (firstNumber >= secondNumber)
                    continue;
                CombinationNumbers keyPairs = new CombinationNumbers(firstNumber, secondNumber);
                if (listOfNumbersAfterPairs.get(keyPairs) != null)
                listOfNumbersForWeek.put(keyPairs, listOfNumbersAfterPairs.get((keyPairs)));
            }
        }

        listOfNumbersForWeek.forEach((x, y) -> {
            if (y != null) {
                y.forEach((x1, y1) -> {
                    if (!propositionNumbers.containsKey(x1)) {
                        propositionNumbers.put(x1, y1);
                    } else {
                        propositionNumbers.replace(x1, propositionNumbers.get(x1) + y1);
                    }
                });
            }
        });
        return propositionNumbers;
    }
}
