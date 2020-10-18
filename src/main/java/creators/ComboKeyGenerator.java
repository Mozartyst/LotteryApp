package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.OneDraw;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComboKeyGenerator implements Runnable {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey;
    private final int start;
    private final int end;
    private final Properties properties;

    public ComboKeyGenerator(ArrayList<OneDraw> lotteryNumbers
            , ArrayList<MultiCombinationKeys> afterMultiCombinationKey
            , int start
            , int end
            , Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.afterMultiCombinationKey = afterMultiCombinationKey;
        this.start = start;
        this.end = end;
        this.properties = properties;
    }


    @SneakyThrows
    @Override
    public void run() {
        TreeMap<Integer, ArrayList<CombinationNumbers>> combinationNumbers = FileService.loadObject(properties.getProperty("combinationNumbers"));
        for (int i = start; i <= end; i++) {
            int finalIndex = i;
            System.out.println(finalIndex);
//            combinationNumbersArrayList.removeIf(con -> con.getListOfIndexesWhereAppeared().size() == 1);
            TreeSet<CombinationNumbers> combinationNumbersForFirst = new TreeSet<>(combinationNumbers.get(finalIndex));
            TreeSet<CombinationNumbers> combinationNumbersForSecond = new TreeSet<>(combinationNumbers.get(finalIndex + 1));
            TreeSet<CombinationNumbers> combinationNumbersForThird = new TreeSet<>(combinationNumbers.get(finalIndex + 2));
            TreeSet<CombinationNumbers> combinationNumbersForFourth = new TreeSet<>(combinationNumbers.get(finalIndex + 3));

            combinationNumbersForFirst.forEach((firstKey) -> {

                MultiCombinationKeys firstMulti = new MultiCombinationKeys(firstKey);
                addToAfterMulti(firstMulti, finalIndex);

                combinationNumbersForSecond.forEach((secondKey) -> {
                    if (firstKey.getNumbers().length == secondKey.getNumbers().length) {
                        MultiCombinationKeys secondMulti = new MultiCombinationKeys(firstKey, secondKey);
                        addToAfterMulti(secondMulti, finalIndex);
                    }

                    combinationNumbersForThird.forEach((thirdKey) -> {
                        if (firstKey.getNumbers().length == thirdKey.getNumbers().length) {
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


    private void saveMulti() throws IOException {
        synchronized (afterMultiCombinationKey) {
            FileService.saveObject(afterMultiCombinationKey, properties.getProperty("afterMulti"));
        }
    }

    private void addToAfterMulti(MultiCombinationKeys multi, Integer index) {
        if (afterMultiCombinationKey.contains(multi)) {
            multi = afterMultiCombinationKey.get(afterMultiCombinationKey.indexOf(multi));
            for (Integer number : lotteryNumbers.get(index + 1).getDrawNumbers()) {
                multi.setWhatNumbers(number, 1);
            }
        } else {
            for (Integer number : lotteryNumbers.get(index + 1).getDrawNumbers()) {
                multi.setWhatNumbers(number, 1);
            }
            synchronized (afterMultiCombinationKey) {
                afterMultiCombinationKey.add(multi);
            }
        }
    }
}

