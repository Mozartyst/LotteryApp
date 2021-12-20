package lottoPropositions;

import checker.IsMatchedWithGaps;
import checker.IsMatchedWithNumbers;
import checker.IsThreesMultiCombinationMatched;
import entity.Number;
import entity.OneDraw;

import java.util.*;

public class SearcherOfPatterns {
    private IsMatchedWithGaps isMatchedWithGaps = new IsMatchedWithGaps();
    private IsMatchedWithNumbers isMatchedWithNumbers = new IsMatchedWithNumbers();
    private IsThreesMultiCombinationMatched isThreesMultiCombinationMatched = new IsThreesMultiCombinationMatched();

    public void search(ArrayList<OneDraw> lotteryNumbers, Map<Integer, Number> numbers) {
        Map<Integer, Set<Integer>> occurredTogether = new TreeMap<>();
        for (int i = lotteryNumbers.size() - 1; i > 0; i--) {
            for (Integer number : lotteryNumbers.get(i).getDrawNumbers()) {
                for (int j = lotteryNumbers.get(i).getDrawNumbers().indexOf(number) + 1;
                     j < lotteryNumbers.get(i).getDrawNumbers().size();
                     j++) {
                    Integer secondNumber = lotteryNumbers.get(i).getDrawNumbers().get(j);
                    if (isMatchedWithNumbers.isMatched(numbers.get(number), secondNumber)) {
                        addToMap(occurredTogether, number, secondNumber);
                        addToMap(occurredTogether, secondNumber, number);
                    }
                }
            }
        }
        occurredTogether.forEach((x, y) -> System.out.println(x + " " + y));

        for (int i = lotteryNumbers.size() - 1; i > lotteryNumbers.size() - 10; i--) {
            Set<Integer> propositions = new TreeSet<>();
            for (Integer number : lotteryNumbers.get(i).getDrawNumbers()) {
                if (occurredTogether.containsKey(number)) {
                    for (Integer numberWith : occurredTogether.get(number)) {
                        if (!lotteryNumbers.get(i).getDrawNumbers().contains(numberWith)) {
                            propositions.add(numberWith);
                        }
                    }
                }
            }

            if (i != lotteryNumbers.size() - 1) {
                System.out.print(lotteryNumbers.get(i + 1));
            }
            System.out.println(propositions);
        }
    }

    private void addToMap(Map<Integer, Set<Integer>> occurredTogether, Integer first, Integer second) {
        if (occurredTogether.containsKey(first)) {
            Set<Integer> integers = occurredTogether.get(first);
            integers.add(second);
            occurredTogether.replace(first, integers);
        } else {
            Set<Integer> integers = new TreeSet<>();
            integers.add(second);
            occurredTogether.put(first, integers);
        }
    }
}
