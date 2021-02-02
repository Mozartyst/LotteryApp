package lottoPropositions;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class NumbersFromFewLastDraws {
    public Set<Integer> get(ArrayList<OneDraw> lotteryNumbers, int range, int index) {
        Set<Integer> proposition = new TreeSet<>();
        for (int i = index - range; i < index; i++) {
            for (Integer number : lotteryNumbers.get(i).getDrawNumbers()) {
                proposition.add(number);
            }
        }
        return proposition;
    }
}
