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
            MultiCombinationNumber combinationFirst = new MultiCombinationNumber(first.getNumbers());
            synchronized (multiCombinationNumbers) {
                if (multiCombinationNumbers.contains(combinationFirst)) {
                    multiCombination.get(multiCombination.indexOf(combinationFirst)).addWhatNumbers(lotteryNumbers.get(index + 1).getDrawNumbers());
                    multiCombination.get(multiCombination.indexOf(combinationFirst)).addIndex(index);
                } else {
                    combinationFirst.addWhatNumbers(lotteryNumbers.get(index + 1).getDrawNumbers());
                    combinationFirst.addIndex(index);
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
                            first.getNumbers()
                            , second.getNumbers());
                    synchronized (multiCombinationNumbers) {
                        if (multiCombinationNumbers.contains(combinationSecond)) {
                            multiCombination.get(multiCombination.indexOf(combinationSecond)).addWhatNumbers(lotteryNumbers.get(index + 2).getDrawNumbers());
                            multiCombination.get(multiCombination.indexOf(combinationSecond)).addIndex(index + 1);
                        } else {
                            combinationSecond.addWhatNumbers(lotteryNumbers.get(index + 2).getDrawNumbers());
                            combinationSecond.addIndex(index + 1);
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
                                    first.getNumbers()
                                    , second.getNumbers()
                                    , third.getNumbers());
                            synchronized (multiCombinationNumbers) {
                                if (multiCombinationNumbers.contains(combinationThird)) {
                                    multiCombination.get(multiCombination.indexOf(combinationThird)).addWhatNumbers(lotteryNumbers.get(index + 3).getDrawNumbers());
                                    multiCombination.get(multiCombination.indexOf(combinationThird)).addIndex(index + 2);
                                } else {
                                    combinationThird.addWhatNumbers(lotteryNumbers.get(index + 3).getDrawNumbers());
                                    combinationThird.addIndex(index + 2);
                                    multiCombinationNumbers.add(combinationThird);
                                    multiCombination.add(combinationThird);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println((index / (lotteryNumbers.size() - 1))*100 + "%");
    }
}
