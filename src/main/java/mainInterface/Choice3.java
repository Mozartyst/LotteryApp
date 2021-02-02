package mainInterface;

import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Choice3 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner) throws IOException, ClassNotFoundException {
        System.out.println("1 - MultiThreesGapChecker");
        System.out.println("2 - EachWithEveryOne");
        System.out.println("3 - ThreesChecker");
        System.out.println("4 - ThreesChecker");
        int choice = scanner.nextInt();
        if (choice == 1) {
            new Choice3a().run(lotteryNumbers, scanner);
        } else if (choice == 2) {
            new Choice3b().run(scanner);
        } else if (choice == 3) {
            new Choice3c().run(lotteryNumbers, scanner);
        } else if (choice == 4) {
            new Choice3c().run(lotteryNumbers, scanner);
        }

    }
}
