package support;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class Auxiliary {

    public static Integer returnAverage(Collection<Integer> integerCollection) {
        AtomicReference<Integer> total = new AtomicReference<>(0);
        integerCollection.forEach((x) -> total.updateAndGet(v -> v + x));
        return total.get() / integerCollection.size();
    }

    public static ArrayList<Integer> changeNumbersOverAndUnder(ArrayList<Integer> propositionForNextGame) {
        while (propositionForNextGame.contains(0)
                || propositionForNextGame.contains(48)
                || propositionForNextGame.contains(-1)
                || propositionForNextGame.contains(49)) {
            if (propositionForNextGame.contains(0)) {
                propositionForNextGame.set(propositionForNextGame.indexOf(0), 47);
            }
            if (propositionForNextGame.contains(48)) {
                propositionForNextGame.set(propositionForNextGame.indexOf(48), 1);
            }
            if (propositionForNextGame.contains(-1)) {
                propositionForNextGame.set(propositionForNextGame.indexOf(-1), 46);
            }
            if (propositionForNextGame.contains(49)) {
                propositionForNextGame.set(propositionForNextGame.indexOf(49), 2);
            }
        }
        return propositionForNextGame;
    }

    public static ArrayList<CombinationNumbers> returnCombinationKeys(ArrayList<Integer> weeklyNumbers) {
        ArrayList<CombinationNumbers> combinationNumbersArrayList = new ArrayList<>();
        for (Integer firstNumber : weeklyNumbers) {
            for (Integer secondNumber : weeklyNumbers) {
                if (firstNumber < secondNumber) {
                    CombinationNumbers keyPairs = new CombinationNumbers(firstNumber, secondNumber);
                    combinationNumbersArrayList.add(keyPairs);
                    for (Integer thirdNumber : weeklyNumbers) {
                        if (secondNumber < thirdNumber) {
                            CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            combinationNumbersArrayList.add(keyTriple);
                        }
                    }
                }
            }
        }
        return combinationNumbersArrayList;
    }

    public static TreeMap<Integer, Integer> returnListOfNumbersAroundAverage(TreeMap<Integer, Integer> listOfNumbers) {
        TreeMap<Integer, Integer> listOfNumbersAroundAverage = new TreeMap<>();
        AtomicReference<Integer> average = new AtomicReference<>(0);
        listOfNumbers.values().forEach((x) -> average.updateAndGet(v -> v + x));
        try {
            average.set(average.get() / listOfNumbers.size());
        } catch (ArithmeticException e) {
            average.set(0);
        }
        listOfNumbers.forEach((x, y) -> {
            if (y > average.get() + 2) {
                listOfNumbersAroundAverage.put(x, y);
            }
        });
        return listOfNumbersAroundAverage;
    }

    public static TreeMap<Integer, Integer> returnListOfNumbersOverAverage(TreeMap<Integer, Integer> listOfNumbers) {
        TreeMap<Integer, Integer> listOfNumbersOverAverage = new TreeMap<>();
        AtomicReference<Integer> average = new AtomicReference<>(0);
        for (Integer integer : listOfNumbers.values()) {
            average.updateAndGet(v -> v + integer);
        }
        try {
            average.set(average.get() / listOfNumbers.size());
        } catch (ArithmeticException e) {
            average.set(0);
        }
        listOfNumbers.forEach((x, y) -> {
            if (y > average.get()) {
                listOfNumbersOverAverage.put(x, y);
            }
        });
        return listOfNumbersOverAverage;
    }

    public static TreeMap<Integer, Integer> returnCompressedPropositionNumbers(TreeMap<Integer, Integer> propositionList) {
        TreeMap<Integer, Integer> compressedList = new TreeMap<>();
        propositionList.forEach((x, y) -> {
            if (x < 46) {
                if (propositionList.containsKey(x) && propositionList.containsKey(x + 1) && propositionList.containsKey(x + 2)) {
                    if (y > propositionList.get(x + 1) && propositionList.get(x + 1) < propositionList.get(x + 2)) {
                        compressedList.put(x + 1, propositionList.get(x + 1));
                    }
                }
            }
        });
        return compressedList;
    }

    public static ArrayList<ArrayList<Integer>> returnReversedListLotteryNumbers(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        ArrayList<ArrayList<Integer>> reversedLotteryNumbersList = new ArrayList<>();
        for (int index = lotteryNumbers.size() - 1; index >= 0; index--) {
            reversedLotteryNumbersList.add(lotteryNumbers.get(index));
        }
        return reversedLotteryNumbersList;
    }

    public static ArrayList<OneDraw> returnReversedOneDraws(ArrayList<OneDraw> lotteryNumbers) {
        ArrayList<OneDraw> reversedLotteryNumbersList = new ArrayList<>();
        for (int index = lotteryNumbers.size() - 1; index >= 0; index--) {
            reversedLotteryNumbersList.add(lotteryNumbers.get(index));
        }
        return reversedLotteryNumbersList;
    }

    public TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> reducer(TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumber) {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> tempList = new TreeMap<>(listOfNumber);
        tempList.forEach((x, y) -> listOfNumber.replace(x, returnListOfNumbersOverAverage(y)));
        tempList.putAll(listOfNumber);
        tempList.forEach((x, y) -> {
            if (y.size() == 0) listOfNumber.remove(x);
        });
        return listOfNumber;
    }

    public static boolean RepeatedReducer(Integer first, Integer second, Integer third, Integer fourth, Integer fifth, Integer sixth, ArrayList<ArrayList<Integer>> lotteryNumbers) {

        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            if (weeklyNumbers.contains(fifth)) {

            }
        }

        return true;
    }
}
