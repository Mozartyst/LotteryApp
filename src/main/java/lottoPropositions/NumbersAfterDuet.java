package lottoPropositions;


import entity.OneDraw;
import support.Auxiliary;

import java.util.ArrayList;

public class NumbersAfterDuet {

    public ArrayList<Integer> returnNumbersAfterDuet(ArrayList<OneDraw> lotteryNumbers, int index) {
        ArrayList<Integer> propositionForNextGame = new ArrayList<>();
        ArrayList<Integer> lastGameNumbers = lotteryNumbers.get(index).getDrawNumbers();
        ArrayList<Integer> oneGamesBeforeNumbers = lotteryNumbers.get(index - 1).getDrawNumbers();

        numbersBeside(propositionForNextGame, lastGameNumbers);
        numbersBeside(propositionForNextGame, lastGameNumbers, oneGamesBeforeNumbers);

        return Auxiliary.changeNumbersOverAndUnder(propositionForNextGame);
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> lastGameNumbers) {
        for (Integer number : lastGameNumbers) {
            if (lastGameNumbers.contains(number + 1)) {
                if (!lastGameNumbers.contains( number + 2)
                        && !lastGameNumbers.contains( number - 1)) {

                    propositionForNextGame.add( number - 2);
                    propositionForNextGame.add( number - 1);
                    propositionForNextGame.add( number + 2);
                    propositionForNextGame.add( number + 3);

                } else if (lastGameNumbers.contains(number + 2)
                        && !lastGameNumbers.contains(number + 3)
                        && !lastGameNumbers.contains(number - 1)) {

                    propositionForNextGame.add(number - 2);
                    propositionForNextGame.add(number - 1);
                    propositionForNextGame.add(number + 3);
                    propositionForNextGame.add(number + 4);

                } else if (lastGameNumbers.contains(number + 2)
                        && lastGameNumbers.contains(number + 3)
                        && !lastGameNumbers.contains(number - 1)) {

                    propositionForNextGame.add(number - 2);
                    propositionForNextGame.add(number - 1);
                    propositionForNextGame.add(number + 4);
                    propositionForNextGame.add(number + 5);
                }
            }
        }
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> lastGameNumbers, ArrayList<Integer> oneGameBeforeNumbers) {
        for (Integer number : oneGameBeforeNumbers) {
            if (oneGameBeforeNumbers.contains(number + 1)) {
                if (!oneGameBeforeNumbers.contains(number + 2)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!lastGameNumbers.contains(number - 2)
                            && !lastGameNumbers.contains(number - 1)
                            && !lastGameNumbers.contains(number + 2)
                            && !lastGameNumbers.contains(number + 3)) {
                        propositionForNextGame.add(number - 2);
                        propositionForNextGame.add(number - 1);
                        propositionForNextGame.add(number + 2);
                        propositionForNextGame.add(number + 3);
                    }
                } else if (oneGameBeforeNumbers.contains(number + 2)
                        && !oneGameBeforeNumbers.contains(number + 3)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!lastGameNumbers.contains(number - 2)
                            && !lastGameNumbers.contains(number - 1)
                            && !lastGameNumbers.contains(number + 3)
                            && !lastGameNumbers.contains(number + 4)) {
                        propositionForNextGame.add(number - 2);
                        propositionForNextGame.add(number - 1);
                        propositionForNextGame.add(number + 3);
                        propositionForNextGame.add(number + 4);
                    }
                } else if (oneGameBeforeNumbers.contains(number + 2)
                        && oneGameBeforeNumbers.contains(number + 3)
                        && !oneGameBeforeNumbers.contains(number - 1)) {
                    if (!lastGameNumbers.contains(number - 2)
                            && !lastGameNumbers.contains(number - 1)
                            && !lastGameNumbers.contains(number + 4)
                            && !lastGameNumbers.contains(number + 5)) {
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

