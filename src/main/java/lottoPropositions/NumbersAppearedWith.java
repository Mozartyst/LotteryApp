package lottoPropositions;

import entity.Number;

import java.util.*;

public class NumbersAppearedWith {
    public Set<Integer> get(Map<Integer, Number> listOfNumbers, Set<Integer> setOfNumbers) {
        Set<Integer> numbers = new TreeSet<>();
        for (Integer num : setOfNumbers) {
                    numbers.addAll(listOfNumbers.get(num).getTwoHighestNumbersOccurredWith());
        }
        return numbers;
    }
}
