package mainInterface;

import entity.Number;
import entity.OneDraw;
import printers.OccurredWith;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Choice1k {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Map<Integer, Number> listOfNumbers) {
        System.out.println("For what number? : ");
        new OccurredWith().print(listOfNumbers, scanner.nextInt());
    }
}
