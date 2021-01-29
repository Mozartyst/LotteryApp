import creators.AlgorithmCreator;
import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersAppearClose;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        System.out.println(lotteryNumbers.get(lotteryNumbers.size() - 2).getDrawNumbers());
        lotteryNumbers.remove(lotteryNumbers.size() - 1);
        lotteryNumbers.remove(lotteryNumbers.size() - 2);
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        OneDraw lastWeek = lotteryNumbers.get(lotteryNumbers.size() - 1);
        TreeMap<Integer, Integer> results = new TreeMap<>();
        for (Integer number : lastWeek.getDrawNumbers()) {
            TreeMap<Integer, Integer> mapWithNumbers = new NumbersAppearClose().getMapWithNumbers(lotteryNumbers, number);
            for (Integer a : mapWithNumbers.keySet()) {
                if (results.containsKey(a)) {
                    results.replace(a, results.get(a) + mapWithNumbers.get(a));
                } else {
                    results.put(a, mapWithNumbers.get(a));
                }
            }
        }
        System.out.println(results);

    }
}
