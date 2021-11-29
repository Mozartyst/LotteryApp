package creators.weightSystem;

import entity.Number;
import entity.OneDraw;
import lombok.SneakyThrows;

import java.util.*;

public class WeightCreator implements Runnable {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Map<Integer, Number> listOfNumbers;
    private final int index;

    public WeightCreator(ArrayList<OneDraw> lotteryNumbers
            , Map<Integer, Number> listOfNumbers
            , int index) {
        this.lotteryNumbers = lotteryNumbers;
        this.listOfNumbers = listOfNumbers;
        this.index = index;
    }

    @SneakyThrows
    public void run() {
        ArrayList<Integer> nextDrawNumbers = lotteryNumbers.get(index + 1).getDrawNumbers();
        ArrayList<Integer> currentDrawNumbers = lotteryNumbers.get(index).getDrawNumbers();
        for (Integer drawNumber :
                currentDrawNumbers) {
            Number number = listOfNumbers.get(drawNumber);
            Map<Integer, Integer> occurredWith = number.getOccurredWith();
            for (Integer currentNumber :
                    nextDrawNumbers) {
                if (occurredWith.containsKey(currentNumber)) {
                    Integer integer = occurredWith.get(currentNumber);

                }
            }
        }
    }
}
//            int currentGap = number.getCurrentGap(index + 1);
//            Map<Integer, Integer> occurredGaps = number.getOccurredGaps();
