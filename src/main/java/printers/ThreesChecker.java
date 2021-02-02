package printers;

import creators.threes.ThreesCreatorFromDrawsHistory;
import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;

public class ThreesChecker {
    public void check(ArrayList<OneDraw> lotteryNumbers, Integer first, Integer second, Integer third){
        Set<CombinationNumbers> integerArrayListTreeMap = new ThreesCreatorFromDrawsHistory().get(lotteryNumbers);
        CombinationNumbers combinationNumbers = new CombinationNumbers(first, second, third);
        if (integerArrayListTreeMap.contains(combinationNumbers)) {
            for (CombinationNumbers com : integerArrayListTreeMap) {
                if (com.equals(combinationNumbers)) {
                    System.out.println(com + " existed " + com.getIndexesWhereAppeared().size());
                }
            }
        }else {
            System.out.println(combinationNumbers + " didn't exist.");
        }
    }
}
