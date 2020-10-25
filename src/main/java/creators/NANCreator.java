package creators;

import dataSupport.FileService;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NANCreator {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final TreeMap<Integer, TreeMap<Integer, Integer>> listOfNumbersAfterNumbers = new TreeMap<>();
    private final Properties properties;

    public NANCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void createNAN() {
        for (OneDraw weeklyNumbers : lotteryNumbers) {
            int index = lotteryNumbers.indexOf(weeklyNumbers);

            if (index > 0) {
                ArrayList<Integer> previousWeeklyNumbers = lotteryNumbers.get(index - 1).getDrawNumbers();
                for (Integer previousNumber : previousWeeklyNumbers) {
                    TreeMap<Integer, Integer> numbersForNumber;
                    if (!listOfNumbersAfterNumbers.containsKey(previousNumber)) {
                        numbersForNumber = new TreeMap<>();
                    } else {
                        numbersForNumber = listOfNumbersAfterNumbers.get(previousNumber);
                    }
                    putNumbersToList(weeklyNumbers.getDrawNumbers(), previousNumber, numbersForNumber);
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
