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
        for (Integer currentNumber :
                currentDrawNumbers) {
            for (Integer nextNumber :
                    nextDrawNumbers) {


            }
        }
    }
}
