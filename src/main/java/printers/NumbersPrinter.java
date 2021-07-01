package printers;

import dataSupport.FileService;
import entity.Number;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class NumbersPrinter {
    public void print(Properties properties) throws IOException, ClassNotFoundException {
        Map<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        listOfNumbers.forEach((x,y)-> System.out.println(y));
    }
}
