package mainInterface;

import entity.MultiCombinationNumber;
import entity.OneDraw;
import printers.MultiThreesGapChecker;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice3a {

    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner){
        System.out.println("Enter first number for first array");
        int first = scanner.nextInt();
        System.out.println("Enter second number for first array");
        int second = scanner.nextInt();
        System.out.println("Enter third number for first array");
        int third = scanner.nextInt();
        System.out.println("Enter first number for second array");
        int fourth = scanner.nextInt();
        System.out.println("Enter second number for second array");
        int fifth = scanner.nextInt();
        System.out.println("Enter third number for second array");
        int sixth = scanner.nextInt();
        System.out.println("Enter first number for third array");
        int seventh = scanner.nextInt();
        System.out.println("Enter second number for third array");
        int eight = scanner.nextInt();
        System.out.println("Enter third number for third array");
        int ninth = scanner.nextInt();
        new MultiThreesGapChecker().print(new MultiCombinationNumber(new Integer[]{first, second, third}
                        , new Integer[]{fourth, fifth, sixth}
                        , new Integer[]{seventh, eight, ninth})
                , lotteryNumbers);
    }
}
