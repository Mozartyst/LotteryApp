package mainInterface;

import creators.*;
import dataSupport.FileService;
import downloader.LotteryDownloader;
import entity.MultiCombinationKeys;
import entity.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice4 {

    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbersForAlgorithm = FileService.loadObject(properties.getProperty("numbersForAlgorithm"));
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));

        System.out.println("Select number of creator: ");
        System.out.println("1 - Numbers and Dependency Creator");
        System.out.println("2 - Duets Creator");
        System.out.println("3 - Algorithm Creator");
        System.out.println("4 - Combination Creator");
        System.out.println("5 - MultiCombination Creator");
        System.out.println("6 - NAN Creator");
        System.out.println("7 - NAP Creator");
        System.out.println("8 - NAT Creator");
        System.out.println("9 - Last Year Lottery Numbers Creator");
        int creatorChoice = scanner.nextInt();
        if (creatorChoice == 1) {
            try {
                new NumberCreator(lotteryNumbers, properties).createNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
                new DependenciesCreator(lotteryNumbers, listOfNumbers, properties).createDependencies();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (creatorChoice == 2) {
            try {
                TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
                new DuetCreator(lotteryNumbers, listOfNumbers, properties).createDuets();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (creatorChoice == 3) {
            TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
            new AlgorithmCreator(lotteryNumbersForAlgorithm, listOfNumbers, properties).createAlgorithm();
        } else if (creatorChoice == 4) {
            try {
                new CombinationCreator(lotteryNumbersForAlgorithm, properties).createAllCombinationNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (creatorChoice == 5) {
            ArrayList<MultiCombinationKeys> afterMultiCombinationKey = new ArrayList<>();
            Thread thread1 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 1, 120, properties));
            Thread thread2 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 121, 240, properties));
            Thread thread3 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 241, 360, properties));
            Thread thread4 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 361, lotteryNumbersForAlgorithm.size() - 4, properties));
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
        } else if (creatorChoice == 6) {
            new NANCreator(lotteryNumbersForAlgorithm, properties).createNAN();
        } else if (creatorChoice == 7) {
            new NAPCreator(lotteryNumbers, properties).createNAP();
        } else if (creatorChoice == 8) {
            new NATCreator(lotteryNumbersForAlgorithm, properties).createNAT();
        } else if (creatorChoice == 9) {
            ArrayList<ArrayList<Integer>> lastYear = new LotteryDownloader().getNumbers(2020, 2020, properties);
            FileService.saveObject(lastYear, properties.getProperty("lastYearNumbers"));
        }
    }
}
