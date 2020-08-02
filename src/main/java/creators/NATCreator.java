package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NATCreator {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private final Properties properties;
    private final TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterTriple = new TreeMap<>();

    public NATCreator(ArrayList<ArrayList<Integer>> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }


    public void createNAT() {
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);

            if (index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeeklyNumbers = lotteryNumbers.get(index + 1);
                for (Integer firstNumber : previousWeeklyNumbers) {
                    for (Integer secondNumber : previousWeeklyNumbers) {
                        if (firstNumber >= secondNumber)
                            continue;
                        for (Integer thirdNumber : previousWeeklyNumbers) {
                            if (secondNumber >= thirdNumber)
                                continue;
                            CombinationNumbers combinationNumbers = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            if (!listOfNumbersAfterTriple.containsKey(combinationNumbers)) {
                                TreeMap<Integer, Integer> numbersForNumber = new TreeMap<>();
                                putNumbersToList(weeklyNumbers, combinationNumbers, numbersForNumber);
                            } else {
                                TreeMap<Integer, Integer> numbersForNumber = listOfNumbersAfterTriple.get(combinationNumbers);
                                putNumbersToList(weeklyNumbers, combinationNumbers, numbersForNumber);
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


    private void putNumbersToList(ArrayList<Integer> weeklyNumbers, CombinationNumbers
            combinationNumbers, TreeMap<Integer, Integer> numbersForNumber) {
        for (Integer number : weeklyNumbers) {
            if (numbersForNumber.containsKey(number)) {
                numbersForNumber.replace(number, numbersForNumber.get(number) + 1);
            } else {
                numbersForNumber.put(number, 1);
            }
        }
        listOfNumbersAfterTriple.put(combinationNumbers, numbersForNumber);
    }
    private synchronized void saveMulti() throws IOException {
        FileService.saveObject(listOfNumbersAfterTriple, properties.getProperty("afterTriple"));
    }
}
