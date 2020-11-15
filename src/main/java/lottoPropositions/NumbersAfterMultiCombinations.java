package lottoPropositions;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NumbersAfterMultiCombinations {
    private final ArrayList<OneDraw> lotteryNumbers;

    public NumbersAfterMultiCombinations(ArrayList<OneDraw> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<Integer, Integer> getProposition(int index, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject(properties.getProperty("afterMulti"));
        TreeMap<Integer, Integer> proposition = new TreeMap<>();
        ArrayList<MultiCombinationKeys> multiCombinationKeys = new ArrayList<>();


        createAfterCombination(multiCombinationKeys
                , returnCombinationNumbers(lotteryNumbers.get(lotteryNumbers.size()-index-3).getDrawNumbers())
                , returnCombinationNumbers(lotteryNumbers.get(lotteryNumbers.size()-index-2).getDrawNumbers())
                , returnCombinationNumbers(lotteryNumbers.get(lotteryNumbers.size()-index-1).getDrawNumbers()));
        multiCombinationKeys.forEach((combination) -> {
            if (afterMultiCombinationKey.contains(combination)) {
                TreeMap<Integer, Integer> whatNumbers = afterMultiCombinationKey.get(afterMultiCombinationKey.indexOf(combination)).getWhatNumbers();
                whatNumbers.forEach((key, value) -> {
                    if (combination.getKeys().length > 1) {
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

    private ArrayList<CombinationNumbers> returnCombinationNumbers(ArrayList<Integer> gamesNumbers) {
        ArrayList<CombinationNumbers> combinationList = new ArrayList<>();
        //FIRST
        for (Integer firstNumber : gamesNumbers) {
            CombinationNumbers keySingle = new CombinationNumbers(firstNumber);
            combinationList.add(keySingle);
            //SECOND
            for (Integer secondNumber : gamesNumbers) {
                if (secondNumber <= firstNumber) {
                    continue;
                }
                CombinationNumbers keyDouble = new CombinationNumbers(firstNumber, secondNumber);
                combinationList.add(keyDouble);
//THIRD
                for (Integer thirdNumber : gamesNumbers) {
                    if (thirdNumber <= secondNumber) {
                        continue;
                    }
                    CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                    combinationList.add(keyTriple);
//FOURTH
                    for (Integer fourthNumber : gamesNumbers) {
                        if (fourthNumber <= thirdNumber) {
                            continue;
                        }
                        CombinationNumbers keyQuadruple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber, fourthNumber);
                        combinationList.add(keyQuadruple);
                    }
                }
            }
        }
        return combinationList;
    }


    private void createAfterCombination(ArrayList<MultiCombinationKeys> multiCombinationKeys
            , ArrayList<CombinationNumbers> firstCombination
            , ArrayList<CombinationNumbers> secondCombination
            , ArrayList<CombinationNumbers> thirdCombination) {


        firstCombination.forEach((firstKey) -> {
            MultiCombinationKeys firstMulti = new MultiCombinationKeys(firstKey);
            multiCombinationKeys.add(firstMulti);

            secondCombination.forEach((secondKey) -> {
                if (firstKey.getNumbers().length == secondKey.getNumbers().length) {
                    MultiCombinationKeys secondMulti = new MultiCombinationKeys(firstKey, secondKey);
                    multiCombinationKeys.add(secondMulti);
                }

                thirdCombination.forEach((thirdKey) -> {
                    if (firstKey.getNumbers().length == thirdKey.getNumbers().length) {
                        MultiCombinationKeys thirdMulti = new MultiCombinationKeys(firstKey, secondKey, thirdKey);
                        multiCombinationKeys.add(thirdMulti);
                    }
                });
            });
        });
    }
}
