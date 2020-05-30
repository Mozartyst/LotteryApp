package lottoPropositions;


import dataSupport.FileService;
import support.Auxiliary;
import entity.CombinationNumbers;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAfterTriple {
    private ArrayList<ArrayList<Integer>> lotteryNumbers;
    private TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterTriple = FileService.loadObject("NumbersAfterTriple");

    public NumbersAfterTriple(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }


    public TreeMap<Integer, Integer> getPropositionNumbersAfterTriple(int index) {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersForWeek = new TreeMap<>();
        TreeMap<Integer, Integer> propositionNumbers = new TreeMap<>();

        for (Integer firstNumber : lotteryNumbers.get(index)) {
            for (Integer secondNumber : lotteryNumbers.get(index)) {
                if ((int) firstNumber >= (int) secondNumber)
                    continue;
                for (Integer thirdNumber : lotteryNumbers.get(index)) {
                    if (secondNumber >= thirdNumber)
                        continue;
                    CombinationNumbers combinationNumbers = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                    if (listOfNumbersAfterTriple.get(combinationNumbers) != null)
                        listOfNumbersForWeek.put(combinationNumbers, listOfNumbersAfterTriple.get(combinationNumbers));

                }
            }
        }

        listOfNumbersForWeek.forEach((x, y) -> {
            if (y != null){
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
