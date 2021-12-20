package mainInterface;

import lottoPropositions.MultiProposition;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class Choice5 {
    public void run(ArrayList<OneDraw> lotteryNumbers, Scanner scanner, Properties properties) throws IOException, ClassNotFoundException {
        Map<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
//        Set<MultiCombinationNumber> multiCombinationSet = FileService.loadObject(properties.getProperty("afterMulti"));
        Set<MultiCombinationNumber> reducedMultiCombinationSet = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
        TreeMap<Integer, Set<Integer>> gaps = FileService.loadObject(properties.getProperty("gaps"));
        new MultiProposition(lotteryNumbers
                , reducedMultiCombinationSet
                , listOfNumbers
                , Integer.parseInt(properties.getProperty("lastIndex"))
                , properties
                , gaps).run();
    }
}
