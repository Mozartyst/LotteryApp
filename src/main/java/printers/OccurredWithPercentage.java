package printers;

import entity.Number;
import entity.OneDraw;
import support.Auxiliary;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class OccurredWithPercentage {
    public void print(ArrayList<OneDraw> lotteryNumbers
            , Map<Integer, Number> listOfNumbers
            , Properties properties
            , int index
            , Integer forNumber) {
        Number number = listOfNumbers.get(forNumber);
        ArrayList<Integer> currentDraw = lotteryNumbers.get(index).getDrawNumbers();
        double goodPro = 0.0;
        int currentGap = number.getCurrentGap(index + 1);
        for (Integer num : currentDraw) {
            double percentage = Auxiliary.returnPercentage(number.getOccurredWith(), num);
            goodPro += percentage;
        }
        if (goodPro > 250) {
            if (index < lotteryNumbers.size() - 1) {
                if (lotteryNumbers.get(index + 1).getDrawNumbers().contains(number.getValue())) {
                    System.out.print(number.getValue() + " PASS " + goodPro);
                } else {
                    System.out.print(number.getValue() + " fail " + goodPro);
                }
            } else {
                System.out.print(number.getValue() + " " + goodPro);
            }
            System.out.println();
        }

    }
}
