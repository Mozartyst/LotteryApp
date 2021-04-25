package algorithm;


import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersAfterMulti;
import lottoPropositions.NumbersAppearedWith;
import lottoPropositions.NumbersFromGaps;
import printers.PropositionChecker;

import java.util.*;

public class AlgorithmCreator implements Runnable {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Set<MultiCombinationNumber> reducedMultiCombination;
    private final Map<Integer, Number> listOfNumbers;
    private final Integer index;
    private final Properties properties;
    private final TreeMap<Integer, Set<Integer>> gaps;

    public AlgorithmCreator(ArrayList<OneDraw> lotteryNumbers
            , Set<MultiCombinationNumber> reducedMultiCombination
            , Map<Integer, Number> listOfNumbers
            , Integer index
            , Properties properties
            , TreeMap<Integer, Set<Integer>> gaps) {
        this.lotteryNumbers = lotteryNumbers;
        this.reducedMultiCombination = reducedMultiCombination;
        this.listOfNumbers = listOfNumbers;
        this.index = index;
        this.properties = properties;
        this.gaps = gaps;
    }

    @Override
    public void run() {
        if (index < lotteryNumbers.size() - 1) {
            System.out.println(lotteryNumbers.get(index + 1));
        }
        Set<Integer> numbersFromGaps = new NumbersFromGaps().get(lotteryNumbers, listOfNumbers, index);
        Set<Integer> after = new NumbersAfterMulti().get(lotteryNumbers, reducedMultiCombination, properties, index);
        Set<Integer> numbers = new NumbersAppearedWith().get(listOfNumbers, numbersFromGaps);
        new PropositionChecker().check(lotteryNumbers, numbersFromGaps, index);
        new PropositionChecker().check(lotteryNumbers, after, index);
        new PropositionChecker().check(lotteryNumbers, numbers, index);
        new PropositionChecker().check(lotteryNumbers, unrealized(), index);

    }

    private void getAverageOccurred(Set<Integer> numbersFromGaps, Integer range) {
        Map<Integer, Integer> averages = new TreeMap<>();
        for (int i = 1; i <= range; i++) {
            averages.put(i, (index - 1) / listOfNumbers.get(i).getIndexesWhereAppeared().size());
            int size = listOfNumbers.get(i).getIndexesWhereAppeared().size();
            int average = (index + 1) / listOfNumbers.get(i).getIndexesWhereAppeared().size() * averages.get(i);
            if (average > size) {
                System.out.println(i + " under");
            }
        }
        for (Integer num : numbersFromGaps) {
        }
    }

    private Set<Integer> unrealized() {
        Set<Integer> numbers = new TreeSet<>();
        for (int i = index - 2; i <= index; i++) {
            Set<Integer> integers = gaps.get(i);
            for (Integer number : integers) {
                if (integers.contains(number + 1)) {
                    if (i == index - 2) {
                        if (!lotteryNumbers.get(i + 1).getDrawNumbers().contains(number)
                                && !lotteryNumbers.get(i + 1).getDrawNumbers().contains(number + 1)
                                && !lotteryNumbers.get(i + 2).getDrawNumbers().contains(number)
                                && !lotteryNumbers.get(i + 2).getDrawNumbers().contains(number + 1)) {
                            numbers.add(number);
                            numbers.add(number + 1);
                        }
                    } else if (i == index - 1) {
                        if (!lotteryNumbers.get(i + 1).getDrawNumbers().contains(number)
                                && !lotteryNumbers.get(i + 1).getDrawNumbers().contains(number + 1)) {
                            numbers.add(number);
                            numbers.add(number + 1);
                        }
                    } else if (i == index){
                        numbers.add(number);
                        numbers.add(number + 1);
                    }
                }
            }
        }
        return numbers;
    }
}
