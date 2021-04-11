package mainInterface;

import entity.OneDraw;
import lottoPropositions.NumbersFromFewLastDraws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice2 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = (lotteryNumbers.size() - 1) - scanner.nextInt();

    }
}
