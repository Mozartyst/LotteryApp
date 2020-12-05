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

public class Choice1 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        ArrayList<OneDraw> temp = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        System.out.println("Input index:");
        int index = (temp.size() - 1) - scanner.nextInt();
        System.out.println(new Algorithm().getPropositionList(index, properties, temp, listOfNumbers));
        if (index != temp.size() - 1) {
            System.out.println(temp.get(index + 1));
        }
    }
}
