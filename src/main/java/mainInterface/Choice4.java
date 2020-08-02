package mainInterface;

import creators.*;
import dataSupport.FileService;
import entity.CombinationNumbers;
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
            TreeMap<Integer, ArrayList<CombinationNumbers>> combinationNumbers = FileService.loadObject(properties.getProperty("combinationNumbers"));
            ArrayList<MultiCombinationKeys> afterMultiCombinationKey = new ArrayList<>();
            Thread thread1 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, combinationNumbers, 1, 100, afterMultiCombinationKey, properties));
            Thread thread2 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, combinationNumbers, 101, 200, afterMultiCombinationKey, properties));
            Thread thread3 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, combinationNumbers, 201, 300, afterMultiCombinationKey, properties));
            Thread thread4 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, combinationNumbers, 301, lotteryNumbersForAlgorithm.size() - 4, afterMultiCombinationKey, properties));
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
        }
    }
}
