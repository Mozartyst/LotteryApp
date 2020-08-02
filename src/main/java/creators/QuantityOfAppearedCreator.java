package creators;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class QuantityOfAppearedCreator {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers; //FullIrishLottery
    private final Properties properties;
    private final TreeMap<Integer, TreeMap<Integer, Integer>> quantityOfAppearedNumbers = new TreeMap<>();

    public QuantityOfAppearedCreator(ArrayList<ArrayList<Integer>> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void create() {
        for (int i = lotteryNumbers.size() - 1; i >= 0; i--) {
            TreeMap<Integer, Integer> numbers = new TreeMap<>();
            if (i < lotteryNumbers.size() - 1) {
                TreeMap<Integer, Integer> integerIntegerTreeMap = quantityOfAppearedNumbers.get(i+1);
                integerIntegerTreeMap.forEach(numbers::put);
            }
            ArrayList<Integer> weekNumbers = lotteryNumbers.get(i);
            for (Integer number : weekNumbers) {
                if (numbers.containsKey(number)) {
                    numbers.put(number, numbers.get(number) + 1);
                } else {
                    numbers.put(number, 1);
                }
            }
            quantityOfAppearedNumbers.put(i,numbers);
        }
        try {
            FileService.saveObject(quantityOfAppearedNumbers, properties.getProperty("quantityOfAppeared"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
