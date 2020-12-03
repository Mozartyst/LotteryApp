package mainInterface;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class MainMenu {
    public void showMenu(Scanner scanner, Properties properties) throws IOException, ClassNotFoundException{
        System.out.println("Select number:");
        System.out.println("1 - Algorithm proposition");
        System.out.println("2 - Proposition");
        System.out.println("3 - EachWithEveryOne");
        System.out.println("4 - NAMultiCombination");
        System.out.println("5 - NAT");
        System.out.println("6 - MultiThreesCreator");
        System.out.println("7 - Check3X3");
        System.out.println("8 - Tests");

        int number = scanner.nextInt();
        if (number == 1) {
            new Choice1().run(scanner,properties);
        } else if (number == 2) {
            new Choice2().run(scanner,properties);
        } else if (number == 3) {
            new Choice3().run(scanner);
        } else if (number == 4) {
            new Choice4().run(scanner,properties);
        } else if (number == 5) {
            new Choice5().run(scanner,properties);
        } else if (number == 6) {
            new Choice6().run(properties);
        } else if (number == 7) {
            new Choice7().run(scanner,properties);
        } else if (number == 8) {
            new Choice8().run(scanner,properties);
        }
    }
}
