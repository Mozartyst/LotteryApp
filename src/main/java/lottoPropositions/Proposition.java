package lottoPropositions;

import dataSupport.FileService;

import java.util.ArrayList;
import java.util.TreeMap;

public class Proposition {
    Integer index;
    ArrayList<Integer> propositionList = new ArrayList<>();

    public Proposition(Integer index) {
        this.index = index;
    }

    public ArrayList<Integer> forMultiCombination() {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadFile("LotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = (TreeMap<Integer, TreeMap<Integer, Boolean>>) FileService.loadObject("AlgorithmFile").getObject();
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

