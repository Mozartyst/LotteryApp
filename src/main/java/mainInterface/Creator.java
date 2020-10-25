package mainInterface;

import creators.*;
import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Creator {
    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> lotteryNumbersForAlgorithm = new ArrayList<>();
        for (int i = 0; i < lotteryNumbers.size() - 50; i++) {
            lotteryNumbersForAlgorithm.add(lotteryNumbers.get(i));
        }

        try {
            new CombinationCreator(lotteryNumbersForAlgorithm, properties).createAllCombinationNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new NANCreator(lotteryNumbersForAlgorithm, properties).createNAN();
        new NAPCreator(lotteryNumbers, properties).createNAP();
        new NATCreator(lotteryNumbersForAlgorithm, properties).createNAT();
        new DuetCreator(lotteryNumbers, properties).createDuets();

        try {
            new NumberCreator(lotteryNumbers, properties).createNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        new AlgorithmCreator(lotteryNumbersForAlgorithm, listOfNumbers, properties).createAlgorithm();
        try {
            new DependenciesCreator(lotteryNumbers, listOfNumbers, properties).createDependencies();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = new ArrayList<>();
//        Thread thread1 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 1, 120, properties));
//        Thread thread2 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 121, 240, properties));
//        Thread thread3 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 241, 360, properties));
//        Thread thread4 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 361, lotteryNumbersForAlgorithm.size() - 4, properties));
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

    }
}
