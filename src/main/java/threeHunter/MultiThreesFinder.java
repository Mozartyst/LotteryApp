package threeHunter;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import printers.MultiThreesGapChecker;

import java.io.IOException;
import java.util.*;

public class MultiThreesFinder implements Runnable {
    private final CombinationNumbers combinationForFirst;
    private final Set<CombinationNumbers> combinationNumbers;
    private final Set<CombinationNumbers> allCombinationNumbers;
    private final Properties properties;
    private final ArrayList<MultiCombinationNumber> multiCombinationList;

    public MultiThreesFinder(CombinationNumbers combinationForFirst
            , Set<CombinationNumbers> combinationNumbers
            , Set<CombinationNumbers> allCombinationNumbers
            , ArrayList<MultiCombinationNumber> multiCombinationList
            , Properties properties
            , ThreadGroup threadGroup) {
        this.combinationForFirst = combinationForFirst;
        this.combinationNumbers = combinationNumbers;
        this.allCombinationNumbers = allCombinationNumbers;
        this.multiCombinationList = multiCombinationList;
        this.properties = properties;
        Thread t = new Thread(threadGroup, this);
        t.start();
    }

    @Override
    public void run() {
        Integer[] numbers = combinationForFirst.getNumbers();
        for (CombinationNumbers com1 : combinationNumbers) {
            Integer[] numbers1 = com1.getNumbers();
            if (com1.containsNumber(numbers[0])
                    || com1.containsNumber(numbers[1])
                    || com1.containsNumber(numbers[2])) {
                continue;
            }
            for (CombinationNumbers com2 : combinationNumbers) {
                Integer[] numbers2 = com2.getNumbers();
                if (com2.containsNumber(numbers[0])
                        || com2.containsNumber(numbers[1])
                        || com2.containsNumber(numbers[2])
                        || com2.containsNumber(numbers1[0])
                        || com2.containsNumber(numbers1[1])
                        || com2.containsNumber(numbers1[2])) {
                    continue;
                }
                Set<Integer> indexes = new HashSet<>();
                for (Integer number1 : numbers) {
                    for (Integer number2 : numbers1) {
                        for (Integer number3 : numbers2) {
                            List<Integer> list = new ArrayList<>();
                            list.add(number1);
                            list.add(number2);
                            list.add(number3);
                            Collections.sort(list);
                            add(allCombinationNumbers, list.get(0), list.get(1), list.get(2), indexes);
                        }
                    }
                }
                int value = indexes.size();
                if (value > Integer.parseInt(properties.getProperty("value"))) {
                    synchronized (multiCombinationList) {
                        MultiCombinationNumber multiCombinationKeys = new MultiCombinationNumber(
                                new Integer[]{numbers[0], numbers1[0], numbers2[0]}
                                , new Integer[]{numbers[1], numbers1[1], numbers2[1]}
                                , new Integer[]{numbers[2], numbers1[2], numbers2[2]});
                        multiCombinationList.add(multiCombinationKeys);
                        try {
                            new MultiThreesGapChecker().print(multiCombinationKeys, FileService.loadObject(properties.getProperty("lotteryNumbers")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void add(Set<CombinationNumbers> combinationNumbers1
            , Integer first
            , Integer second
            , Integer third
            , Set<Integer> indexes) {

        CombinationNumbers combinationNumbers = new CombinationNumbers(first, second, third);
        if (combinationNumbers1.contains(combinationNumbers)) {
            for (CombinationNumbers com : combinationNumbers1) {
                if (com.equals(combinationNumbers)) {
                    indexes.addAll(com.getIndexesWhereAppeared());
                }
            }
        }
    }
}
