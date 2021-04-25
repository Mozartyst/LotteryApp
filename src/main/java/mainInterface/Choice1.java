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
        System.out.println("4 - ThreesPrinter");
        System.out.println("5 - LastDrawsThreesPrinter");
        System.out.println("6 - AllDrawsPrinter");
        System.out.println("7 - AllDrawsGraph");
        System.out.println("8 - Print repeated");
        System.out.println("9 - ");
        System.out.println("10 - ");
        System.out.println("11 - ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            new Choice1a().run(lotteryNumbers, scanner);
        } else if (choice == 2) {
            new Choice1b().run(scanner);
        } else if (choice == 3) {
            new Choice1c().run(lotteryNumbers, scanner);
        } else if (choice == 4) {
            new Choice1e().run(lotteryNumbers, scanner, properties);
        }else if (choice == 5) {
            new Choice1d().run(lotteryNumbers, scanner);
        }else if (choice == 6) {
            new Choice1f().run(lotteryNumbers, scanner);
        }else if (choice == 7) {
            new Choice1g().run(lotteryNumbers, scanner, properties);
        }else if (choice == 8) {
            new Choice1h().run(lotteryNumbers, scanner);
        }else if (choice == 9) {
            new Choice1i().run(lotteryNumbers, scanner);
        }else if (choice == 10) {
            new Choice1j().run(lotteryNumbers, scanner);
        }else if (choice == 11) {
            new Choice1k().run(lotteryNumbers, scanner);
        }
    }
}
