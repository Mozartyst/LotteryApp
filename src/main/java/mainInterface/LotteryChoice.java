package mainInterface;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class LotteryChoice {
    public Properties run(Scanner scanner, Properties lotteriesProp) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        Properties properties = new Properties();
        int number = 0;
        int counter = 0;
        System.out.println("0 - EXIT");
        for (Object o : lotteriesProp.keySet()) {
            counter++;
            System.out.println(counter + " - " + o.toString().toUpperCase());
        }
        try {
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Your choose isn't a number.");
        }
        if (number == 0) {
            System.exit(0);
        }
        counter = 0;
        for (Object o : lotteriesProp.keySet()) {
            counter++;
            if (number == counter) {
                properties.load(new FileInputStream(lotteriesProp.getProperty(o.toString())));
            }
        }
        return properties;
    }
}
