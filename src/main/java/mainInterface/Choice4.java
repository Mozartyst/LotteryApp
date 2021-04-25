package mainInterface;

import algorithm.AlgorithmCreator;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class Choice4 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        Set<MultiCombinationNumber> reduced = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
        TreeMap<Integer, Set<Integer>> gaps = FileService.loadObject(properties.getProperty("gaps"));
        new AlgorithmCreator(lotteryNumbers
                , reduced
                , listOfNumbers
                , Integer.parseInt(properties.getProperty("lastIndex"))
                , properties
                , gaps).run();

    }
}
