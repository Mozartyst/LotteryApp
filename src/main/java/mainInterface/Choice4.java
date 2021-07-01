package mainInterface;

import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class Choice4 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));

    }
}
