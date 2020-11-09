package lottoPropositions;


import entity.OneDraw;
import support.Auxiliary;

import java.util.ArrayList;

public class NumbersAfterDuet {

    public ArrayList<Integer> returnNumbersAfterDuet(ArrayList<OneDraw> lotteryNumbers, int index) {
        ArrayList<Integer> propositionForNextGame = new ArrayList<>();
        ArrayList<Integer> currentDrawNumbers = lotteryNumbers.get(index).getDrawNumbers();
        ArrayList<Integer> oneGamesBeforeNumbers = lotteryNumbers.get(index - 1).getDrawNumbers();

        numbersBeside(propositionForNextGame, currentDrawNumbers);
        numbersBeside(propositionForNextGame, currentDrawNumbers, oneGamesBeforeNumbers);

        return Auxiliary.changeNumbersOverAndUnder(propositionForNextGame);
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> currentDrawNumbers) {
        for (Integer number : currentDrawNumbers) {
            if (currentDrawNumbers.contains(number + 1)) {
                if (!currentDrawNumbers.contains( number + 2)
                        && !currentDrawNumbers.contains( number - 1)) {

                    propositionForNextGame.add( number - 2);
                    propositionForNextGame.add( number - 1);
                    propositionForNextGame.add( number + 2);
                    propositionForNextGame.add( number + 3);

                } else if (currentDrawNumbers.contains(number + 2)
                        && !currentDrawNumbers.contains(number + 3)
                        && !currentDrawNumbers.contains(number - 1)) {

                    propositionForNextGame.add(number - 2);
                    propositionForNextGame.add(number - 1);
                    propositionForNextGame.add(number + 3);
                    propositionForNextGame.add(number + 4);

                } else if (currentDrawNumbers.contains(number + 2)
                        && currentDrawNumbers.contains(number + 3)
                        && !currentDrawNumbers.contains(number - 1)) {

                    propositionForNextGame.add(number - 2);
                    propositionForNextGame.add(number - 1);
                    propositionForNextGame.add(number + 4);
                    propositionForNextGame.add(number + 5);
                }
            }
        }
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> currentDrawNumbers, ArrayList<Integer> oneGameBeforeNumbers) {
        for (Integer number : oneGameBeforeNumbers) {
            if (oneGameBeforeNumbers.contains(number + 1)) {
                if (!oneGameBeforeNumbers.contains(number + 2)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!currentDrawNumbers.contains(number - 2)
                            && !currentDrawNumbers.contains(number - 1)
                            && !currentDrawNumbers.contains(number + 2)
                            && !currentDrawNumbers.contains(number + 3)) {
                        propositionForNextGame.add(number - 2);
                        propositionForNextGame.add(number - 1);
                        propositionForNextGame.add(number + 2);
                        propositionForNextGame.add(number + 3);
                    }
                } else if (oneGameBeforeNumbers.contains(number + 2)
                        && !oneGameBeforeNumbers.contains(number + 3)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!currentDrawNumbers.contains(number - 2)
                            && !currentDrawNumbers.contains(number - 1)
                            && !currentDrawNumbers.contains(number + 3)
                            && !currentDrawNumbers.contains(number + 4)) {
                        propositionForNextGame.add(number - 2);
                        propositionForNextGame.add(number - 1);
                        propositionForNextGame.add(number + 3);
                        propositionForNextGame.add(number + 4);
                    }
                } else if (oneGameBeforeNumbers.contains(number + 2)
                        && oneGameBeforeNumbers.contains(number + 3)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!currentDrawNumbers.contains(number - 2)
                            && !currentDrawNumbers.contains(number - 1)
                            && !currentDrawNumbers.contains(number + 4)
                            && !currentDrawNumbers.contains(number + 5)) {
                        propositionForNextGame.add(number - 2);
                        propositionForNextGame.add(number - 1);
                        propositionForNextGame.add(number + 4);
                        propositionForNextGame.add(number + 5);
                    }
                }
            }
        }
    }
}

