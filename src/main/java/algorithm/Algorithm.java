package algorithm;

import dataSupport.FileService;
import entity.Number;
import lottoPropositions.NumbersAfterMultiCombinations;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Algorithm implements Serializable {

    private final TreeSet<Integer> proposition = new TreeSet<>();

    public TreeSet<Integer> getPropositionList(Integer index) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LastYearLotteryNumbersFile");
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
        TreeSet<Integer> propositions = new Proposition(index).forMultiCombination();
        System.out.println(propositions);
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index);

        propositions.forEach((betNumber) -> {
            if (betNumber == 1) {
                float bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                float over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                if (bet < over) {
                    proposition.add(betNumber);
                } else {
                    proposition.add(betNumber + 1);
                }
            } else if (betNumber == 47) {
                float under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                float bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                if (under < bet) {
                    proposition.add(betNumber - 1);
                } else {
                    proposition.add(betNumber);
                }
            } else {
                float under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                float bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                float over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                if (under < bet && under < over) {
                    proposition.add(betNumber - 1);
                } else if (bet < under && bet < over) {
                    proposition.add(betNumber);
                } else {
                    proposition.add(betNumber + 1);
                }
            }
        });
        return proposition;
    }
}
