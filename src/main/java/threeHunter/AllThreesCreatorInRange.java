package threeHunter;

import creators.AllThreesCreator;
import creators.ThreesCreatorFromDrawsHistory;
import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class AllThreesCreatorInRange {
    private final TreeMap<CombinationNumbers, Set<Integer>> byCombination = new TreeMap<>();
    private final TreeMap<Integer, TreeMap<CombinationNumbers, Integer>> byNumbers = new TreeMap<>();
    private final TreeMap<Integer, Set<CombinationNumbers>> byIndexes = new TreeMap<>();

    public void get(ArrayList<OneDraw> lotteryNumbers,Properties properties) throws InterruptedException, IOException, ClassNotFoundException {
        Set<CombinationNumbers> combinationNumbers = new ThreesCreatorFromDrawsHistory(lotteryNumbers).get();
        Set<CombinationNumbers> combinationNumbers1 = new AllThreesCreator().get(properties);
        for (CombinationNumbers com : combinationNumbers) {
            byCombination.put(com, new TreeSet<>(com.getIndexesWhereAppeared()));
            addNumber(com.getFirstNumber(), com);
            addNumber(com.getSecondNumber(), com);
            addNumber(com.getThirdNumber(), com);
            addIndex(com.getIndexesWhereAppeared().size(), com);
        }

        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti = new TreeMap<>();
        ThreadGroup forOne = new ThreadGroup("ForOne");
        for (CombinationNumbers com : byIndexes.get(3)) {
            new NumbersToMultiThreesFinder(new MultiCombinationNumber(
                    new Integer[]{com.getFirstNumber()}
                    , new Integer[]{com.getSecondNumber()}
                    , new Integer[]{com.getThirdNumber()})
                    , mapMulti
                    , byCombination
                    , combinationNumbers1
                    , forOne);
        }

        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti1 = new TreeMap<>();
        ThreadGroup forTwo = new ThreadGroup("ForTwo");
        for (MultiCombinationNumber multi : mapMulti.get(mapMulti.lastKey())) {
            new NumbersToMultiThreesFinder(multi, mapMulti1, byCombination, combinationNumbers1, forTwo);
        }
        synchronized (mapMulti1) {
            mapMulti1.forEach((x, y) -> y.forEach((z) -> System.out.println(x + " " + z)));
        }
    }


    private void addNumber(Integer number, CombinationNumbers combinationNumbers) {
        if (byNumbers.containsKey(number)) {
            byNumbers.get(number).put(combinationNumbers, combinationNumbers.getIndexesWhereAppeared().size());
        } else {
            TreeMap<CombinationNumbers, Integer> value = new TreeMap<>();
            value.put(combinationNumbers, combinationNumbers.getIndexesWhereAppeared().size());
            byNumbers.put(number, value);
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