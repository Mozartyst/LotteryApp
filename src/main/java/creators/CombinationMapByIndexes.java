package creators;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.*;

public class CombinationMapByIndexes {
    private final Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes = new TreeMap<>();

    public Map<Integer, Set<CombinationNumbers>> create(ArrayList<OneDraw> lotteryNumbers) {
        for (OneDraw weekNumbers : lotteryNumbers) {
            Set<CombinationNumbers> combinationsNumbers = new CombinationCreator().getCombinationNumbers(weekNumbers.getDrawNumbers(), lotteryNumbers.indexOf(weekNumbers));
            combinationMapByIndexes.put(lotteryNumbers.indexOf(weekNumbers), combinationsNumbers);
        }
        return combinationMapByIndexes;
    }
}
