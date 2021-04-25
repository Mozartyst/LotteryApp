package lottoPropositions;

import entity.Number;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NumbersAppearedWith {
    public Set<Integer> get(Map<Integer, Number> listOfNumbers, Set<Integer> numbersFromGaps) {
        Set<Integer> numbers = new TreeSet<>();
        for (Integer num : numbersFromGaps) {
            numbers.addAll(listOfNumbers.get(num).getOneHighestNumberOccurredWith());
        }
        return numbers;
    }
}
