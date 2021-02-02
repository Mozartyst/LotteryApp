package creators.multiThrees;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.NonSortedCombinationNumbers;

import java.util.*;

public class NumbersForMultiThreesFinder implements Runnable {
    private final MultiCombinationNumber multiToProceed;
    private final TreeMap<Integer, Set<MultiCombinationNumber>> mapMultiToReturn;
    private final TreeMap<CombinationNumbers, Set<Integer>> byCombination;
    private final Set<CombinationNumbers> combinationNumbers;
    private final Integer rangeFrom;
    private final Integer rangeTo;

    public NumbersForMultiThreesFinder(MultiCombinationNumber multiToProceed
            , TreeMap<Integer, Set<MultiCombinationNumber>> mapMultiToReturn
            , TreeMap<CombinationNumbers, Set<Integer>> byCombination
            , Set<CombinationNumbers> combinationNumbers
            , ThreadGroup threadGroup
            , Integer rangeFrom
            , Integer rangeTo) {
        this.multiToProceed = multiToProceed;
        this.mapMultiToReturn = mapMultiToReturn;
        this.byCombination = byCombination;
        this.combinationNumbers = combinationNumbers;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        Thread t = new Thread(threadGroup, this);
        t.start();
    }

    @Override
    public void run() {
        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti = new TreeMap<>();
        if (multiToProceed.getFirstKey().getNumbers().length == 1) {
            List<Integer> firstNumbers = getNumbersForOne(
                    multiToProceed.getSecondKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getFirstNumber()
                    , multiToProceed.getFirstKey().getFirstNumber());
            List<Integer> secondNumbers = getNumbersForOne(
                    multiToProceed.getFirstKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getFirstNumber()
                    , multiToProceed.getSecondKey().getFirstNumber());
            List<Integer> thirdNumbers = getNumbersForOne(
                    multiToProceed.getFirstKey().getFirstNumber()
                    , multiToProceed.getSecondKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getFirstNumber());
            for (Integer firstNumber : firstNumbers) {
                for (Integer secondNumber : secondNumbers) {
                    if (firstNumber.equals(secondNumber)) {
                        continue;
                    }
                    for (Integer thirdNumber : thirdNumbers) {
                        if (firstNumber.equals(thirdNumber)
                                || secondNumber.equals(thirdNumber)) {
                            continue;
                        } else {
                            NonSortedCombinationNumbers tempComFirst = new NonSortedCombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            MultiCombinationNumber multi = new MultiCombinationNumber(
                                    new CombinationNumbers(multiToProceed.getFirstKey().getFirstNumber(), tempComFirst.getFirstNumber()).getNumbers()
                                    , new CombinationNumbers(multiToProceed.getSecondKey().getFirstNumber(), tempComFirst.getSecondNumber()).getNumbers()
                                    , new CombinationNumbers(multiToProceed.getThirdKey().getFirstNumber(), tempComFirst.getThirdNumber()).getNumbers());
                            addMulti(mapMulti, multi);
                        }
                    }
                }
            }
        } else {
            Set<Integer> firstNumbers = new TreeSet<>(getNumbersForTwo(
                    multiToProceed.getSecondKey().getFirstNumber()
                    , multiToProceed.getSecondKey().getSecondNumber()
                    , multiToProceed.getThirdKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getSecondNumber()
                    , multiToProceed.getFirstKey().getFirstNumber()
                    , multiToProceed.getFirstKey().getSecondNumber()));

            Set<Integer> secondNumbers = new TreeSet<>(getNumbersForTwo(
                    multiToProceed.getFirstKey().getFirstNumber()
                    , multiToProceed.getFirstKey().getSecondNumber()
                    , multiToProceed.getThirdKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getSecondNumber()
                    , multiToProceed.getSecondKey().getFirstNumber()
                    , multiToProceed.getSecondKey().getSecondNumber()));
            Set<Integer> thirdNumbers = new TreeSet<>(getNumbersForTwo(
                    multiToProceed.getFirstKey().getFirstNumber()
                    , multiToProceed.getFirstKey().getSecondNumber()
                    , multiToProceed.getSecondKey().getFirstNumber()
                    , multiToProceed.getSecondKey().getSecondNumber()
                    , multiToProceed.getThirdKey().getFirstNumber()
                    , multiToProceed.getThirdKey().getSecondNumber()));
            for (Integer firstNumber : firstNumbers) {
                for (Integer secondNumber : secondNumbers) {
                    if (firstNumber.equals(secondNumber)) {
                        continue;
                    }
                    for (Integer thirdNumber : thirdNumbers) {
                        if (firstNumber.equals(thirdNumber)
                                || secondNumber.equals(thirdNumber)) {
                            continue;
                        } else {
                            NonSortedCombinationNumbers tempComFirst = new NonSortedCombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            MultiCombinationNumber tempMulti = new MultiCombinationNumber(
                                    new CombinationNumbers(multiToProceed.getFirstKey().getFirstNumber(), multiToProceed.getFirstKey().getSecondNumber(), tempComFirst.getFirstNumber()).getNumbers()
                                    , new CombinationNumbers(multiToProceed.getSecondKey().getFirstNumber(), multiToProceed.getSecondKey().getSecondNumber(), tempComFirst.getSecondNumber()).getNumbers()
                                    , new CombinationNumbers(multiToProceed.getThirdKey().getFirstNumber(), multiToProceed.getThirdKey().getSecondNumber(), tempComFirst.getThirdNumber()).getNumbers()).getSorted();
                            addMulti(mapMulti, tempMulti);
                        }
                    }
                }
            }
        }
        synchronized (mapMultiToReturn) {
            mapMultiToReturn.putAll(mapMulti);
        }
    }

