package lottoPropositions;


import entity.OneDraw;
import support.Auxiliary;

import java.util.ArrayList;

public class NumbersAfterSlant {
    public ArrayList<Integer> returnNextSlantNumber(ArrayList<OneDraw> lotteryNumbers, int index) {
        ArrayList<Integer> propositionForNextGame = new ArrayList<>();
        int propositionOfNumber;
        ArrayList<Integer> lastGameNumbers = lotteryNumbers.get(index).getDrawNumbers();
        ArrayList<Integer> oneGamesBeforeNumbers = lotteryNumbers.get(index+1).getDrawNumbers();
        ArrayList<Integer> twoGamesBeforeNumbers = lotteryNumbers.get(index+2).getDrawNumbers();
        for (Object number : lastGameNumbers) {
            for (Object number2 : oneGamesBeforeNumbers) {

                if ((Integer) number == (Integer) number2 - 1) {
                    propositionOfNumber = (Integer) number - 1;
                    if (!twoGamesBeforeNumbers.contains((Integer) number2 + 1)) {
                        propositionForNextGame.add(propositionOfNumber);
                    }
                } else if ((Integer) number == (Integer) number2 + 1) {
                    propositionOfNumber = (Integer) number + 1;
                    if (!twoGamesBeforeNumbers.contains((Integer) number2 - 1)) {
                        propositionForNextGame.add(propositionOfNumber);
                    }
                }
            }
        }
        return Auxiliary.changeNumbersOverAndUnder(propositionForNextGame);
    }

}
