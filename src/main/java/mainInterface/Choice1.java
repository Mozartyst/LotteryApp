package mainInterface;

import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice1 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        ArrayList<OneDraw> lotteryNumbersPrep = new ArrayList<>(lotteryNumbers);
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();
        for (int i = index; i < lotteryNumbers.size(); i++) {
            lotteryNumbersPrep.remove(i);
        }
//        System.out.println(new Algorithm().getPropositionList(index, properties, lotteryNumbersPrep, listOfNumbers));
        if (index != lotteryNumbers.size() - 1) {
            System.out.println(lotteryNumbers.get(index + 1));
        }
    }
}
