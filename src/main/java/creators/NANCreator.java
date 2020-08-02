package creators;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NANCreator {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private TreeMap<Integer, TreeMap<Integer, Integer>> listOfNumbersAfterNumbers = new TreeMap<>();
    private final Properties properties;

    public NANCreator(ArrayList<ArrayList<Integer>> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void createNAN() {
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);

            if (index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> previousWeeklyNumbers = lotteryNumbers.get(index + 1);
                for (Integer previousNumber : previousWeeklyNumbers) {
                    if (!listOfNumbersAfterNumbers.containsKey(previousNumber)) {
                        TreeMap<Integer, Integer> numbersForNumber = new TreeMap<>();
                        putNumbersToList(weeklyNumbers, previousNumber, numbersForNumber);
                    } else {
                        TreeMap<Integer, Integer> numbersForNumber = listOfNumbersAfterNumbers.get(previousNumber);
                        putNumbersToList(weeklyNumbers, previousNumber, numbersForNumber);
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

    private void putNumbersToList(ArrayList<Integer> weeklyNumbers, Integer previousNumber, TreeMap<Integer, Integer> numbersForNumber) {
        for (Integer number : weeklyNumbers) {
            if (numbersForNumber.containsKey(number)) {
                numbersForNumber.replace(number, numbersForNumber.get(number) + 1);
            } else {
                numbersForNumber.put(number, 1);
            }

        }
        listOfNumbersAfterNumbers.put(previousNumber, numbersForNumber);
    }
    private synchronized void saveMulti() throws IOException {
        FileService.saveObject(listOfNumbersAfterNumbers, properties.getProperty("afterNumber"));
    }
}
