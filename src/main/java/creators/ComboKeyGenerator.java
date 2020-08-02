package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComboKeyGenerator implements Runnable {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private final TreeMap<Integer, ArrayList<CombinationNumbers>> combinationNumbers;
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey;
    private final int start;
    private final int end;
    private final Properties properties;

    public ComboKeyGenerator(ArrayList<ArrayList<Integer>> lotteryNumbers
            , TreeMap<Integer, ArrayList<CombinationNumbers>> combinationNumbers
            , int start
            , int end
            , ArrayList<MultiCombinationKeys> afterMultiCombinationKey
            , Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.combinationNumbers = combinationNumbers;
        this.start = start;
        this.end = end;
        this.afterMultiCombinationKey = afterMultiCombinationKey;
        this.properties = properties;
    }


    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            int finalIndex = i;
            System.out.println(finalIndex);
            TreeSet<CombinationNumbers> combinationNumbersForFirst = new TreeSet<>();
            TreeSet<CombinationNumbers> combinationNumbersForSecond = new TreeSet<>();
            TreeSet<CombinationNumbers> combinationNumbersForThird = new TreeSet<>();
            TreeSet<CombinationNumbers> combinationNumbersForFourth = new TreeSet<>();
            combinationNumbersForFirst.addAll(combinationNumbers.get(finalIndex));
            combinationNumbersForSecond.addAll(combinationNumbers.get(finalIndex + 1));
            combinationNumbersForThird.addAll(combinationNumbers.get(finalIndex + 2));
            combinationNumbersForFourth.addAll(combinationNumbers.get(finalIndex + 3));

            combinationNumbersForFirst.forEach((firstKey) -> {

                MultiCombinationKeys firstMulti = new MultiCombinationKeys(firstKey);
                addToAfterMulti(firstMulti, finalIndex);
                combinationNumbersForSecond.forEach((secondKey) -> {
                    if (firstKey.getNumber().length == secondKey.getNumber().length) {
                        MultiCombinationKeys secondMulti = new MultiCombinationKeys(firstKey, secondKey);
                        addToAfterMulti(secondMulti, finalIndex);
                    }

                    combinationNumbersForThird.forEach((thirdKey) -> {
                        if (firstKey.getNumber().length == thirdKey.getNumber().length) {
                            MultiCombinationKeys thirdMulti = new MultiCombinationKeys(firstKey, secondKey, thirdKey);
                            addToAfterMulti(thirdMulti, finalIndex);
                        }

//                            combinationNumbersForFourth.forEach((fourthKey) -> {
//                                if (firstKey.getNumber().length == fourthKey.getNumber().length) {
//                                    MultiCombinationKeys fourthMulti = new MultiCombinationKeys(firstKey, secondKey, thirdKey, fourthKey);
//                                    addToAfterMulti(fourthMulti, finalIndex);
//
//                                }
//                            });
                    });
                });
            });
        }
        try {
            saveMulti();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private synchronized void saveMulti() throws IOException {
        FileService.saveObject(afterMultiCombinationKey, properties.getProperty("afterMulti"));
    }

    private synchronized void addToAfterMulti(MultiCombinationKeys multi, Integer index) {
        if (afterMultiCombinationKey.contains(multi)) {
            multi = afterMultiCombinationKey.get(afterMultiCombinationKey.indexOf(multi));
            for (Integer number : lotteryNumbers.get(index - 1)) {
                multi.setWhatNumbers(number, 1);
            }
        } else {
            for (Integer number : lotteryNumbers.get(index - 1)) {
                multi.setWhatNumbers(number, 1);
            }
            afterMultiCombinationKey.add(multi);
        }
    }
}

