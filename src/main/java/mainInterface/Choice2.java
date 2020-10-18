package mainInterface;

import algorithm.Algorithm;
import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Choice2 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lastYearNumbers"));
        System.out.println("Input index:");
        int index = scanner.nextInt();
        System.out.println(new Algorithm().getPropositionList(index, properties, lotteryNumbers, listOfNumbers));
        if (index != 0) {
            System.out.println(lotteryNumbers.get(index - 1));
        }
    }
}
