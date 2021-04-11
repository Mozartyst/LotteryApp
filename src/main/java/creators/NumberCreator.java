package creators;

import entity.*;
import entity.Number;

import java.util.*;

public class NumberCreator {
    private final Map<Integer, Number> listOfNumbers;
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Integer index;

    public NumberCreator(Map<Integer, Number> listOfNumbers
            , ArrayList<OneDraw> lotteryNumbers
            , Integer index) {
        this.listOfNumbers = listOfNumbers;
        this.lotteryNumbers = lotteryNumbers;
        this.index = index;
    }

    public void run() {
        ArrayList<Integer> drawNumbersBefore = lotteryNumbers.get(index - 1).getDrawNumbers();
        ArrayList<Integer> drawNumbersCurrent = lotteryNumbers.get(index).getDrawNumbers();
        for (Integer num : drawNumbersCurrent) {
            Number number;
            if (listOfNumbers.containsKey(num)) {
                number = listOfNumbers.get(num);
                number.addIndex(index);
                addOccureedWith(drawNumbersBefore, drawNumbersCurrent, number);
            } else {
                number = new Number(num);
                number.addIndex(index);
                addOccureedWith(drawNumbersBefore, drawNumbersCurrent, number);
                listOfNumbers.put(num, number);
            }

        }
    }

    private void addOccureedWith(ArrayList<Integer> drawNumbersBefore
            , ArrayList<Integer> drawNumbersCurrent
            , Number number) {

        for (Integer numberBefore : drawNumbersBefore) {
            if (!numberBefore.equals(number.getValue())) {
                if (number.getOccurredWith().containsKey(numberBefore)) {
                    number.getOccurredWith().replace(numberBefore, number.getOccurredWith().get(numberBefore) + 1);
                } else {
                    number.getOccurredWith().put(numberBefore, 1);
                }
            }
        }
        for (Integer numberCurrent : drawNumbersCurrent) {
            if (!numberCurrent.equals(number.getValue())) {
                if (number.getOccurredWith().containsKey(numberCurrent)) {
                    number.getOccurredWith().replace(numberCurrent, number.getOccurredWith().get(numberCurrent) + 1);
                } else {
                    number.getOccurredWith().put(numberCurrent, 1);
                }
            }
        }
    }
}
