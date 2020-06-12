package creators;

import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class QuantityOfAppearedCreator {
    private ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
    private TreeMap<Integer, TreeMap<Integer, Integer>> quantityOfAppearedNumbers = new TreeMap<>();

    public QuantityOfAppearedCreator() throws IOException, ClassNotFoundException {
    }

    public void create() {
        for (int i = lotteryNumbers.size() - 1; i >= 0; i--) {
            TreeMap<Integer, Integer> numbers = new TreeMap<>();
            if (i < lotteryNumbers.size() - 1) {
                TreeMap<Integer, Integer> integerIntegerTreeMap = quantityOfAppearedNumbers.get(i+1);
                integerIntegerTreeMap.forEach((number, value) -> numbers.put(number, value));
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
            FileService.saveObject(quantityOfAppearedNumbers,"QuantityOfAppearedNumbers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
