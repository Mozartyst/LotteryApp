package lottoPropositions;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class NumbersFromFewLastDraws {
    public TreeSet<Integer> get(ArrayList<OneDraw> lotteryNumbers, int range, int lotteryIndex) {
        TreeSet<Integer> proposition = new TreeSet<>();
        for (int i = lotteryIndex - range; i < lotteryIndex; i++) {
            OneDraw weekNumbers = lotteryNumbers.get(i);
            for (Integer number : weekNumbers.getDrawNumbers()) {
                proposition.add(number);
            }
        }
        return proposition;
    }
}
