package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationKeys;

import java.util.*;

public class ThreesFinder implements Runnable {
    private final TreeMap<CombinationNumbers, Integer> allThreesCombinations;
    private final Set<CombinationNumbers> combinationNumbers;
    private final ArrayList<MultiCombinationKeys> multiCombinationList;

    public ThreesFinder(TreeMap<CombinationNumbers, Integer> allThreesCombinations
            , Set<CombinationNumbers> combinationNumbers
            , ArrayList<MultiCombinationKeys> multiCombinationList
            , ThreadGroup threadGroup) {
        this.allThreesCombinations = allThreesCombinations;
        this.combinationNumbers = combinationNumbers;
        this.multiCombinationList = multiCombinationList;
        Thread t = new Thread(threadGroup, this);
        t.start();
    }

    @Override
    public void run() {
        for (CombinationNumbers com : combinationNumbers) {
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
                                value += add(allThreesCombinations, list.get(0), list.get(1), list.get(2));
                            }
                        }
                    }
                    if (value > 47) {
                        synchronized (multiCombinationList) {
                            MultiCombinationKeys multiCombinationKeys = new MultiCombinationKeys(
                                    new CombinationNumbers(numbers[0], numbers1[0], numbers2[0])
                                    , new CombinationNumbers(numbers[1], numbers1[1], numbers2[1])
                                    , new CombinationNumbers(numbers[2], numbers1[2], numbers2[2]));
                            multiCombinationList.add(multiCombinationKeys);
                            System.out.println(multiCombinationKeys + " " + value);
                        }
                    }
                }
            }
        }
    }

    private int add(TreeMap<CombinationNumbers, Integer> integerArrayListTreeMap
            , Integer first
            , Integer second
            , Integer third) {

        Integer integer = integerArrayListTreeMap.get(new CombinationNumbers(first, second, third));
        if (integer == null) {
            return 0;
        } else return integer;
    }
}
