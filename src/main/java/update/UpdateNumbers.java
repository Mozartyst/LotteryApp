package update;

import algorithm.AlgorithmCreator;
import creators.AfterMultiCreator;
import creators.NumberCreator;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersFromGaps;

import java.io.IOException;
import java.util.*;

public class UpdateNumbers {
    public void run(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        String path = properties.getProperty("path");
        Map<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        Set<MultiCombinationNumber> multiCombinationSet = FileService.loadObject(properties.getProperty("afterMulti"));
        Set<MultiCombinationNumber> reducedMultiCombinationSet = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
        TreeMap<Integer, Set<Integer>> gaps = FileService.loadObject(properties.getProperty("gaps"));
        Integer lastIndex = Integer.valueOf(properties.getProperty("lastIndex"));
        for (int index = lastIndex; index < lotteryNumbers.size(); index++) {
            new AlgorithmCreator(lotteryNumbers, reducedMultiCombinationSet, listOfNumbers, index, properties, gaps).run();
            if (index < lotteryNumbers.size() - 1) {
                new NumberCreator(listOfNumbers, lotteryNumbers, index + 1);
                new AfterMultiCreator().run(lotteryNumbers, properties, multiCombinationSet, index + 1, (index + 2));
                properties.setProperty("lastIndex", String.valueOf(index + 1));
                gaps.put(index + 1, new NumbersFromGaps().get(lotteryNumbers, listOfNumbers, index + 1));
            }
        }
//        properties.store(new FileOutputStream(path), null);
//        FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
//        FileService.saveObject(multiCombinationSet, properties.getProperty("afterMulti"));
//        new MultiCombinationReducer(multiCombinationSet, properties).reduceMultiFile();
    }
}
