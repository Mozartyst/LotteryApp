package mainInterface;

import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class MainMenu {
    public void showMenu(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Select number:");
        System.out.println("1 - ");
        System.out.println("2 - ");
        System.out.println("3 - Printers");
        System.out.println("4 - ");
        System.out.println("5 - ");
        System.out.println("6 - MultiThreesCreator");
        System.out.println("7 - ");
        System.out.println("8 - Tests");
        System.out.println("9 - LastDrawsThreesPrinter");
        System.out.println("0 - QUIT");
        int number = scanner.nextInt();
        if (number == 1) {
            new Choice1().run(lotteryNumbers, scanner, properties);
        } else if (number == 2) {
            new Choice2().run(lotteryNumbers, scanner, properties);
        } else if (number == 3) {
            new Choice3().run(lotteryNumbers, scanner);
        } else if (number == 4) {
            new Choice4().run(lotteryNumbers, scanner, properties);
        } else if (number == 5) {
            new Choice5().run(lotteryNumbers, scanner, properties);
        } else if (number == 6) {
            new Choice6().run(lotteryNumbers, scanner, properties);
        } else if (number == 7) {
            new Choice7().run(scanner, properties);
        } else if (number == 8) {
            new Choice8().run(lotteryNumbers, scanner, properties);
        } else if (number == 9) {
            new Choice9().run(lotteryNumbers, scanner);
        } else if (number == 0) {
            System.exit(0);
        }
    }
}
