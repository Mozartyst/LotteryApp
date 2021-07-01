package checker;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;

public class PropositionChecker {
    public boolean isMatch(Set<Integer> propositionSet, ArrayList<OneDraw> lotteryNumbers, Integer currentIndex){
        boolean isMatch = true;
        ArrayList<Integer> drawNumbers = lotteryNumbers.get(currentIndex + 1).getDrawNumbers();
        for (Integer number :
                propositionSet) {
            if (!drawNumbers.contains(number)){
                isMatch = false;
                break;
            }
        }
        return isMatch;
    }
}
