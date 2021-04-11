package algorithm;


import creators.CombinationCreator;
import creators.MultiCombinationCreator;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import support.Auxiliary;

import java.util.*;

public class AlgorithmCreator1 implements Runnable {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Set<MultiCombinationNumber> reducedMultiCombination;
    private final Map<Integer, Number> listOfNumbers;
    private final Integer index;
    private final Properties properties;

    public AlgorithmCreator1(ArrayList<OneDraw> lotteryNumbers
            , Set<MultiCombinationNumber> reducedMultiCombination
            , Map<Integer, Number> listOfNumbers
            , Integer index, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.reducedMultiCombination = reducedMultiCombination;
        this.listOfNumbers = listOfNumbers;
        this.index = index;
        this.properties = properties;
    }

    @Override
    public void run() {
        if (index < lotteryNumbers.size()) {
            System.out.println(lotteryNumbers.get(index));
        }
        Set<MultiCombinationNumber> multiCombinationSet = new HashSet<>();
        createMultiSet(multiCombinationSet);
        Map<Integer, Integer> afterNumbers = new HashMap<>();
        getAfterMulti(multiCombinationSet, afterNumbers);
        Set<Integer> numbersFromGaps = getNumbersFromGaps();
        Set<Integer> after = new TreeSet<>(Auxiliary.returnThreeHighestKey(afterNumbers));
        getNumbersFromWith(after);
        System.out.println("AfterNumbers " + Auxiliary.returnFourHighestKey(afterNumbers));
    }

    private void createMultiSet(Set<MultiCombinationNumber> multiCombinationSet) {
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
            allNumbers.add(i);
        }
        Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes = new TreeMap<>();
        Set<CombinationNumbers> combinationsNumbers =
                new CombinationCreator().getCombinationNumbers(allNumbers, index - 1);
        combinationMapByIndexes.put(index - 1, combinationsNumbers);
        Set<CombinationNumbers> combinationsNumbers1 =
                new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 2).getDrawNumbers(), index - 2);
        combinationMapByIndexes.put(index - 2, combinationsNumbers1);
        Set<CombinationNumbers> combinationsNumbers2 =
                new CombinationCreator().getCombinationNumbers(lotteryNumbers.get(index - 3).getDrawNumbers(), index - 3);
        combinationMapByIndexes.put(index - 3, combinationsNumbers2);
        new MultiCombinationCreator(multiCombinationSet, combinationMapByIndexes, index - 1).run();
    }

    private Set<Integer> getNumbersFromGaps() {
        Set<Integer> numbers = new TreeSet<>();
        for (int i = index - 1; i > index - 8; i--) {
            for (Integer num : lotteryNumbers.get(i).getDrawNumbers()) {
                int currentGap = (index) - i;
                for (Integer gap : listOfNumbers.get(num).getFourHighestGap()) {
                    if (currentGap == gap) {
                        numbers.add(num);
                    }
                }
            }
        }
        System.out.println("Gaps " + numbers);
        System.out.println(numbers.size());
        return numbers;
    }

    private void getNumbersFromWith(Set<Integer> numbersFromGaps) {
        Set<Integer> numbers = new TreeSet<>();
        for (Integer num : numbersFromGaps) {
            for (Integer numb : listOfNumbers.get(num).getTwoHighestNumbersOccurredWith()) {
                numbers.add(numb);
            }
        }
        System.out.println("Numbers With: " + numbers);
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

    private void getAfterMulti(Set<MultiCombinationNumber> multiCombinationSet, Map<Integer, Integer> afterNumbers) {
        for (MultiCombinationNumber m : reducedMultiCombination) {
            if (multiCombinationSet.contains(m)) {
                if (m.getComplexNumber().length == 2) {
                    if (m.getSecondKey().getNumbers().length == 1) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getSecondKey().getFirstNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getSecondKey().getNumbers().length == 2) {
                        Auxiliary.addAfterNumber(afterNumbers,m.getSecondKey().getSecondNumber(),m.getIndexesWhereAppeared().size());
                    } else if (m.getSecondKey().getNumbers().length == 3) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getSecondKey().getThirdNumber(), m.getIndexesWhereAppeared().size());
                    }
                } else if (m.getComplexNumber().length == 3) {
                    if (m.getThirdKey().getNumbers().length == 1) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getThirdKey().getFirstNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getThirdKey().getNumbers().length == 2) {
                        Auxiliary.addAfterNumber(afterNumbers, m.getThirdKey().getSecondNumber(), m.getIndexesWhereAppeared().size());
                    } else if (m.getThirdKey().getNumbers().length == 3) {
                        Auxiliary.addAfterNumber(afterNumbers,m.getThirdKey().getThirdNumber(),m.getIndexesWhereAppeared().size());
                    }
                }
            }
        }
    }
}
