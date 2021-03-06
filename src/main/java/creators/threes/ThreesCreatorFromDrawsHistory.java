package creators.threes;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.*;

public class ThreesCreatorFromDrawsHistory {

    public Set<CombinationNumbers> get(ArrayList<OneDraw> lotteryNumbers) {
        Set<CombinationNumbers> combinationNumbersSet = new TreeSet<>();
        for (OneDraw week : lotteryNumbers) {
            for (Integer number1 : week.getDrawNumbers()) {
                for (Integer number2 : week.getDrawNumbers()) {
                    if (number1 >= number2) {
                        continue;
                    }
                    for (Integer number3 : week.getDrawNumbers()) {
                        if (number2 >= number3) {
                            continue;
                        }
                        CombinationNumbers com = new CombinationNumbers(number1, number2, number3);
                        if (combinationNumbersSet.contains(com)) {
                            for (CombinationNumbers com1 : combinationNumbersSet) {
                                if (com1.equals(com)) {
                                    com1.addIndexToList(lotteryNumbers.indexOf(week));
                                }
                            }
                        } else {
                            com.addIndexToList(lotteryNumbers.indexOf(week));
                            combinationNumbersSet.add(com);
                        }
                    }
                }
            }
        }
        return combinationNumbersSet;
    }
}
