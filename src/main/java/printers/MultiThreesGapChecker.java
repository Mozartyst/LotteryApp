package printers;

import creators.multiThrees.MultiThreesAppeared;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.util.*;

public class MultiThreesGapChecker {

    public void print(MultiCombinationNumber multi, ArrayList<OneDraw> lotteryNumbers) {
        List<Integer> list = new MultiThreesAppeared(multi, lotteryNumbers).getAppearedList();

        System.out.println("Result for: " + multi);
        System.out.println("_________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            int diff = 0;
            if (i > 0) {
                diff = list.get(i) - list.get(i - 1) - 1;
            }
            System.out.println(String.format("%02d", i + 1)
                    + " | " + String.format("%03d", list.get(i))
                    + " | " + String.format("%03d", diff)
                    + " | " + lotteryNumbers.get(list.get(i)).getDrawNumbers()
                    + " | " + lotteryNumbers.get(list.get(i)).getDrawDate());
        }
        System.out.println("-- | " + (lotteryNumbers.size() - 1) + " | " + String.format("%03d", ((lotteryNumbers.size() - 1) - (list.get(list.size() - 1)))) + " | " + "<-- How long is waiting now...");
    }
}
