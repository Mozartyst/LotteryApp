package mainInterface;

import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice1 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("1 - MultiThreesGapChecker");
        System.out.println("2 - EachWithEveryOne");
        System.out.println("3 - ThreesChecker");
        System.out.println("4 - ThreesChecker");
        System.out.println("5 - LastDrawsThreesPrinter");
        int choice = scanner.nextInt();
        if (choice == 1) {
            new Choice1a().run(lotteryNumbers, scanner);
        } else if (choice == 2) {
            new Choice1b().run(scanner);
        } else if (choice == 3) {
            new Choice1c().run(lotteryNumbers, scanner);
        } else if (choice == 4) {
            new Choice1c().run(lotteryNumbers, scanner);
        }else if (choice == 5) {
            new Choice1d().run(lotteryNumbers, scanner);
        }
    }
}
