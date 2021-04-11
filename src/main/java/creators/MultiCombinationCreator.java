package creators;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;

import java.util.*;

public class MultiCombinationCreator implements Runnable {
    private final Set<MultiCombinationNumber> multiCombinationSet;
    private final Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes;
    private final int index;

    public MultiCombinationCreator(Set<MultiCombinationNumber> multiCombinationSet
            , Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes
            , int index) {
        this.multiCombinationSet = multiCombinationSet;
        this.combinationMapByIndexes = combinationMapByIndexes;
        this.index = index;
    }

    @Override
    public void run() {
        Set<CombinationNumbers> firstCom = combinationMapByIndexes.get(index);
        Set<CombinationNumbers> secondCom = combinationMapByIndexes.get(index - 1);
        Set<CombinationNumbers> thirdCom = combinationMapByIndexes.get(index - 2);
        for (CombinationNumbers first : firstCom) {
            if (first.getNumbers().length == 4) {
                continue;
            }
            MultiCombinationNumber combinationFirst = new MultiCombinationNumber(first.getNumbers());
            if (first.getNumbers().length != 1) {
                addToMulti(combinationFirst);
            }
            for (CombinationNumbers second : secondCom) {
                if (first.getNumbers().length == second.getNumbers().length) {
                    MultiCombinationNumber combinationSecond = new MultiCombinationNumber(
                            second.getNumbers()
                            , first.getNumbers());
                    addToMulti(combinationSecond);
                    if (second.getNumbers().length > 1) {
                        continue;
                    }
                    for (CombinationNumbers third : thirdCom) {
                        if (second.getNumbers().length == third.getNumbers().length) {
                            MultiCombinationNumber combinationThird = new MultiCombinationNumber(
                                    third.getNumbers()
                                    , second.getNumbers()
                                    , first.getNumbers());
                            addToMulti(combinationThird);
                        }
                    }
                }
            }
        }
    }

    private void addToMulti(MultiCombinationNumber combination) {
        synchronized (multiCombinationSet) {
            if (multiCombinationSet.contains(combination)) {
                for (MultiCombinationNumber m : multiCombinationSet) {
                    if (m.equals(combination)) {
                        m.addIndex(index);
                        break;
                    }
                }
            } else {
                combination.addIndex(index);
                multiCombinationSet.add(combination);
            }
        }
    }
}
