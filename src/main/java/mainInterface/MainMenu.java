package mainInterface;

import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class MainMenu {
    public void showMenu(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Select number:");
        System.out.println("1 - Printers");
        System.out.println("2 - Creators");
        System.out.println("3 - Tests");
        System.out.println("4 - ");
        System.out.println("5 - ");
        System.out.println("0 - QUIT");
        int number = 0;
        try {
            number = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Your choose isn't a number.");
        }
        if (number == 1) {
            new Choice1().run(lotteryNumbers, scanner, properties);
        } else if (number == 2) {
            new Choice2().run(lotteryNumbers, scanner, properties);
        } else if (number == 3) {
            new Choice3().run(lotteryNumbers, scanner, properties);
        } else if (number == 4) {
            new Choice4().run(lotteryNumbers, scanner, properties);
        } else if (number == 5) {
            new Choice5().run(lotteryNumbers, scanner, properties);
        } else if (number == 0) {
            System.exit(0);
        } else {
            System.out.println("Wrong choice.");
        }
    }
}
