import dataSupport.FileService;
import entity.MultiCombinationNumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/EuroLotto"));
        ArrayList<MultiCombinationNumber> multi = FileService.loadObject(properties.getProperty("afterMulti"));
        for (MultiCombinationNumber m:multi) {
            System.out.println(m);
        }

    }
}
