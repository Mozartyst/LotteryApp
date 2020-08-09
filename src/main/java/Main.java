import update.LotteryUpdate;
import mainInterface.LotteryChoice;
import mainInterface.MainMenu;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        new LotteryUpdate().run();

        Properties properties = new LotteryChoice().run(scanner);
        new MainMenu().showMenu(scanner, properties);

    }
}

