package lottoPropositions;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class NumbersAfterMultiCombinations {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject("ReducedMulti");

    public NumbersAfterMultiCombinations(ArrayList<ArrayList<Integer>> lotteryNumbers) throws IOException, ClassNotFoundException {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<Integer, Integer> getProposition(int index) {
        TreeMap<Integer, Integer> proposition = new TreeMap<>();
        ArrayList<CombinationNumbers> combinationNumbersArrayList = new ArrayList<>();
        ArrayList<MultiCombinationKeys> multiCombinationKeys = new ArrayList<>();
        for (int i = index; i < index + 4; i++) {

            if (i + 4 < lotteryNumbers.size()) {
                ArrayList<Integer> gamesNumbers = lotteryNumbers.get(i);
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
        }
        createAfterCombination(combinationNumbersArrayList, multiCombinationKeys, index);
        multiCombinationKeys.forEach((combination) -> {
            if (afterMultiCombinationKey.contains(combination)) {
                TreeMap<Integer, Integer> whatNumbers = afterMultiCombinationKey.get(afterMultiCombinationKey.indexOf(combination)).getWhatNumbers();
                whatNumbers.forEach((key, value) -> {
//                    if (combination.getKeys().length > 1 && combination.getKeys().length < 3) {
                        if (proposition.containsKey(key)) {
                            proposition.replace(key, proposition.get(key) + value);
                        } else {
                            proposition.put(key, value);
                        }
//                    }
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
                if (firstKey.getNumber().length == secondKey.getNumber().length) {
                    MultiCombinationKeys secondMulti = new MultiCombinationKeys(firstKey, secondKey);
                    multiCombinationKeys.add(secondMulti);

                }

                combinationNumbersTreeSetForThird.forEach((thirdKey) -> {
                    if (firstKey.getNumber().length == thirdKey.getNumber().length) {
                        MultiCombinationKeys thirdMulti = new MultiCombinationKeys(firstKey, secondKey, thirdKey);
                        multiCombinationKeys.add(thirdMulti);

                    }
                });
            });
        });
    }
}
