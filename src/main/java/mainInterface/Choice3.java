package mainInterface;

import entity.OneDraw;
import testy.Testy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice3 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Select test:");
        System.out.println("1 - Random");
        System.out.println("2 - ");
        System.out.println("3 - ");
        System.out.println("4 - ");
        System.out.println("5 - ");
        System.out.println("6 - ");
        System.out.println("7 - ");
        System.out.println("8 - ");
        int choice = scanner.nextInt();
        ArrayList<OneDraw> lastFiftyDraws = new ArrayList<>();
        for (int i = lotteryNumbers.size()-50; i < lotteryNumbers.size(); i++) {
            lastFiftyDraws.add(lotteryNumbers.get(i));
        }
        Testy testy = new Testy(lastFiftyDraws, properties);
        if (choice == 1) {
            System.out.println("Input 6 numbers:");
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int random = scanner.nextInt();
                arrayList.add(random);
            }
            testy.skutecznoscLiczbRandomowych(arrayList);
        } else if (choice == 2) {
        } else if (choice == 3) {
        } else if (choice == 4) {
        } else if (choice == 5) {
        } else if (choice == 6) {
        } else if (choice == 7) {
        } else if (choice == 8) {
        }
    }
}
