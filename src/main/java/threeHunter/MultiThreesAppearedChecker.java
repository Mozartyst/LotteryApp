package threeHunter;

import creators.ThreesCreatorFromDrawsHistory;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MultiThreesAppearedChecker {

    public int check(MultiCombinationNumber multi, ArrayList<OneDraw> lotteryNumbers) {
        Set<Integer> indexes = new HashSet<>();
        Set<CombinationNumbers> combinationNumbers = new ThreesCreatorFromDrawsHistory(lotteryNumbers).get();
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
        return indexes.size();
    }
}