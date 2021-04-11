import algorithm.AlgorithmCreator1;
import creators.AlgorithmCreator;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersAppearClose;
import support.Auxiliary;
import update.UpdateNumbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        Set<MultiCombinationNumber> reduced = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
//        new AlgorithmCreator1(lotteryNumbers, reduced, listOfNumbers, Integer.parseInt(properties.getProperty("lastIndex")) + 1, properties).run();
        new UpdateNumbers().run(lotteryNumbers, properties);
    }
}
