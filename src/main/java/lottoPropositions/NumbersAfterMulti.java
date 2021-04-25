package lottoPropositions;

import creators.CombinationCreator;
import creators.MultiCombinationCreator;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import support.Auxiliary;

import java.util.*;

public class NumbersAfterMulti {
    public Set<Integer> get(ArrayList<OneDraw> lotteryNumbers
            , Set<MultiCombinationNumber> reducedMultiCombination
            , Properties properties
            , Integer index) {

        Set<MultiCombinationNumber> multiCombinationSet = new HashSet<>();
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
            allNumbers.add(i);
        }
        Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes = new TreeMap<>();
        Set<CombinationNumbers> combinationsNumbers =
                new CombinationCreator().getCombinationNumbers(allNumbers, index);
        combinationMapByIndexes.put(index, combinationsNumbers);
        Set<CombinationNumbers> combinationsNumbers1 =
                new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 1).getDrawNumbers(), index - 1);
        combinationMapByIndexes.put(index - 1, combinationsNumbers1);
        Set<CombinationNumbers> combinationsNumbers2 =
                new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 2).getDrawNumbers(), index - 2);
        combinationMapByIndexes.put(index - 2, combinationsNumbers2);
        new MultiCombinationCreator(multiCombinationSet, combinationMapByIndexes, index).run();
        Map<Integer, Integer> afterNumbers = new HashMap<>();
        for (MultiCombinationNumber m : reducedMultiCombination) {
            if (multiCombinationSet.contains(m)) {
                if (m.getComplexNumber().length == 2) {
                    if (m.getSecondKey().getNumbers().length == 1) {
//                        Auxiliary.addAfterNumber(afterNumbers, m.getSecondKey().getFirstNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getSecondKey().getNumbers().length == 2) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getSecondKey().getSecondNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getSecondKey().getNumbers().length == 3) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getSecondKey().getThirdNumber(), m.getIndexesWhereAppeared().size());
                    }
                } else if (m.getComplexNumber().length == 3) {
                    if (m.getThirdKey().getNumbers().length == 1) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getThirdKey().getFirstNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getThirdKey().getNumbers().length == 2) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getThirdKey().getSecondNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getThirdKey().getNumbers().length == 3) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getThirdKey().getThirdNumber(), m.getIndexesWhereAppeared().size());
                    }
                }
            }
        }
        return Auxiliary.returnFourHighestKey(afterNumbers);
    }
}
