package creators.threes;

import entity.CombinationNumbers;
import entity.OneDraw;
import lottoPropositions.NumbersFromFewLastDraws;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class LastDrawsThreesCreator {
    public Set<CombinationNumbers> get(ArrayList<OneDraw> lotteryNumbers, int range, int index) {
        Set<CombinationNumbers> combinationNumbersSet = new TreeSet<>();
        Set<Integer> numbers = new NumbersFromFewLastDraws().get(lotteryNumbers, range, index);

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
                    for (OneDraw oneDraw : lotteryNumbers) {
                        ArrayList<Integer> drawNumbers = oneDraw.getDrawNumbers();
                        if (drawNumbers.contains(number1) && drawNumbers.contains(number2) && drawNumbers.contains(number3)) {
                            com.addIndexToList(lotteryNumbers.indexOf(oneDraw));
                        }
                    }
                    combinationNumbersSet.add(com);
                }
            }
        }
        return combinationNumbersSet;
    }
}
