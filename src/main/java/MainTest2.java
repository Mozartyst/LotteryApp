import creators.AlgorithmCreator1;
import dataSupport.FileService;
import entity.CombinationNumbers;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.util.ArrayList;
import java.util.TreeMap;

public class MainTest2 {
    public static void main(String[] args) {
        Integer index = 0;
        ArrayList<ArrayList<Integer>> lotteryNumbersFile1 = FileService.loadFile("LotteryNumbersFile");
        TreeMap<Integer, TreeMap<CombinationNumbers, Boolean>> algorithm = (TreeMap<Integer, TreeMap<CombinationNumbers, Boolean>>) FileService.loadObject("AlgorithmFile1").getObject();
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbersFile1).getProposition(index);
        ArrayList<Integer> proposition = new ArrayList<>();
        ArrayList<Integer> integerArrayList = lotteryNumbersFile1.get(index);

        multiProposition.forEach((number, value) -> {
            if (algorithm.containsKey(number)) {
                algorithm.get(number).forEach((x,y)->{
                    if (integerArrayList.contains(x.getFirstNumber())&& integerArrayList.contains(x.getSecondNumber())){
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
