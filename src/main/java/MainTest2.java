import dataSupport.FileService;
import entity.CombinationNumbers;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        for (int i = 10; i >=0; i--) {

            Integer index = i;
            ArrayList<ArrayList<Integer>> lotteryNumbersFile1 = FileService.loadObject("FullLotteryNumbersFile");
            TreeMap<Integer, TreeMap<CombinationNumbers, Boolean>> algorithm = FileService.loadObject("AlgorithmFile1");
            TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbersFile1).getProposition(index);
            ArrayList<Integer> proposition = new ArrayList<>();
            ArrayList<Integer> integerArrayList = lotteryNumbersFile1.get(index);

            multiProposition.forEach((number, value) -> {
                if (algorithm.containsKey(number)) {
                    algorithm.get(number).forEach((x, y) -> {
                        if (integerArrayList.contains(x.getFirstNumber())) {
                            proposition.add(number);
                        }
                    });
                }
            });

            System.out.println(proposition);
            if (index != 0) {
                System.out.println(lotteryNumbersFile1.get(index - 1));
            }
        }
    }
}
