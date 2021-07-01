package mainInterface;

import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;
import printers.Dependency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Choice1j {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        Map<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        System.out.println("Write index: ");
        int index = Integer.parseInt(properties.getProperty("lastIndex"));
        new Dependency().print(lotteryNumbers, listOfNumbers, index);
    }
}
