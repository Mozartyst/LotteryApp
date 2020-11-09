package creators;

import entity.CombinationNumbers;
import entity.MultiCombinationKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class MultiCombinationCreator implements Runnable {
    private final ArrayList<CombinationNumbers> firstWeekNumbers;
    private final ArrayList<CombinationNumbers> secondWeekNumbers;
    private final ArrayList<CombinationNumbers> thirdWeekNumbers;
    private final int index;
    private final Thread t;
    private final TreeMap<Integer, HashSet<MultiCombinationKeys>> multiCombinationMap;

    public MultiCombinationCreator(ArrayList<CombinationNumbers> firstWeekNumbers
            , ArrayList<CombinationNumbers> secondWeekNumbers
            , ArrayList<CombinationNumbers> thirdWeekNumbers
            , int index
            , ThreadGroup threadGroup
            , TreeMap<Integer, HashSet<MultiCombinationKeys>> multiCombinationMap) {
        this.firstWeekNumbers = firstWeekNumbers;
        this.secondWeekNumbers = secondWeekNumbers;
        this.thirdWeekNumbers = thirdWeekNumbers;
        this.index = index;
        this.t = new Thread(threadGroup, this);
        this.multiCombinationMap = multiCombinationMap;
        System.out.println(t.getName());
        t.start();
    }

    @Override
    public void run() {
//FIRST
        synchronized (multiCombinationMap) {
            HashSet<MultiCombinationKeys> multiSet = new HashSet<>();
            for (CombinationNumbers firstCombination : firstWeekNumbers) {
                MultiCombinationKeys keySingle = new MultiCombinationKeys(firstCombination);
                multiSet.add(keySingle);
//SECOND
                for (CombinationNumbers secondCombination : secondWeekNumbers) {
                    if (firstCombination.getNumbers().length == secondCombination.getNumbers().length) {
                        MultiCombinationKeys keyDouble = new MultiCombinationKeys(firstCombination, secondCombination);
                        multiSet.add(keyDouble);
//THIRD
                        for (CombinationNumbers thirdCombination : thirdWeekNumbers) {
                            if (secondCombination.getNumbers().length == thirdCombination.getNumbers().length) {
                                MultiCombinationKeys keyTriple = new MultiCombinationKeys(firstCombination, secondCombination, thirdCombination);
                                multiSet.add(keyTriple);
                            }
                        }
                    }
                }
            }
            multiCombinationMap.put(index,multiSet);
        }
        System.out.println("Finished" + t.getName());
        System.gc();
    }

    public Thread getThread() {
        return t;
    }
}
