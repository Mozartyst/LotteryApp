package update;

import algorithm.AlgorithmCreator1;
import creators.AfterMultiCreator;
import creators.MultiCombinationReducer;
import creators.NumberCreator;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UpdateNumbers {
    public void run(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        String path = properties.getProperty("path");
        Map<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        Set<MultiCombinationNumber> multiCombinationSet = FileService.loadObject(properties.getProperty("afterMulti"));
        Set<MultiCombinationNumber> reducedMultiCombinationSet = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
        Integer lastIndex = Integer.valueOf(properties.getProperty("lastIndex"));
        for (int index = lastIndex + 1; index <= lotteryNumbers.size(); index++) {
            new AlgorithmCreator1(lotteryNumbers, reducedMultiCombinationSet, listOfNumbers, index, properties).run();
            new NumberCreator(listOfNumbers, lotteryNumbers, index);
            new AfterMultiCreator().run(lotteryNumbers, properties, multiCombinationSet, index, (index + 1));
            properties.setProperty("lastIndex", String.valueOf(index));
        }
//        properties.store(new FileOutputStream(path), null);
//        FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
//        FileService.saveObject(multiCombinationSet, properties.getProperty("afterMulti"));
//        new MultiCombinationReducer(multiCombinationSet, properties).reduceMultiFile();
    }
}
