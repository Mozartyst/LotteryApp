package lottoPropositions;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class NumbersAfterMultiCombinations {
    private final ArrayList<OneDraw> lotteryNumbers;

    public NumbersAfterMultiCombinations(ArrayList<OneDraw> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<Integer, Integer> getProposition(int index, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject(properties.getProperty("reducedMulti"));
        TreeMap<Integer, Integer> proposition = new TreeMap<>();
        ArrayList<CombinationNumbers> combinationNumbersArrayList = new ArrayList<>();
        ArrayList<MultiCombinationKeys> multiCombinationKeys = new ArrayList<>();
        for (int i = lotteryNumbers.size() - 1 - index; i > 4; i--) {

            ArrayList<Integer> gamesNumbers = lotteryNumbers.get(i).getDrawNumbers();
//FIRST
            for (Integer firstNumber : gamesNumbers) {
                CombinationNumbers keySingle = new CombinationNumbers(firstNumber);
                addCombinationNumbers(keySingle, i, combinationNumbersArrayList);
//SECOND
                for (Integer secondNumber : gamesNumbers) {
                    if (secondNumber <= firstNumber) {
                        continue;
                    }
                    CombinationNumbers keyDouble = new CombinationNumbers(firstNumber, secondNumber);
                    addCombinationNumbers(keyDouble, i, combinationNumbersArrayList);
//THIRD
                    for (Integer thirdNumber : gamesNumbers) {
                        if (thirdNumber <= secondNumber) {
                            continue;
                        }
                        CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                        addCombinationNumbers(keyTriple, i, combinationNumbersArrayList);
//FOURTH
                        for (Integer fourthNumber : gamesNumbers) {
                            if (fourthNumber <= thirdNumber) {
                                continue;
                            }
                            CombinationNumbers keyQuadruple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber, fourthNumber);
                            addCombinationNumbers(keyQuadruple, i, combinationNumbersArrayList);
                        }
                    }
                }
            }

        }
        createAfterCombination(combinationNumbersArrayList, multiCombinationKeys, index);
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

    private void addCombinationNumbers(CombinationNumbers combination, int index, ArrayList<CombinationNumbers> combinationNumbersArrayList) {
        if (combinationNumbersArrayList.contains(combination)) {
            int i = combinationNumbersArrayList.indexOf(combination);
            combinationNumbersArrayList.get(i).addIndexToList(index);
        } else {
            combination.addIndexToList(index);
            combinationNumbersArrayList.add(combination);
        }
    }

    private void createAfterCombination(ArrayList<CombinationNumbers> combinationNumbersArrayList, ArrayList<MultiCombinationKeys> multiCombinationKeys, Integer finalIndex) {

        TreeSet<CombinationNumbers> combinationNumbersTreeSetForFirst = new TreeSet<>();
        TreeSet<CombinationNumbers> combinationNumbersTreeSetForSecond = new TreeSet<>();
        TreeSet<CombinationNumbers> combinationNumbersTreeSetForThird = new TreeSet<>();
        combinationNumbersArrayList.forEach((combination) -> {
            if (combination.containsIndex(finalIndex)) {
                combinationNumbersTreeSetForFirst.add(combination);
            } else if (combination.containsIndex(finalIndex + 1)) {
                combinationNumbersTreeSetForSecond.add(combination);
            } else if (combination.containsIndex(finalIndex + 2)) {
                combinationNumbersTreeSetForThird.add(combination);
            }
        });
        combinationNumbersTreeSetForFirst.forEach((firstKey) -> {

            MultiCombinationKeys firstMulti = new MultiCombinationKeys(firstKey);
            multiCombinationKeys.add(firstMulti);

            combinationNumbersTreeSetForSecond.forEach((secondKey) -> {
                if (firstKey.getNumbers().length == secondKey.getNumbers().length) {
                    MultiCombinationKeys secondMulti = new MultiCombinationKeys(firstKey, secondKey);
                    multiCombinationKeys.add(secondMulti);

                }

                combinationNumbersTreeSetForThird.forEach((thirdKey) -> {
                    if (firstKey.getNumbers().length == thirdKey.getNumbers().length) {
                        MultiCombinationKeys thirdMulti = new MultiCombinationKeys(firstKey, secondKey, thirdKey);
                        multiCombinationKeys.add(thirdMulti);

                    }
                });
            });
        });
    }
}
