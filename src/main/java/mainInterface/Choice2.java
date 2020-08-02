package mainInterface;

import algorithm.Algorithm;
import dataSupport.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice2 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Input index:");
        int index = scanner.nextInt();
        System.out.println(new Algorithm().getPropositionList(index,properties));
        if (index != 0) {
            ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
            System.out.println(lotteryNumbers.get(index - 1));
        }
    }
}
