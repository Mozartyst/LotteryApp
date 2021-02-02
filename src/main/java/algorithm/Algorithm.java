package algorithm;

import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersAfterMultiCombinations;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

public class Algorithm implements Serializable {

    private final TreeSet<Integer> proposition = new TreeSet<>();

    public TreeSet<Integer> getPropositionList(Integer index
            , Properties properties
            , ArrayList<OneDraw> lotteryNumbers
            , TreeMap<Integer, Number> listOfNumbers) throws IOException, ClassNotFoundException {

        TreeSet<Integer> propositions = new Proposition().forMultiCombination(lotteryNumbers, properties, index);
        System.out.println(propositions);
        TreeMap<Integer, Integer> multiProposition = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index, properties);

        propositions.forEach((betNumber) -> {
            float twoUnder = 1.1F;
            float under = 1.1F;
            float bet = 1.1F;
            float over = 1.1F;
            float twoOver = 1.1F;

            if (betNumber == 1) {
                try {
                    bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                } catch (Exception e) {

                }
                try {
                    over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                } catch (Exception e) {

                }
                try {
                    twoOver = (float) listOfNumbers.get(betNumber + 2).getOccurred() / multiProposition.get(betNumber + 2);
                } catch (Exception e) {

                }
                if (bet < over && bet < twoOver) {
                    proposition.add(betNumber);
                } else if (over < bet && over < twoOver) {
                    proposition.add(betNumber + 1);
                } else {
                    proposition.add(betNumber + 2);
                }
            } else if (betNumber == Integer.parseInt(properties.getProperty("range"))) {
                try {
                    twoUnder = (float) listOfNumbers.get(betNumber - 2).getOccurred() / multiProposition.get(betNumber - 2);
                } catch (Exception e) {

                }
                try {
                    under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                } catch (Exception e) {

                }
                try {
                    bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                } catch (Exception e) {

                }
                if (twoUnder < under && twoUnder < bet) {
                    proposition.add(betNumber - 2);
                } else if (under < bet && under < twoUnder) {
                    proposition.add(betNumber - 1);
                } else {
                    proposition.add(betNumber);
                }
            } else if (betNumber == 2) {
                try {
                    under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                } catch (Exception e) {

                }
                try {
                    bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                } catch (Exception e) {

                }
                try {
                    over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                } catch (Exception e) {

                }
                try {
                    twoOver = (float) listOfNumbers.get(betNumber + 2).getOccurred() / multiProposition.get(betNumber + 2);
                } catch (Exception e) {

                }
                if (under < bet && under < over && under < twoOver) {
                    proposition.add(betNumber - 1);
                } else if (bet < under && bet < over && bet < twoOver) {
                    proposition.add(betNumber);
                } else if (over < under && over < bet && over < twoOver) {
                    proposition.add(betNumber + 1);
                } else {
                    proposition.add(betNumber + 2);
                }
            } else if (betNumber == (Integer.parseInt(properties.getProperty("range")) - 1)) {
                try {
                    twoUnder = (float) listOfNumbers.get(betNumber - 2).getOccurred() / multiProposition.get(betNumber - 2);
                } catch (Exception e) {

                }
                try {
                    under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                } catch (Exception e) {

                }
                try {
                    bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                } catch (Exception e) {

                }
                try {
                    over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                } catch (Exception e) {

                }
                if (twoUnder < under && twoUnder < bet && twoUnder < over) {
                    proposition.add(betNumber - 2);
                } else if (under < twoUnder && under < bet && under < over) {
                    proposition.add(betNumber - 1);
                } else if (bet < twoUnder && bet < under && bet < over) {
                    proposition.add(betNumber);
                } else {
                    proposition.add(betNumber + 1);
                }
            } else {
                try {
                    twoUnder = (float) listOfNumbers.get(betNumber - 2).getOccurred() / multiProposition.get(betNumber - 2);
                } catch (Exception e) {

                }
                try {
                    under = (float) listOfNumbers.get(betNumber - 1).getOccurred() / multiProposition.get(betNumber - 1);
                } catch (Exception e) {

                }
                try {
                    bet = (float) listOfNumbers.get(betNumber).getOccurred() / multiProposition.get(betNumber);
                } catch (Exception e) {

                }
                try {
                    over = (float) listOfNumbers.get(betNumber + 1).getOccurred() / multiProposition.get(betNumber + 1);
                } catch (Exception e) {

                }
                try {
                    twoOver = (float) listOfNumbers.get(betNumber + 2).getOccurred() / multiProposition.get(betNumber + 2);
                } catch (Exception e) {

                }
                if (twoUnder < under && twoUnder < bet && twoUnder < over && twoUnder < twoOver) {
                    proposition.add(betNumber - 2);
                } else if (under < twoUnder && under < bet && under < over && under < twoOver) {
                    proposition.add(betNumber - 1);
                } else if (bet < twoUnder && bet < under && bet < over && bet < twoOver) {
                    proposition.add(betNumber);
                } else if (over < twoUnder && over < under && over < bet && over < twoOver) {
                    proposition.add(betNumber + 1);
                } else {
                    proposition.add(betNumber + 2);
                }
            }
        });
        return proposition;
    }
}
