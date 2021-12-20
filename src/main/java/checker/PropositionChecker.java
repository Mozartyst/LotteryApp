package checker;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Set;

public class PropositionChecker {
    public boolean isMatch(Set<Integer> propositionSet, OneDraw lotteryNumbers){
        boolean isMatch = false;
        ArrayList<Integer> drawNumbers = lotteryNumbers.getDrawNumbers();
        for (Integer number :
                propositionSet) {
            if (drawNumbers.contains(number)){
                isMatch = true;

            }else {
                isMatch = false;
                break;
            }
        }
        return isMatch;
    }
}
