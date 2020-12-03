package mainInterface;

import dataSupport.FileService;
import entity.OneDraw;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice2 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();
        System.out.println(new Proposition(index).forMultiCombination(properties));
        if (index != lotteryNumbers.size() - 1) {
            System.out.println(lotteryNumbers.get(index + 1));
        }
    }
}
