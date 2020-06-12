package lottoPropositions;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAfterNumber {
    private final TreeMap<Integer, TreeMap<Integer, Integer>> listOfNumbersAfterNumbers = FileService.loadObject("NumbersAfterNumbers");
    private ArrayList<ArrayList<Integer>> lotteryNumbers;

    public NumbersAfterNumber(ArrayList<ArrayList<Integer>> lotteryNumbers) throws IOException, ClassNotFoundException {
        this.lotteryNumbers = lotteryNumbers;
    }


    public TreeMap<Integer, Integer> getPropositionNumbersAfterNumber(int index) {
        TreeMap<Integer, TreeMap<Integer, Integer>> listOfNumbersForWeek = new TreeMap<>();
        TreeMap<Integer, Integer> propositionNumbers = new TreeMap<>();

        for (Integer number : lotteryNumbers.get(index)) {
            listOfNumbersForWeek.put(number, listOfNumbersAfterNumbers.get(number));
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

