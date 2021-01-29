package threeHunter;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class ThreesLastDrawsFinder {
    public Set<CombinationNumbers> get(ArrayList<OneDraw> lotteryNumbers, int range, int index) {
        Set<CombinationNumbers> combinationNumbersSet = new TreeSet<>();
        Set<Integer> numbers = new TreeSet<>();
        for (int i = lotteryNumbers.size() - index - range; i < lotteryNumbers.size(); i++) {
            for (Integer number : lotteryNumbers.get(i).getDrawNumbers()) {
                numbers.add(number);
            }
        }
        for (Integer number1 : numbers) {
            for (Integer number2 : numbers) {
                if (number1 >= number2) {
                    continue;
                }
                for (Integer number3 : numbers) {
                    if (number2 >= number3) {
                        continue;
                    }
                    CombinationNumbers com = new CombinationNumbers(number1, number2, number3);
                    combinationNumbersSet.add(com);
                }
            }
        }
        return combinationNumbersSet;
    }
}
