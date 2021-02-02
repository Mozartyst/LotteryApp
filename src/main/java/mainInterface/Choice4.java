package mainInterface;

import entity.OneDraw;
import lottoPropositions.NumbersAfterMultiCombinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice4 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterMultiCombinations(lotteryNumbers).getProposition(index, properties);
        System.out.println(lotteryNumbersFile);
        if (index > 0) {
            System.out.println(lotteryNumbers.get(lotteryNumbers.size() - index));
        }
    }
}
