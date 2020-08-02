package mainInterface;

import dataSupport.FileService;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice9 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterMultiCombinations(FileService.loadObject(properties.getProperty("lotteryNumbers"))).getProposition(index, properties);
        System.out.println(lotteryNumbersFile);
        if (index > 0) {
            ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
            System.out.println(lotteryNumbers.get(index - 1));
        }
    }
}
