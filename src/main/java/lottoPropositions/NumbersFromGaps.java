package lottoPropositions;

import entity.Number;
import entity.OneDraw;

import java.util.*;

public class NumbersFromGaps {
    public Set<Integer> get(ArrayList<OneDraw> lotteryNumbers, Map<Integer, Number> listOfNumbers, Integer index) {
        Set<Integer> numbers = new TreeSet<>();
        for (int i = index; i > index - 11; i--) {
           for (Integer num : lotteryNumbers.get(i).getDrawNumbers()) {
                int currentGap = (index + 1) - i;
                for (Integer gap : listOfNumbers.get(num).getThreeHighestGap()) {
                    if (currentGap == gap) {
                        numbers.add(num);
                    }
                }
            }
        }
        return numbers;
    }
}
