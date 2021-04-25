package printers;

import dataSupport.FileService;
import entity.MultiCombinationNumber;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class MultiThreesPrinter {
    public void print(Properties properties) throws IOException, ClassNotFoundException {
        Set<MultiCombinationNumber> mapMulti = FileService.loadObject(properties.getProperty("threes"));
        for (MultiCombinationNumber m:mapMulti) {
            System.out.println(m);
        }
    }
}
