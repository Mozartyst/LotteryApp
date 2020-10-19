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
        ArrayList<OneDraw> temp = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> lastFiftyDraws = new ArrayList<>();
        for (int i = temp.size()-50; i < temp.size(); i++) {
            lastFiftyDraws.add(temp.get(i));
        }

        System.out.println("Input index:");
        int index = scanner.nextInt();
        System.out.println(new Algorithm().getPropositionList(index, properties, lastFiftyDraws, listOfNumbers));
        if (index != 0) {
            System.out.println(lastFiftyDraws.get(index - 1));
        }
    }
}
