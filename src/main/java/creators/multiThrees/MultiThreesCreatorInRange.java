package creators.multiThrees;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class MultiThreesCreatorInRange {
    private final TreeMap<CombinationNumbers, Set<Integer>> byCombination = new TreeMap<>();
    private final TreeMap<Integer, Set<CombinationNumbers>> byIndexes = new TreeMap<>();

    public void create(Set<CombinationNumbers> combinationNumbers
            , Properties properties
            , Integer rangeFromForDoubleThree
            , Integer rangeToForDoubleThree
            , Integer rangeFromForTripleThree
            , Integer rangeToForTripleThree
            , ArrayList<OneDraw> lotteryNumbers) throws InterruptedException, IOException {


        for (CombinationNumbers com : combinationNumbers) {
            byCombination.put(com, new TreeSet<>(com.getIndexesWhereAppeared()));
            addIndex(com.getIndexesWhereAppeared().size(), com);
        }

        Set<MultiCombinationNumber> mapMulti = new TreeSet<>();
        ThreadGroup forOne = new ThreadGroup("ForOne");
        int counter = 0;
        for (int i = 4; i < byIndexes.size(); i++) {
            if (byIndexes.containsKey(i)) {
                for (CombinationNumbers com : byIndexes.get(i)) {
                    boolean isCom = true;
                    while (isCom) {
                        if (forOne.activeCount() <= Runtime.getRuntime().availableProcessors()) {
                            new NumbersForMultiThreesFinder(new MultiCombinationNumber(
                                    new Integer[]{com.getFirstNumber()}
                                    , new Integer[]{com.getSecondNumber()}
                                    , new Integer[]{com.getThirdNumber()})
                                    , mapMulti
                                    , byCombination
                                    , combinationNumbers
                                    , forOne
                                    , rangeFromForDoubleThree
                                    , rangeToForDoubleThree
                                    , lotteryNumbers);
                            counter++;
                            isCom = false;
                        } else {
                            System.out.println("DoubleNumbersFinders: " + counter);
                            Thread.sleep(150);
                        }
                    }
                }
            }
        }
        while (forOne.activeCount() > 0) {
            counter = 0;
            Thread.sleep(1000);
        }

        Set<MultiCombinationNumber> mapMulti1 = new TreeSet<>();
        ThreadGroup forTwo = new ThreadGroup("ForTwo");
        for (MultiCombinationNumber multi : mapMulti) {
            boolean isCom = true;
            while (isCom) {
                if (forTwo.activeCount() <= Runtime.getRuntime().availableProcessors()) {
                    new NumbersForMultiThreesFinder(
                            multi
                            , mapMulti1
                            , byCombination
                            , combinationNumbers
                            , forTwo
                            , rangeFromForTripleThree
                            , rangeToForTripleThree
                            , lotteryNumbers);
                    counter++;
                    isCom = false;
                } else {
                    System.out.println("TripleNumbersFinder: " + counter);
                    Thread.sleep(1000);
                }
            }
        }
        while (forTwo.activeCount() > 0) {
            Thread.sleep(1000);
        }
        synchronized (mapMulti1) {
            FileService.saveObject(mapMulti1, properties.getProperty("threes"));
        }
    }

    private void addIndex(Integer number, CombinationNumbers combinationNumbers) {
        if (byIndexes.containsKey(number)) {
            byIndexes.get(number).add(combinationNumbers);
        } else {
            Set<CombinationNumbers> combinationNumbers1 = new TreeSet<>();
            combinationNumbers1.add(combinationNumbers);
            byIndexes.put(number, combinationNumbers1);
        }
    }
}