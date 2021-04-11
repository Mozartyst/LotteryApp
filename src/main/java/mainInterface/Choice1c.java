package mainInterface;

import entity.OneDraw;
import printers.ThreesChecker;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice1c {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner){
        System.out.println("Enter first number");
        int first = scanner.nextInt();
        System.out.println("Enter second number");
        int second = scanner.nextInt();
        System.out.println("Enter third number");
        int third = scanner.nextInt();
        new ThreesChecker().check(lotteryNumbers, first, second, third);
    }
}
