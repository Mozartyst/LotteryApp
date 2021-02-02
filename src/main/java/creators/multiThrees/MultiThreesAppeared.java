package creators.multiThrees;

import creators.threes.ThreesCreatorFromDrawsHistory;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.*;

public class MultiThreesAppeared {
    private final MultiCombinationNumber multi;
    private final ArrayList<OneDraw> lotteryNumbers;

    public MultiThreesAppeared(MultiCombinationNumber multi, ArrayList<OneDraw> lotteryNumbers) {
        this.multi = multi;
        this.lotteryNumbers = lotteryNumbers;
    }

    public List<Integer> getAppearedList() {
        List<Integer> list = new ArrayList<>(getIndexes());
        Collections.sort(list);
        return list;
    }

    public MultiCombinationNumber addAppearedToMulti() {
        multi.setIndexes(getIndexes());
        return multi;
    }

    private Set<Integer> getIndexes() {
        Set<Integer> indexes = new TreeSet<>();
        Set<CombinationNumbers> combinationNumbers = new ThreesCreatorFromDrawsHistory().get(lotteryNumbers);
        ArrayList<CombinationNumbers> com = new ArrayList<>(combinationNumbers);
        CombinationNumbers firstKey = multi.getFirstKey();
        CombinationNumbers secondKey = multi.getSecondKey();
        CombinationNumbers thirdKey = multi.getThirdKey();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    CombinationNumbers combinationNumbers1 = new CombinationNumbers(firstKey.getNumbers()[i]
                            , secondKey.getNumbers()[j]
                            , thirdKey.getNumbers()[k]);
                    if (combinationNumbers.contains(combinationNumbers1)) {
                        indexes.addAll(com.get(com.indexOf(combinationNumbers1)).getIndexesWhereAppeared());
                    }
                }
            }
        }
        return indexes;
    }
}
