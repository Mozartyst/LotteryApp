package mainInterface;

import dataSupport.FileService;
import lottoPropositions.Proposition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice7 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        System.out.println(new Proposition(index).forMultiCombination(properties));
        if (index != 0) {
            ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
            System.out.println(lotteryNumbers.get(index - 1));
        }
    }
}