package lottoPropositions;


import support.Auxiliary;

import java.util.ArrayList;

public class NumbersAfterDuet {

    public ArrayList<Integer> returnNumbersAfterDuet(ArrayList<ArrayList<Integer>> lotteryNumbers, int index) {
        ArrayList<Integer> propositionForNextGame = new ArrayList<>();
        ArrayList<Integer> lastGameNumbers = lotteryNumbers.get(index);
        ArrayList<Integer> oneGamesBeforeNumbers = lotteryNumbers.get(index + 1);

        numbersBeside(propositionForNextGame, lastGameNumbers);
        numbersBeside(propositionForNextGame, lastGameNumbers, oneGamesBeforeNumbers);

        return Auxiliary.changeNumbersOverAndUnder(propositionForNextGame);
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> lastGameNumbers) {
        for (Object number : lastGameNumbers) {
            if (lastGameNumbers.contains((Integer) number + 1)) {
                if (!lastGameNumbers.contains((Integer) number + 2)
                        && !lastGameNumbers.contains((Integer) number - 1)) {

                    propositionForNextGame.add((Integer) number - 2);
                    propositionForNextGame.add((Integer) number - 1);
                    propositionForNextGame.add((Integer) number + 2);
                    propositionForNextGame.add((Integer) number + 3);

                } else if (lastGameNumbers.contains((Integer) number + 2)
                        && !lastGameNumbers.contains((Integer) number + 3)
                        && !lastGameNumbers.contains((Integer) number - 1)) {

                    propositionForNextGame.add((Integer) number - 2);
                    propositionForNextGame.add((Integer) number - 1);
                    propositionForNextGame.add((Integer) number + 3);
                    propositionForNextGame.add((Integer) number + 4);

                } else if (lastGameNumbers.contains((Integer) number + 2)
                        && lastGameNumbers.contains((Integer) number + 3)
                        && !lastGameNumbers.contains((Integer) number - 1)) {

                    propositionForNextGame.add((Integer) number - 2);
                    propositionForNextGame.add((Integer) number - 1);
                    propositionForNextGame.add((Integer) number + 4);
                    propositionForNextGame.add((Integer) number + 5);
                }
            }
        }
    }

    private void numbersBeside(ArrayList<Integer> propositionForNextGame, ArrayList<Integer> lastGameNumbers, ArrayList<Integer> oneGameBeforeNumbers) {
        for (Object number : oneGameBeforeNumbers) {
            if (oneGameBeforeNumbers.contains((Integer) number + 1)) {
                if (!oneGameBeforeNumbers.contains((Integer) number + 2)
                        && !oneGameBeforeNumbers.contains((Integer) number - 1)) {
                    if (!lastGameNumbers.contains((Integer) number - 2)
                            && !lastGameNumbers.contains((Integer) number - 1)
                            && !lastGameNumbers.contains((Integer) number + 2)
                            && !lastGameNumbers.contains((Integer) number + 3)) {
                        propositionForNextGame.add((Integer) number - 2);
                        propositionForNextGame.add((Integer) number - 1);
                        propositionForNextGame.add((Integer) number + 2);
                        propositionForNextGame.add((Integer) number + 3);
                    }
                } else if (oneGameBeforeNumbers.contains((Integer) number + 2)
                        && !oneGameBeforeNumbers.contains((Integer) number + 3)
                        && !oneGameBeforeNumbers.contains((Integer) number - 1)) {
                    if (!lastGameNumbers.contains((Integer) number - 2)
                            && !lastGameNumbers.contains((Integer) number - 1)
                            && !lastGameNumbers.contains((Integer) number + 3)
                            && !lastGameNumbers.contains((Integer) number + 4)) {
                        propositionForNextGame.add((Integer) number - 2);
                        propositionForNextGame.add((Integer) number - 1);
                        propositionForNextGame.add((Integer) number + 3);
                        propositionForNextGame.add((Integer) number + 4);
                    }
                } else if (oneGameBeforeNumbers.contains((Integer) number + 2)
                        && oneGameBeforeNumbers.contains((Integer) number + 3)
                        && !oneGameBeforeNumbers.contains((Integer) number - 1)) {
                    if (!lastGameNumbers.contains((Integer) number - 2)
                            && !lastGameNumbers.contains((Integer) number - 1)
                            && !lastGameNumbers.contains((Integer) number + 4)
                            && !lastGameNumbers.contains((Integer) number + 5)) {
                        propositionForNextGame.add((Integer) number - 2);
                        propositionForNextGame.add((Integer) number - 1);
                        propositionForNextGame.add((Integer) number + 4);
                        propositionForNextGame.add((Integer) number + 5);
                    }
                }
            }
        }
    }
}

