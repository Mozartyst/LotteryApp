package mainInterface;

import dataSupport.FileService;
import entity.OneDraw;
import lottoPropositions.NumbersFromFewLastDraws;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice9 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        System.out.println("Input range:");
        int range = scanner.nextInt();
        System.out.println("Input index:");
        int index = scanner.nextInt();
        System.out.println(new NumbersFromFewLastDraws().get(lotteryNumbers, range, index));
        if (index > 0) {
            System.out.println(lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawNumbers());
        }
    }
}
