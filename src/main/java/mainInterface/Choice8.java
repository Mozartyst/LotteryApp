package mainInterface;

import dataSupport.FileService;
import entity.OneDraw;
import testy.Testy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Choice8 {
    public void run(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        System.out.println("Select test:");
        System.out.println("1 - NAN");
        System.out.println("2 - NAP");
        System.out.println("3 - NAT");
        System.out.println("4 - Duet");
        System.out.println("5 - Slant");
        System.out.println("6 - Algorithm");
        System.out.println("7 - ");
        System.out.println("8 - Random");
        System.out.println("9 - ");
        System.out.println("10 -");
        System.out.println("11 - NAMultiCombination");
        int choice = scanner.nextInt();
        ArrayList<OneDraw> lotteryNumber = FileService.loadObject(properties.getProperty("lastYearNumbers"));
        Testy testy = new Testy(lotteryNumber, properties);
        if (choice == 1) {
            testy.skutecznoscNumbersAfterNumber();
        } else if (choice == 2) {
            testy.skutecznoscNumbersAfterPairs();
        } else if (choice == 3) {
            testy.skutecznoscNumbersAfterTriple();
        } else if (choice == 4) {
            testy.skutecznoscDuet();
        } else if (choice == 5) {
            testy.skutecznoscSlant();
        } else if (choice == 6) {
            testy.skutecznoscAlgorithm();
        } else if (choice == 7) {
        } else if (choice == 8) {
            System.out.println("Input 6 numbers:");
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int random = scanner.nextInt();
                arrayList.add(random);
            }
            testy.skutecznoscLiczbRandomowych(arrayList);
        } else if (choice == 9) {
        } else if (choice == 10) {
        } else if (choice == 11) {
            testy.skutecznoscMultiCombination();
        }
    }
}
