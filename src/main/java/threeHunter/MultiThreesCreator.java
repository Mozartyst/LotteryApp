package threeHunter;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class MultiThreesCreator {

    public void create(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<MultiCombinationNumber> multiCombinationList = new ArrayList<>();
        Set<CombinationNumbers> combinationNumbers = new ThreesCreator(lotteryNumbers).get();
        ThreadGroup threadGroup = new ThreadGroup("MultiThrees");
        Set<CombinationNumbers> firstCom = new TreeSet<>();
        Set<CombinationNumbers> secondCom = new TreeSet<>();
        Set<CombinationNumbers> thirdCom = new TreeSet<>();
        Set<CombinationNumbers> fourthCom = new TreeSet<>();
        int counter = 0;
        for (CombinationNumbers combination : combinationNumbers) {
            counter++;
            if (counter <= 1000) {
                firstCom.add(combination);
            } else if (counter > 1000 && counter <= 2500) {
                secondCom.add(combination);
            } else if (counter > 2500 && counter <= 4000) {
                thirdCom.add(combination);
            } else {
                fourthCom.add(combination);
            }
        }
        new ThreesFinder(combinationNumbers, firstCom, multiCombinationList, threadGroup, properties);
        new ThreesFinder(combinationNumbers, secondCom, multiCombinationList, threadGroup, properties);
        new ThreesFinder(combinationNumbers, thirdCom, multiCombinationList, threadGroup, properties);
        new ThreesFinder(combinationNumbers, fourthCom, multiCombinationList, threadGroup, properties);
        while (threadGroup.activeCount() > 0) {
            try {
                System.out.println(threadGroup.activeCount());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FileService.saveObject(multiCombinationList,properties.getProperty("threes"));
    }
}
