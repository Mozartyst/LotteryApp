package mainInterface;

import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import printers.MultiThreesGapChecker;
import printers.ThreesChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice7 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("1 - 3 x 3 Checker");
        System.out.println("2 - Threes Checker");
        System.out.println("3 - Checker");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
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
        } else if (choice == 2) {
            System.out.println("Enter first number");
            int first = scanner.nextInt();
            System.out.println("Enter second number");
            int second = scanner.nextInt();
            System.out.println("Enter third number");
            int third = scanner.nextInt();
            new ThreesChecker().check(properties, first, second, third);
        }
    }
}
