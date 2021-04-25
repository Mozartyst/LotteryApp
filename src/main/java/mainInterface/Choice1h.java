package mainInterface;

import entity.OneDraw;
import printers.PrintRepeated;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice1h {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner) {
        System.out.println("How many numbers: ");
        int numbers = scanner.nextInt();
        System.out.println("Repeated from: ");
        int range = scanner.nextInt();
        new PrintRepeated().print(lotteryNumbers, numbers, range);
    }
}
