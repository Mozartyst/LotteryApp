package mainInterface;

import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import threeHunter.GapChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice7 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
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
        new GapChecker().print(new MultiCombinationNumber(new Integer[]{first, second, third}
                        , new Integer[]{fourth, fifth, sixth}
                        , new Integer[]{seventh, eight, ninth})
                , lotteryNumbers);
    }
}
