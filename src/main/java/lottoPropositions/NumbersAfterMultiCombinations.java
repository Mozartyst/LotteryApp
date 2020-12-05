package lottoPropositions;

import creators.CombinationCreator;
import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class NumbersAfterMultiCombinations {
    private final ArrayList<OneDraw> lotteryNumbers;

    public NumbersAfterMultiCombinations(ArrayList<OneDraw> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<Integer, Integer> getProposition(int index, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationNumber> afterMultiCombinationKey = FileService.loadObject(properties.getProperty("afterMulti"));
        TreeMap<Integer, Integer> proposition = new TreeMap<>();
        ArrayList<MultiCombinationNumber> multiCombinationKeys = new ArrayList<>();


        createAfterCombination(multiCombinationKeys
                , new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 2).getDrawNumbers(), index - 2)
                , new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 1).getDrawNumbers(), index - 1)
                , new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index).getDrawNumbers(), index));
        multiCombinationKeys.forEach((combination) -> {
            if (afterMultiCombinationKey.contains(combination)) {
                Map<Integer, Integer> whatNumbers = afterMultiCombinationKey.get(afterMultiCombinationKey.indexOf(combination)).getNumbersAfter();
                whatNumbers.forEach((key, value) -> {
                    if (combination.getComplexNumber().length > 1) {
                        if (proposition.containsKey(key)) {
                            proposition.replace(key, proposition.get(key) + value);
                        } else {
                            proposition.put(key, value);
                        }
                    }
                });
            }
        });

        return proposition;
    }

    private void createAfterCombination(ArrayList<MultiCombinationNumber> multiCombinationKeys
            , Set<CombinationNumbers> firstCombination
            , Set<CombinationNumbers> secondCombination
            , Set<CombinationNumbers> thirdCombination) {


        firstCombination.forEach((firstKey) -> {
            MultiCombinationNumber firstMulti = new MultiCombinationNumber(firstKey.getNumbers());
            multiCombinationKeys.add(firstMulti);

            secondCombination.forEach((secondKey) -> {
                if (firstKey.getNumbers().length == secondKey.getNumbers().length) {
                    MultiCombinationNumber secondMulti = new MultiCombinationNumber(firstKey.getNumbers(), secondKey.getNumbers());
                    multiCombinationKeys.add(secondMulti);
                }

                thirdCombination.forEach((thirdKey) -> {
                    if (firstKey.getNumbers().length == thirdKey.getNumbers().length) {
                        MultiCombinationNumber thirdMulti = new MultiCombinationNumber(firstKey.getNumbers(), secondKey.getNumbers(), thirdKey.getNumbers());
                        multiCombinationKeys.add(thirdMulti);
                    }
                });
            });
        });
    }
}
