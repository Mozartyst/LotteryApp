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
        //Mam liczbę która wypadła.
        //Do tej liczby sprawdzam zależności.
        //
        ArrayList<Integer> nextDrawNumbers = lotteryNumbers.get(index + 1).getDrawNumbers();
        ArrayList<Integer> currentDrawNumbers = lotteryNumbers.get(index).getDrawNumbers();
        for (Integer drawNumber :
                nextDrawNumbers) {
            Number number = listOfNumbers.get(drawNumber);
            Map<Integer, Integer> occurredWith = number.getOccurredWith();
            Integer value = occurredWith.get(drawNumber);
            int currentGap = listOfNumbers.get(drawNumber).getCurrentGap(index + 1);
            Map<Integer, Integer> occurredGaps = listOfNumbers.get(drawNumber).getOccurredGaps();
            for (Integer currentNumber:currentDrawNumbers) {
                System.out.println(occurredWith);
                System.out.println(currentNumber);
            }
        }
    }
}
