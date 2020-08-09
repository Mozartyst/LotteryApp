package mainInterface;

import support.EachWithEveryOne;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice6 {
    public void run(Scanner scanner){
        System.out.println("Input first number to first list:");
        int first = scanner.nextInt();
        System.out.println("Input second number to first list:");
        int second = scanner.nextInt();
        System.out.println("Input third number to first list:");
        int third = scanner.nextInt();
        System.out.println("Input first number to second list:");
        int fourth = scanner.nextInt();
        System.out.println("Input second number to second list:");
        int fifth = scanner.nextInt();
        System.out.println("Input third number to second list:");
        int sixth = scanner.nextInt();
        System.out.println("Input first number to third list:");
        int seventh = scanner.nextInt();
        System.out.println("Input second number to third list:");
        int eight = scanner.nextInt();
        System.out.println("Input second number to third list:");
        int ninth = scanner.nextInt();
        ArrayList<ArrayList<Integer>> arrayLists = new EachWithEveryOne(first, second, third, fourth, fifth, sixth, seventh, eight, ninth).getList();
        arrayLists.forEach((x) -> System.out.println(x));
    }
}
