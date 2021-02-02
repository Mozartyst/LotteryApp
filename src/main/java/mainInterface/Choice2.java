package mainInterface;

import dataSupport.FileService;
import entity.OneDraw;
import lottoPropositions.NumbersFromFewLastDraws;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice2 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();
        System.out.println(new Proposition().forMultiCombination(lotteryNumbers,properties,index));
        System.out.println(new NumbersFromFewLastDraws().get(lotteryNumbers,3,index));
        if (index != lotteryNumbers.size() - 1) {
            System.out.println(lotteryNumbers.get(index + 1));
        }
    }
}
