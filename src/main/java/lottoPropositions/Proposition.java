package lottoPropositions;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Proposition {
    Integer index;
    TreeSet<Integer> propositionList = new TreeSet<>();

    public Proposition(Integer index) {
        this.index = index;
    }

    public TreeSet<Integer> forMultiCombination() throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = FileService.loadObject("AlgorithmFile");
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index);

        multiProposition.forEach((number, value) -> {
            if (algorithm.containsKey(number)) {
                algorithm.get(number).forEach((x,y)->{
                    if (lotteryNumbers.get(index).contains(x)){
                        propositionList.add(number);
                    }
                });
            }
        });
        return propositionList;
    }

}

