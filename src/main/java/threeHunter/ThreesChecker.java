package threeHunter;

import entity.CombinationNumbers;
import entity.MultiCombinationKeys;

import java.util.ArrayList;

public class ThreesChecker {

    public synchronized int howManyAppeared(MultiCombinationKeys multiKeys, ArrayList<ArrayList<Integer>> lotteryNumbers) {
        int counter = 0;
        CombinationNumbers firstKey = multiKeys.getFirstKey();
        CombinationNumbers secondKey = multiKeys.getSecondKey();
        CombinationNumbers thirdKey = multiKeys.getThirdKey();
        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
            boolean passed = false;
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    for (int k = 0; k <= 2; k++) {
                        Integer firstNumber = firstKey.getNumbers()[i];
                        Integer secondNumber = secondKey.getNumbers()[j];
                        Integer thirdNumber = thirdKey.getNumbers()[k];
                        if (weekNumbers.contains(firstNumber)
                                && weekNumbers.contains(secondNumber)
                                && weekNumbers.contains(thirdNumber)) {
                            passed = true;
                        }
                    }
                }
            }
            if (passed) {
                counter++;
            }
        }
        return counter;
    }
}
