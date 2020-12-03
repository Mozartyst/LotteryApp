import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<MultiCombinationNumber> multi = FileService.loadObject(properties.getProperty("afterMulti"));
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        System.out.println(listOfNumbers);
        for (MultiCombinationNumber m:multi) {
            if (m.getNumbersAfter().size()>10) {

                System.out.println(m);
            }
        }
    }
}
