package support;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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

    public static int returnMaxKey(Map<Integer, Integer> map) {
        AtomicInteger number = new AtomicInteger();
        AtomicInteger max = new AtomicInteger(0);
        map.forEach((key, value) -> {
            if (max.get() < value) {
                number.set(key);
                max.set(value);

            }
        });

        return number.get();
    }

    public static Set<Integer> returnFiveHighestKey(Map<Integer, Integer> map) {
        Set<Integer> highestList = new TreeSet<>();
        AtomicInteger first = new AtomicInteger(0);
        AtomicInteger firstValue = new AtomicInteger(0);
        AtomicInteger second = new AtomicInteger(0);
        AtomicInteger secondValue = new AtomicInteger(0);
        AtomicInteger third = new AtomicInteger(0);
        AtomicInteger thirdValue = new AtomicInteger(0);
        AtomicInteger fourth = new AtomicInteger(0);
        AtomicInteger fourthValue = new AtomicInteger(0);
        AtomicInteger fifth = new AtomicInteger(0);
        AtomicInteger fifthValue = new AtomicInteger(0);
        map.forEach((key, value) -> {
            if (value > firstValue.get()) {
                fifthValue.set(fourthValue.get());
                fifth.set(fourth.get());
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(firstValue.get());
                second.set(first.get());
                firstValue.set(value);
                first.set(key);
            } else if (value > secondValue.get()) {
                fifthValue.set(fourthValue.get());
                fifth.set(fourth.get());
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(value);
                second.set(key);
            } else if (value > thirdValue.get()) {
                fifthValue.set(fourthValue.get());
                fifth.set(fourth.get());
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(value);
                third.set(key);
            } else if (value > fourthValue.get()) {
                fifthValue.set(fourthValue.get());
                fifth.set(fourth.get());
                fourthValue.set(value);
                fourth.set(key);
            } else if (value > fifthValue.get()) {
                fifthValue.set(value);
                fifth.set(key);
            }
        });
        if (first.get() != 0) {
            highestList.add(first.get());
        }
        if (second.get() != 0) {
            highestList.add(second.get());
        }
        if (third.get() != 0) {
            highestList.add(third.get());
        }
        if (fourth.get() != 0) {
            highestList.add(fourth.get());
        }
        if (fifth.get() != 0) {
            highestList.add(fifth.get());
        }
        return highestList;
    }

    public static Set<Integer> returnFourHighestKey(Map<Integer, Integer> map) {
        Set<Integer> highestList = new TreeSet<>();
        AtomicInteger first = new AtomicInteger(0);
        AtomicInteger firstValue = new AtomicInteger(0);
        AtomicInteger second = new AtomicInteger(0);
        AtomicInteger secondValue = new AtomicInteger(0);
        AtomicInteger third = new AtomicInteger(0);
        AtomicInteger thirdValue = new AtomicInteger(0);
        AtomicInteger fourth = new AtomicInteger(0);
        AtomicInteger fourthValue = new AtomicInteger(0);
        map.forEach((key, value) -> {
            if (value > firstValue.get()) {
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(firstValue.get());
                second.set(first.get());
                firstValue.set(value);
                first.set(key);
            } else if (value > secondValue.get()) {
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(value);
                second.set(key);
            } else if (value > thirdValue.get()) {
                fourthValue.set(thirdValue.get());
                fourth.set(third.get());
                thirdValue.set(value);
                third.set(key);
            } else if (value > fourthValue.get()) {
                fourthValue.set(value);
                fourth.set(key);
            }
        });
        if (first.get() != 0) {
            highestList.add(first.get());
        }
        if (second.get() != 0) {
            highestList.add(second.get());
        }
        if (third.get() != 0) {
            highestList.add(third.get());
        }
        if (fourth.get() != 0) {
            highestList.add(fourth.get());
        }
        return highestList;
    }

    public static Set<Integer> returnThreeHighestKey(Map<Integer, Integer> map) {
        Set<Integer> highestList = new TreeSet<>();
        AtomicInteger first = new AtomicInteger(0);
        AtomicInteger firstValue = new AtomicInteger(0);
        AtomicInteger second = new AtomicInteger(0);
        AtomicInteger secondValue = new AtomicInteger(0);
        AtomicInteger third = new AtomicInteger(0);
        AtomicInteger thirdValue = new AtomicInteger(0);
        map.forEach((key, value) -> {
            if (value > firstValue.get()) {
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(firstValue.get());
                second.set(first.get());
                firstValue.set(value);
                first.set(key);
            } else if (value > secondValue.get()) {
                thirdValue.set(secondValue.get());
                third.set(second.get());
                secondValue.set(value);
                second.set(key);
            } else if (value > thirdValue.get()) {
                thirdValue.set(value);
                third.set(key);
            }
        });
        if (first.get() != 0) {
            highestList.add(first.get());
        }
        if (second.get() != 0) {
            highestList.add(second.get());
        }
        if (third.get() != 0) {
            highestList.add(third.get());
        }
        return highestList;
    }

    public static Set<Integer> returnTwoHighestKey(Map<Integer, Integer> map) {
        Set<Integer> highestList = new TreeSet<>();
        AtomicInteger first = new AtomicInteger(0);
        AtomicInteger firstValue = new AtomicInteger(0);
        AtomicInteger second = new AtomicInteger(0);
        AtomicInteger secondValue = new AtomicInteger(0);

        map.forEach((key, value) -> {
            if (value > firstValue.get()) {
                secondValue.set(firstValue.get());
                second.set(first.get());
                firstValue.set(value);
                first.set(key);
            } else if (value > secondValue.get()) {
                secondValue.set(value);
                second.set(key);
            }
        });
        if (first.get() != 0) {
            highestList.add(first.get());
        }
        if (second.get() != 0) {
            highestList.add(second.get());
        }
        return highestList;
    }

    public static void returnAfterNumbersFromIndexes(Set<Integer> indexesWhereAppeared, ArrayList<OneDraw> lotteryNumbers, Map<Integer, Integer> afterNumbers) {
        for (Integer index : indexesWhereAppeared) {
            for (Integer number : lotteryNumbers.get(index + 1).getDrawNumbers()) {
                if (afterNumbers.containsKey(number)) {
                    afterNumbers.replace(number, afterNumbers.get(number) + 1);
                } else {
                    afterNumbers.put(number, 1);
                }
            }
        }
    }

    public static void addAfterNumber(Map<Integer, Integer> afterNumbers, Integer number, Integer size) {
        if (afterNumbers.containsKey(number)) {
            afterNumbers.replace(number, afterNumbers.get(number) + size);
        } else {
            afterNumbers.put(number, size);
        }
    }
}
