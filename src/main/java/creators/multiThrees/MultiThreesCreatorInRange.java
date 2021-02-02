package creators.multiThrees;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import creators.multiThrees.NumbersForMultiThreesFinder;

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
            , Integer rangeToForTripleThree) throws InterruptedException, IOException {


        for (CombinationNumbers com : combinationNumbers) {
            byCombination.put(com, new TreeSet<>(com.getIndexesWhereAppeared()));
            addIndex(com.getIndexesWhereAppeared().size(), com);
        }

        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti = new TreeMap<>();
        ThreadGroup forOne = new ThreadGroup("ForOne");
        int activeThreads = 0;
        int counter = 0;
        for (int i = 2; i < byIndexes.size(); i++) {
            if (byIndexes.containsKey(i)) {
                for (CombinationNumbers com : byIndexes.get(i)) {
                    boolean isCom = true;
                    while (isCom) {
                        if (activeThreads < 50) {
                            new NumbersForMultiThreesFinder(new MultiCombinationNumber(
                                    new Integer[]{com.getFirstNumber()}
                                    , new Integer[]{com.getSecondNumber()}
                                    , new Integer[]{com.getThirdNumber()})
                                    , mapMulti
                                    , byCombination
                                    , combinationNumbers
                                    , forOne
                                    , rangeFromForDoubleThree
                                    , rangeToForDoubleThree);
                            counter++;
                            isCom = false;
                        } else {
                            System.out.println("DoubleNumbersFinders: " + counter);
                            Thread.sleep(1000);
                        }
                        activeThreads = forOne.activeCount();
                    }
                }
            }
        }
        while (forOne.activeCount() > 0) {
            counter = 0;
            Thread.sleep(1000);
        }

        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti1 = new TreeMap<>();
        ThreadGroup forTwo = new ThreadGroup("ForTwo");
        for (Integer index : mapMulti.keySet()) {
            for (MultiCombinationNumber multi : mapMulti.get(index)) {
                boolean isCom = true;
                while (isCom) {
                    if (activeThreads < 50) {
                        new NumbersForMultiThreesFinder(
                                multi
                                , mapMulti1
                                , byCombination
                                , combinationNumbers
                                , forTwo
                                , rangeFromForTripleThree
                                , rangeToForTripleThree);
                        counter++;
                        isCom = false;
                    } else {
                        System.out.println("TripleNumbersFinder: " + counter);
                        Thread.sleep(1000);
                    }
                    activeThreads = forTwo.activeCount();
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