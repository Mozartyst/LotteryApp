package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NAPCreator {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private final TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterPairs = new TreeMap<>();
    private final Properties properties;

    public NAPCreator(ArrayList<ArrayList<Integer>> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void createNAP() {
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);

            if (index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeeklyNumbers = lotteryNumbers.get(index + 1);
                for (Integer firstNumber : previousWeeklyNumbers) {
                    for (Integer secondNumber : previousWeeklyNumbers) {
                        if (!firstNumber.equals(secondNumber) && firstNumber < secondNumber) {
                            CombinationNumbers keyPairs = new CombinationNumbers(firstNumber, secondNumber);
                            if (!listOfNumbersAfterPairs.containsKey(keyPairs)) {
                                TreeMap<Integer, Integer> numbersForNumber = new TreeMap<>();
                                putNumbersToList(weeklyNumbers, keyPairs, numbersForNumber);
                            } else {
                                TreeMap<Integer, Integer> numbersForNumber = listOfNumbersAfterPairs.get(keyPairs);
                                putNumbersToList(weeklyNumbers, keyPairs, numbersForNumber);
                            }
                        }
                    }
                }
            }
        }
        try {
            saveMulti();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putNumbersToList(ArrayList<Integer> weeklyNumbers, CombinationNumbers keyPair, TreeMap<Integer, Integer> numbersForNumber) {
        for (Integer number : weeklyNumbers) {
            if (numbersForNumber.containsKey(number)) {
                numbersForNumber.replace(number, numbersForNumber.get(number) + 1);
            } else {
                numbersForNumber.put(number, 1);
            }
        }
        listOfNumbersAfterPairs.put(keyPair, numbersForNumber);
    }

    private synchronized void saveMulti() throws IOException {
        FileService.saveObject(listOfNumbersAfterPairs, properties.getProperty("afterPairs"));
    }
}
