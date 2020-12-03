package threeHunter;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class MultiThreesCreator {

    public void create(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<MultiCombinationNumber> multiCombinationList = new ArrayList<>();
        Set<CombinationNumbers> combinationNumbers = new ThreesCreator(lotteryNumbers).get();
        ThreadGroup threadGroup = new ThreadGroup("MultiThrees");
        Set<CombinationNumbers> firstCom = new TreeSet<>();
        for (CombinationNumbers combination : combinationNumbers) {
            if (combination.getIndexesWhereAppeared().size()==4){
                firstCom.add(combination);
            }
        }
        new ThreesFinder(combinationNumbers, firstCom, multiCombinationList, threadGroup, properties);
        while (threadGroup.activeCount() > 0) {
            try {
                System.out.println(threadGroup.activeCount());
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FileService.saveObject(multiCombinationList, properties.getProperty("threes"));
    }
}
