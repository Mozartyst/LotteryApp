package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.*;

public class GapChecker {

    public void print(MultiCombinationNumber multi, ArrayList<OneDraw> lotteryNumbers) {
        Set<Integer> indexes = new HashSet<>();
        Set<CombinationNumbers> combinationNumbers = new ThreesCreator(lotteryNumbers).get();
        ArrayList<CombinationNumbers> com = new ArrayList<>(combinationNumbers);
        CombinationNumbers firstKey = multi.getFirstKey();
        CombinationNumbers secondKey = multi.getSecondKey();
        CombinationNumbers thirdKey = multi.getThirdKey();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                for (int k = 0; k <= 2; k++) {
                    CombinationNumbers combinationNumbers1 = new CombinationNumbers(firstKey.getNumbers()[i]
                            , secondKey.getNumbers()[j]
                            , thirdKey.getNumbers()[k]);
                    if (combinationNumbers.contains(combinationNumbers1)) {
                        indexes.addAll(com.get(com.indexOf(combinationNumbers1)).getIndexesWhereAppeared());
                    }
                }
            }
        }


        List<Integer> list = new ArrayList<>(indexes);
        Collections.sort(list);

        System.out.println("Result for: " + multi);
        System.out.println("_________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            int diff = 0;
            if (i > 0) {
                diff = list.get(i) - list.get(i - 1) - 1;
            }
            System.out.println(String.format("%02d", i + 1)
                    + " | " + String.format("%03d", list.get(i))
                    + " | " + String.format("%03d", diff)
                    + " | " + lotteryNumbers.get(list.get(i)).getDrawNumbers()
                    + " | " + lotteryNumbers.get(list.get(i)).getDrawDate());
        }
        System.out.println("-- | " + (lotteryNumbers.size() - 1) + " | " + String.format("%03d", ((lotteryNumbers.size() - 1) - (list.get(list.size() - 1)))) + " | " + "<-- How long is waiting now...");
    }
}
