package creators;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;

public class MultiCombinationCreator implements Runnable {
    private final Set<MultiCombinationNumber> multiCombinationNumbers;
    private final ArrayList<MultiCombinationNumber> multiCombination;
    private final ArrayList<OneDraw> lotteryNumbers;
    private final int index;
    private final ArrayList<CombinationNumbers> comList;

    public MultiCombinationCreator(Set<MultiCombinationNumber> multiCombinationNumbers
            , ArrayList<MultiCombinationNumber> multiCombination, ArrayList<CombinationNumbers> comList
            , ArrayList<OneDraw> lotteryNumbers
            , int index) {
        this.multiCombinationNumbers = multiCombinationNumbers;
        this.multiCombination = multiCombination;
        this.comList = comList;
        this.lotteryNumbers = lotteryNumbers;
        this.index = index;
    }

    @Override
    public void run() {
        Set<CombinationNumbers> firstCom = new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index).getDrawNumbers(), index);
        Set<CombinationNumbers> secondCom = new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index + 1).getDrawNumbers(), index + 1);
        Set<CombinationNumbers> thirdCom = new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index + 2).getDrawNumbers(), index + 2);
        for (CombinationNumbers first : firstCom) {
            if (comList.get(comList.indexOf(first)).getIndexesWhereAppeared().size() == 1) {
                continue;
            }
            MultiCombinationNumber combinationFirst = new MultiCombinationNumber(
                    makeIntArray(first.getNumbers()));
            if (multiCombinationNumbers.contains(combinationFirst)) {
                multiCombination.get(multiCombination.indexOf(combinationFirst)).addWhatNumbers(lotteryNumbers.get(index + 1).getDrawNumbers(), index + 1);
            } else {
                combinationFirst.addWhatNumbers(lotteryNumbers.get(index + 1).getDrawNumbers(), index);
                synchronized (multiCombination) {
                    multiCombinationNumbers.add(combinationFirst);
                    multiCombination.add(combinationFirst);
                }
            }
            for (CombinationNumbers second : secondCom) {
                if (comList.get(comList.indexOf(second)).getIndexesWhereAppeared().size() == 1) {
                    continue;
                }
                if (first.getNumbers().length == second.getNumbers().length) {
                    MultiCombinationNumber combinationSecond = new MultiCombinationNumber(
                            makeIntArray(first.getNumbers())
                            , makeIntArray(second.getNumbers()));
                    if (multiCombinationNumbers.contains(combinationSecond)) {
                        multiCombination.get(multiCombination.indexOf(combinationSecond)).addWhatNumbers(lotteryNumbers.get(index + 2).getDrawNumbers(), index + 2);
                    } else {
                        combinationSecond.addWhatNumbers(lotteryNumbers.get(index + 2).getDrawNumbers(), index + 1);
                        synchronized (multiCombination) {
                            multiCombinationNumbers.add(combinationSecond);
                            multiCombination.add(combinationSecond);
                        }
                    }
                    for (CombinationNumbers third : thirdCom) {
                        if (comList.get(comList.indexOf(third)).getIndexesWhereAppeared().size() == 1) {
                            continue;
                        }
                        if (second.getNumbers().length == third.getNumbers().length) {
                            MultiCombinationNumber combinationThird = new MultiCombinationNumber(
                                    makeIntArray(first.getNumbers())
                                    , makeIntArray(second.getNumbers())
                                    , makeIntArray(third.getNumbers()));
                            if (multiCombinationNumbers.contains(combinationThird)) {
                                multiCombination.get(multiCombination.indexOf(combinationThird)).addWhatNumbers(lotteryNumbers.get(index + 3).getDrawNumbers(), index + 3);
                            } else {
                                combinationThird.addWhatNumbers(lotteryNumbers.get(index + 3).getDrawNumbers(), index + 2);
                                synchronized (multiCombination) {
                                    multiCombinationNumbers.add(combinationThird);
                                    multiCombination.add(combinationThird);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(index);
    }

    private int[] makeIntArray(Integer[] integersArray) {
        int[] array = new int[integersArray.length];
        int index = 0;
        for (Integer number : integersArray) {
            array[index] = number;
            index++;
        }
        return array;
    }
}
