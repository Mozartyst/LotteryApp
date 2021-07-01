package printers;

import entity.Number;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Dependency {
    public void print(ArrayList<OneDraw> lotteryNumbers, Map<Integer, Number> listOfNumbers, int index) {
        Set<Integer> results = new TreeSet<>();
        for (Integer number : lotteryNumbers.get(index).getDrawNumbers()) {
            Number number1 = listOfNumbers.get(number);
            Set<Integer> with = number1.getThreeHighestNumbersOccurredWith();
            Set<Integer> extendedWith = new TreeSet<>();
            for (Integer withNum : with) {
                if (withNum.equals(1)) {
                    extendedWith.add(withNum);
                    extendedWith.add(withNum + 1);
                } else if (withNum.equals(47)) {
                    extendedWith.add(withNum - 1);
                    extendedWith.add(withNum);
                } else {
                    extendedWith.add(withNum - 1);
                    extendedWith.add(withNum);
                    extendedWith.add(withNum + 1);
                }
            }
            for (Integer exWithNumb : extendedWith) {
                int currentGap = listOfNumbers.get(exWithNumb).getCurrentGap(index + 1);
                Set<Integer> fourHighestGap = listOfNumbers.get(exWithNumb).getThreeHighestGap();
                if (fourHighestGap.contains(currentGap)) {
                    results.add(exWithNumb);
                }
            }

            number1.getFiveHighestGap();
        }
        System.out.println(results);
        System.out.println(lotteryNumbers.get(index + 1));
    }
}
