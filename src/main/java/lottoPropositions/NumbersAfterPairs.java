package lottoPropositions;


import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAfterPairs {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterPairs; //IrishLottery/NumbersAfterPairs

    public NumbersAfterPairs(ArrayList<OneDraw> lotteryNumbers, TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterPairs) {
        this.lotteryNumbers = lotteryNumbers;
        this.listOfNumbersAfterPairs = listOfNumbersAfterPairs;
    }

    public TreeMap<Integer, Integer> getPropositionNumbersAfterPairs(int index) {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersForWeek = new TreeMap<>();
        TreeMap<Integer, Integer> propositionNumbers = new TreeMap<>();

        for (Integer firstNumber : lotteryNumbers.get(index).getDrawNumbers()) {
            for (Integer secondNumber : lotteryNumbers.get(index).getDrawNumbers()) {
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

