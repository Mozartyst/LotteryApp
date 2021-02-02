package mainInterface;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.OneDraw;
import lottoPropositions.NumbersAfterTriple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice5 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<CombinationNumbers, TreeMap<Integer, Integer>> listOfNumbersAfterTriple = FileService.loadObject(properties.getProperty("afterTriple"));
        System.out.println("Input index:");
        int index = scanner.nextInt();
        TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterTriple(lotteryNumbers, listOfNumbersAfterTriple ).getPropositionNumbersAfterTriple(lotteryNumbers.size() - 1 - index);
        System.out.println(lotteryNumbersFile);
        if (index > 0) {
            System.out.println(lotteryNumbers.get(lotteryNumbers.size() - index - 1));
        }
    }
}