    private void addMulti(TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti, MultiCombinationNumber multi) {
        int appeared = checkAppeared(multi);
        if (appeared >= rangeFrom && appeared <= rangeTo)
            if (mapMulti.containsKey(appeared)) {
                mapMulti.get(appeared).add(multi);
            } else {
                Set<MultiCombinationNumber> tempSet = new TreeSet<>();
                tempSet.add(multi);
                mapMulti.put(appeared, tempSet);
            }
    }

    private int checkAppeared(MultiCombinationNumber multi) {
        Set<Integer> appeared = new TreeSet<>();
        Integer[][] complexNumber = multi.getComplexNumber();

        for (Integer first : complexNumber[0]) {
            for (Integer second : complexNumber[1]) {
                for (Integer third : complexNumber[2]) {
                    CombinationNumbers temComb = new CombinationNumbers(first, second, third);
                    if (byCombination.containsKey(temComb)) {
                        appeared.addAll(byCombination.get(temComb));
                    }
                }
            }
        }
        return appeared.size();
    }

    private List<Integer> getNumbersForOne(Integer first, Integer second, Integer exclude) {
        List<Integer> thirdNumbers = new ArrayList<>();
        combinationNumbers.stream()
                .filter((x) -> x.containsNumber(first))
                .filter((x) -> x.containsNumber(second))
                .filter((x) -> !x.containsNumber(exclude))
                .map(CombinationNumbers::getNumbers)
                .map((x) -> Arrays.stream(x).filter((y) -> !y.equals(first) && !y.equals(second)).toArray(Integer[]::new))
                .forEach(x -> {
                    if (x.length > 0) {
                        thirdNumbers.add(x[0]);
                    }
                });
        return thirdNumbers;
    }

    private List<Integer> getNumbersForTwo(
            Integer first, Integer second
            , Integer secondOne, Integer secondTwo
            , Integer firstExclude, Integer secondExclude) {
        List<Integer> thirdNumbers = new ArrayList<>();
        combinationNumbers.stream()
                .filter((x) -> x.containsNumber(first) || x.containsNumber(second))
                .filter((x) -> x.containsNumber(secondOne) || x.containsNumber(secondTwo))
                .filter((x) -> !x.containsNumber(firstExclude))
                .filter((x) -> !x.containsNumber(secondExclude))
                .map(CombinationNumbers::getNumbers)
                .map((x) -> Arrays.stream(x).filter((y) -> !y.equals(first)
                        && !y.equals(second)
                        && !y.equals(secondOne)
                        && !y.equals(secondTwo)).toArray(Integer[]::new))
                .forEach(x -> {
                    if (x.length > 0) {
                        thirdNumbers.add(x[0]);
                    }
                });
        return thirdNumbers;
    }
}
