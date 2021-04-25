package printers;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class PropositionChecker {

    public void check(ArrayList<OneDraw> lotteryNumbers, Set<Integer> numbers, Integer index) {
        int counter = 0;
        Set<Integer> matched = new TreeSet<>();
        for (Integer number : numbers) {
            if (index < lotteryNumbers.size() - 1) {
                if (lotteryNumbers.get(index + 1).getDrawNumbers().contains(number)) {
                    counter += 1;
                    matched.add(number);
                }
            }
        }
        System.out.println(counter + "/" + String.format("%02d", numbers.size()) + " " + String.format("%24s", matched.toString()) + " / " + numbers);
    }
}
