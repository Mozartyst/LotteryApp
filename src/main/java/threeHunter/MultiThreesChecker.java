package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.ArrayList;

public class MultiThreesChecker {

    public synchronized int howManyAppeared(MultiCombinationNumber multiKeys, ArrayList<OneDraw> lotteryNumbers) {
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
