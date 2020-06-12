package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.ObjectForFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComboKeyGenerator implements Runnable {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LotteryNumbersForAlgorithm");
    private final TreeMap<Integer, ArrayList<CombinationNumbers>> combinationNumbers = FileService.loadObject("CombinationNumbers");
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey = new ArrayList<>();

    public ComboKeyGenerator() throws IOException, ClassNotFoundException {
    }


    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        System.out.println("Watek" + id);
        int counter = 0;
        int index = 0;
        if (id == 12) {
            counter = 100;
            index = 1;
        } else if (id == 13) {
            counter = 200;
            index = 101;
        } else if (id == 14) {
            counter = 300;
            index = 201;
        } else if (id == 15) {
            counter = lotteryNumbers.size()-4;
            index = 301;
        }
        for (int i = index; i <= counter; i++) {
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
        FileService.saveObject(afterMultiCombinationKey, "AfterMultiCombinationNumbers");
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

