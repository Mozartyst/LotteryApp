package mainInterface;

import dataSupport.FileService;
import entity.OneDraw;
import lottoPropositions.NumbersAfterTriple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice5 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterTriple(lotteryNumbers).getPropositionNumbersAfterTriple(lotteryNumbers.size() - 1 - index);
        System.out.println(lotteryNumbersFile);
        if (index > 0) {
            System.out.println(lotteryNumbers.get(lotteryNumbers.size() - index - 1));
        }
    }
}
