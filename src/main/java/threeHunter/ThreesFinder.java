package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;

import java.util.*;

public class ThreesFinder implements Runnable {
    private final Set<CombinationNumbers> combinationNumbers;
    private final Set<CombinationNumbers> combinationForFirst;
    private final ArrayList<MultiCombinationNumber> multiCombinationList;
    private final Properties properties;

    public ThreesFinder(Set<CombinationNumbers> combinationNumbers
            , Set<CombinationNumbers> combinationForFirst
            , ArrayList<MultiCombinationNumber> multiCombinationList
            , ThreadGroup threadGroup, Properties properties) {
        this.combinationNumbers = combinationNumbers;
        this.combinationForFirst = combinationForFirst;
        this.multiCombinationList = multiCombinationList;
        this.properties = properties;
        Thread t = new Thread(threadGroup, this);
        t.start();
    }

    @Override
    public void run() {
        for (CombinationNumbers com : combinationForFirst) {
            Integer[] numbers = com.getNumbers();
            for (CombinationNumbers com1 : combinationNumbers) {
                Integer[] numbers1 = com1.getNumbers();
                if (com1.compareTo(com) < 0
                        || com1.containsNumber(numbers[0])
                        || com1.containsNumber(numbers[1])
                        || com1.containsNumber(numbers[2])) {
                    continue;
                }
                for (CombinationNumbers com2 : combinationNumbers) {
                    Integer[] numbers2 = com2.getNumbers();
                    if (com2.compareTo(com1) < 0
                            || com2.containsNumber(numbers[0])
                            || com2.containsNumber(numbers[1])
                            || com2.containsNumber(numbers[2])
                            || com2.containsNumber(numbers1[0])
                            || com2.containsNumber(numbers1[1])
                            || com2.containsNumber(numbers1[2])) {
                        continue;
                    }
                    int value = 0;
                    for (Integer number1 : numbers) {
                        for (Integer number2 : numbers1) {
                            for (Integer number3 : numbers2) {
                                List<Integer> list = new ArrayList<>();
                                list.add(number1);
                                list.add(number2);
                                list.add(number3);
                                Collections.sort(list);
                                value += add(combinationNumbers, list.get(0), list.get(1), list.get(2));
                            }
                        }
                    }
                    if (value > Integer.parseInt(properties.getProperty("value"))) {
                        synchronized (multiCombinationList) {
                            MultiCombinationNumber multiCombinationKeys = new MultiCombinationNumber(
                                    new int[]{numbers[0], numbers1[0], numbers2[0]}
                                    , new int[]{numbers[1], numbers1[1], numbers2[1]}
                                    , new int[]{numbers[2], numbers1[2], numbers2[2]});
                            multiCombinationList.add(multiCombinationKeys);
                            System.out.println(multiCombinationKeys + " " + value);
                        }
                    }
                }
            }
        }
    }

    private int add(Set<CombinationNumbers> combinationNumbers1
            , Integer first
            , Integer second
            , Integer third) {

        CombinationNumbers combinationNumbers = new CombinationNumbers(first, second, third);
        int size = 0;
        if (combinationNumbers1.contains(combinationNumbers)) {
            for (CombinationNumbers com : combinationNumbers1) {
                if (com.equals(combinationNumbers)) {
                    size = com.getIndexesWhereAppeared().size();
                }
            }
        }
        return size;
    }
}
