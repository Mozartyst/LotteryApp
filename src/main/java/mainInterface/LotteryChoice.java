package mainInterface;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class LotteryChoice {
    public Properties run(Scanner scanner) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        Properties properties = new Properties();
        System.out.println("Select lottery:");
        System.out.println("1 - IrishLotto");
        System.out.println("2 - EuroMillion");
        System.out.println("3 - PolishLotto");
        System.out.println("4 - AustralianLotto");
        System.out.println("5 - Quit");
        int number = 4;
        try {
            number = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Your choose isn't a number.");
        }
        if (number == 1){
            properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        }else if (number == 2){
            properties.load(new FileInputStream("src/main/resources/EuroLotto"));
        }else if (number == 3){
            properties.load(new FileInputStream("src/main/resources/PolishLotto"));
        }else if (number == 4){
            properties.load(new FileInputStream("src/main/resources/AustralianLotto"));
        }else if (number == 5){
            System.exit(0);
        }else {
            System.out.println("Wrong choice!!!");
            System.out.println("Default is choose IrishLotto");
            properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        }
        return properties;
    }
}
