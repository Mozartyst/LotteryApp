package mainInterface;

import printers.EachWithEveryOne;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Choice1b {
    public void run(Scanner scanner) {
        Set<Integer> list = new HashSet<>();
        System.out.println("Input first number to first list:");
        list.add(scanner.nextInt());
        System.out.println("Input second number to first list:");
        list.add(scanner.nextInt());
        System.out.println("Input third number to first list:");
        list.add(scanner.nextInt());
        System.out.println("Input first number to second list:");
        list.add(scanner.nextInt());
        System.out.println("Input second number to second list:");
        list.add(scanner.nextInt());
        System.out.println("Input third number to second list:");
        list.add(scanner.nextInt());
        System.out.println("Input first number to third list:");
        list.add(scanner.nextInt());
        System.out.println("Input second number to third list:");
        list.add(scanner.nextInt());
        System.out.println("Input second number to third list:");
        list.add(scanner.nextInt());
        new EachWithEveryOne().printList(list);
    }
}
