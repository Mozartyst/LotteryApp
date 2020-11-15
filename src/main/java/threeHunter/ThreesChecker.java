package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.OneDraw;

import java.util.ArrayList;

public class ThreesChecker {

    public synchronized int howManyAppeared(MultiCombinationKeys multiKeys, ArrayList<OneDraw> lotteryNumbers) {
        int counter = 0;
        CombinationNumbers firstKey = multiKeys.getFirstKey();
        CombinationNumbers secondKey = multiKeys.getSecondKey();
        CombinationNumbers thirdKey = multiKeys.getThirdKey();
        for (OneDraw weekNumbers : lotteryNumbers) {
            boolean passed = false;
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    for (int k = 0; k <= 2; k++) {
                        Integer firstNumber = firstKey.getNumbers()[i];
                        Integer secondNumber = secondKey.getNumbers()[j];
                        Integer thirdNumber = thirdKey.getNumbers()[k];
                        if (weekNumbers.getDrawNumbers().contains(firstNumber)
                                && weekNumbers.getDrawNumbers().contains(secondNumber)
                                && weekNumbers.getDrawNumbers().contains(thirdNumber)) {
                            passed = true;
                            break;
                        }
                    }
                    if (passed) {
                        break;
                    }
                }
                if (passed) {
                    break;
                }
            }
            if (passed) {
                counter++;
            }
        }
        return counter;
    }
}
