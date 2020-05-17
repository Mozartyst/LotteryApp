import dataSupport.FileService;
import entity.MultiCombinationKeys;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.util.ArrayList;
import java.util.TreeMap;

public class TesterMain {

    public static void main(String[] args) {
        Integer index = 12;
        ArrayList<ArrayList<Integer>> lotteryNumbersFile1 = FileService.loadFile("LotteryNumbersFile");
        TreeMap<Integer, TreeMap<Integer, Boolean>> algorithm = (TreeMap<Integer, TreeMap<Integer, Boolean>>) FileService.loadObject("AlgorithmFile").getObject();
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbersFile1).getProposition(index);
        ArrayList<Integer> proposition = new ArrayList<>();
        ArrayList<Integer> integerArrayList = lotteryNumbersFile1.get(index);

        multiProposition.forEach((number, value) -> {
            if (algorithm.containsKey(number)) {
                algorithm.get(number).forEach((x,y)->{
                    if (integerArrayList.contains(x)){
                        proposition.add(number);
                    }
                });
            }
        });

        System.out.println(proposition);
        if (index != 0) {
            System.out.println(lotteryNumbersFile1.get(index-1));
        }
    }
}

