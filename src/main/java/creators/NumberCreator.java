package creators;

import entity.*;
import dataSupport.FileService;
import entity.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NumberCreator {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Properties properties;
    private final TreeMap<Integer, Number> listOfNumbers = new TreeMap<>();
    private final ArrayList<CombinationNumbers> combinationNumbersArrayList;

    public NumberCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
        this.combinationNumbersArrayList = FileService.loadObject(properties.getProperty("combinationNumbers"));
    }

    public void createNumbers() throws IOException {
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
            Dependencies dependencies = new Dependencies();
            Number number = new Number(i);
            listOfNumbers.put(i, number);
            dependencies.setAfterNumbers(NumberAfterNumbers(i));
            dependencies.setAfterPairs(NumberAfterPairs(i));
            dependencies.setAfterTriple(NumberAfterTriple(i));
            listOfNumbers.get(i).setDependency(dependencies);
            listOfNumbers.get(i).setOccurred(valueOfAppeared(i));
        }

        FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
    }

    private TreeMap<Integer, Integer> NumberAfterNumbers(Integer forNumber) {
        TreeMap<Integer, Integer> afterNumbers = new TreeMap<>();
        for (OneDraw currentDraw : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(currentDraw);
            if (currentDraw.getDrawNumbers().contains(forNumber) && index > 0) {
                ArrayList<Integer> previousDraw = lotteryNumbers.get(index - 1).getDrawNumbers();
                for (Integer number : previousDraw) {
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
        for (OneDraw currentDraw : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(currentDraw);
            if (currentDraw.getDrawNumbers().contains(forNumber) && index > 0) {
                ArrayList<Integer> previousDraw = lotteryNumbers.get(index - 1).getDrawNumbers();
                for (Integer firstNumber : previousDraw) {
                    for (Integer secondNumber : previousDraw) {
                        if (firstNumber < secondNumber) {
                            CombinationNumbers keyPairs = new CombinationNumbers(firstNumber, secondNumber);
                            addCombination(afterPairs, keyPairs);
                        }
                    }
                }
            }
        }
        return afterPairs;
    }


    private TreeMap<CombinationNumbers, Occurrences> NumberAfterTriple(Integer forNumber) {
        TreeMap<CombinationNumbers, Occurrences> afterTriple = new TreeMap<>();
        for (OneDraw currentDraw : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(currentDraw);
            if (currentDraw.getDrawNumbers().contains(forNumber) && index > 0) {
                ArrayList<Integer> previousWeek = lotteryNumbers.get(index - 1).getDrawNumbers();
                for (Integer firstNumber : previousWeek) {
                    for (Integer secondNumber : previousWeek) {
                        for (Integer thirdNumber : previousWeek) {
                            if (firstNumber < secondNumber && secondNumber < thirdNumber) {
                                CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                                addCombination(afterTriple, keyTriple);
                            }
                        }
                    }
                }
            }
        }
        return afterTriple;
    }

    private void addCombination(TreeMap<CombinationNumbers, Occurrences> afterPairs, CombinationNumbers keyPairs) {
        if (afterPairs.containsKey(keyPairs)) {
            Occurrences occurrences = afterPairs.get(keyPairs);
            occurrences.setHit(occurrences.getHit() + 1);
            afterPairs.replace(keyPairs, occurrences);
        } else {
            if (combinationNumbersArrayList.contains(keyPairs)) {
                Integer occurrence = combinationNumbersArrayList.get(combinationNumbersArrayList.indexOf(keyPairs))
                        .getListOfIndexesWhereAppeared().size();
                Occurrences occurrences = new Occurrences(occurrence, 1);
                afterPairs.put(keyPairs, occurrences);
            }
        }
    }

    private Integer valueOfAppeared(int forNumber) {
        int value = 0;
        for (OneDraw currentDraw : lotteryNumbers) {
            if (currentDraw.getDrawNumbers().contains(forNumber)) {
                value += 1;
            }
        }
        return value;
    }

}
