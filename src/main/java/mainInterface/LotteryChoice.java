package mainInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class LotteryChoice {
    public Properties run(Scanner scanner) throws IOException {
        Properties properties = new Properties();
        System.out.println("Select lottery:");
        System.out.println("1 - IrishLotto");
        System.out.println("2 - EuroMillion");
        System.out.println("3 - PolishLotto");
        int number = scanner.nextInt();
        if (number == 1){
            properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        }else if (number == 2){
            properties.load(new FileInputStream("src/main/resources/EuroLotto"));
        }else if (number == 3){
            properties.load(new FileInputStream("src/main/resources/PolishLotto"));
        }else {
            System.out.println("Zły wybór.");
        }
        return properties;
    }
}
