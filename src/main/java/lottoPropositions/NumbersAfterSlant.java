package lottoPropositions;


import entity.OneDraw;
import support.Auxiliary;

import java.util.ArrayList;

public class NumbersAfterSlant {
    public ArrayList<Integer> returnNextSlantNumber(ArrayList<OneDraw> lotteryNumbers, int index) {
        ArrayList<Integer> propositionForNextGame = new ArrayList<>();
        int propositionOfNumber;
        ArrayList<Integer> currentDraw = lotteryNumbers.get(index).getDrawNumbers();
        ArrayList<Integer> oneDrawBefore = lotteryNumbers.get(index-1).getDrawNumbers();
        ArrayList<Integer> twoDrawsBefore = lotteryNumbers.get(index-2).getDrawNumbers();
        for (Integer number : currentDraw) {
            for (Integer number2 : oneDrawBefore) {

                if (number.equals(number2 - 1)) {
                    propositionOfNumber = number - 1;
                    if (!twoDrawsBefore.contains(number2 + 1)) {
                        propositionForNextGame.add(propositionOfNumber);
                    }
                } else if (number.equals(number2 + 1)) {
                    propositionOfNumber = number + 1;
                    if (!twoDrawsBefore.contains(number2 - 1)) {
                        propositionForNextGame.add(propositionOfNumber);
                    }
                }
            }
        }
        return Auxiliary.changeNumbersOverAndUnder(propositionForNextGame);
    }

}
