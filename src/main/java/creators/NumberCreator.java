package creators;

import entity.*;
import dataSupport.FileService;
import entity.Number;
import support.Auxiliary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class NumberCreator {
    private ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
    private TreeMap<Integer, Number> listOfNumbers = new TreeMap<>();

    public void createNumbers() throws IOException {
        for (int i = 1; i < 48; i++) {
            Dependency dependency = new Dependency();
            Number number = new Number(i);
            listOfNumbers.put(i, number);
            dependency.setAfterNumbers(NumberAfterNumbers(i));
            dependency.setAfterPairs(NumberAfterPairs(i));
            dependency.setAfterTriple(NumberAfterTriple(i));
            listOfNumbers.get(i).setDependency(dependency);
            listOfNumbers.get(i).setOccurred(valueOfAppeared(i));
        }

        FileService.saveObject(listOfNumbers, "ListOfNumbers");
    }

    private TreeMap<Integer, Integer> NumberAfterNumbers(Integer forNumber) {
        TreeMap<Integer, Integer> afterNumbers = new TreeMap<>();
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);
            if (weeklyNumbers.contains(forNumber) && index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeek = lotteryNumbers.get(index + 1);
                for (Integer number : previousWeek) {
                    if (afterNumbers.containsKey(number)) {
                        afterNumbers.replace(number, afterNumbers.get(number) + 1);
                    } else {
                        afterNumbers.put(number, 1);
                    }
                }
            }
        }
        return afterNumbers;
    }

    private TreeMap<CombinationNumbers, Occurrences> NumberAfterPairs(Integer forNumber) {
        TreeMap<CombinationNumbers, Occurrences> afterPairs = new TreeMap<>();
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);
            if (weeklyNumbers.contains(forNumber) && index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeek = lotteryNumbers.get(index + 1);
                for (Integer firstNumber : previousWeek) {
                    for (Integer secondNumber : previousWeek) {
                        if (!firstNumber.equals(secondNumber) && firstNumber < secondNumber) {
                            CombinationNumbers keyPairs = new CombinationNumbers(firstNumber, secondNumber);
                            if (afterPairs.containsKey(keyPairs)) {
                                Occurrences occurrences = afterPairs.get(keyPairs);
                                occurrences.setHit(occurrences.getHit() + 1);
                                afterPairs.replace(keyPairs, occurrences);
                            } else {
                                Integer occurrence = 0;
                                for (ArrayList<Integer> week : lotteryNumbers) {
                                    ArrayList<CombinationNumbers> combinationNumbers = Auxiliary.returnCombinationKeys(week);
                                    if (combinationNumbers.contains(keyPairs)) {
                                        occurrence = occurrence + 1;
                                    }
                                }
                                Occurrences occurrences = new Occurrences(occurrence, 1);
                                afterPairs.put(keyPairs, occurrences);
                            }
                        }
                    }
                }
            }
        }
        return afterPairs;
    }

    private TreeMap<CombinationNumbers, Occurrences> NumberAfterTriple(Integer forNumber) {
        TreeMap<CombinationNumbers, Occurrences> afterTriple = new TreeMap<>();
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);
            if (weeklyNumbers.contains(forNumber) && index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeek = lotteryNumbers.get(index + 1);
                for (Integer firstNumber : previousWeek) {
                    for (Integer secondNumber : previousWeek) {
                        if (firstNumber >= secondNumber)
                            continue;
                        for (Integer thirdNumber : previousWeek) {
                            if (secondNumber >= thirdNumber)
                                continue;
                            CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            if (afterTriple.containsKey(keyTriple)) {
                                Occurrences occurrences = afterTriple.get(keyTriple);
                                occurrences.setHit(occurrences.getHit() + 1);
                                afterTriple.replace(keyTriple, occurrences);
                            } else {
                                Integer occurrence = 0;
                                for (ArrayList<Integer> week : lotteryNumbers) {
                                    ArrayList<CombinationNumbers> combinationNumbers = Auxiliary.returnCombinationKeys(week);
                                    if (combinationNumbers.contains(keyTriple)) {
                                        occurrence = occurrence + 1;
                                    }
                                }
                                Occurrences occurrences = new Occurrences(occurrence, 1);
                                afterTriple.put(keyTriple, occurrences);
                            }
                        }
                    }
                }
            }
        }
        return afterTriple;
    }

    private Integer valueOfAppeared(int forNumber) {
        int value = 0;
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            if (weeklyNumbers.contains(forNumber)) {
                value += 1;
            }
        }
        return value;
    }

}
