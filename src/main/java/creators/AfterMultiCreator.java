package creators;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class AfterMultiCreator {

    public void run(ArrayList<OneDraw> lotteryNumbers
            , Properties properties
            , Set<MultiCombinationNumber> multiCombinationSet
            , Integer indexFrom
            , Integer indexTo) throws IOException, ClassNotFoundException {
        Map<Integer, Set<CombinationNumbers>> combinationMapByIndexes = new CombinationMapByIndexes().create(lotteryNumbers);
        ThreadGroup threadGroup = new ThreadGroup("MultiThrees");
        int percent = 0;
        for (int i = indexFrom; i < indexTo; i++) {
            Thread thread = new Thread(threadGroup
                    , new MultiCombinationCreator(multiCombinationSet, combinationMapByIndexes, i)
                    , i + "");
            thread.start();
            while (threadGroup.activeCount() > Runtime.getRuntime().availableProcessors()) {
                try {
                    if (i * 100 / indexTo > percent) {
                        percent = i * 100 / indexTo;
                        System.out.println(properties.getProperty("name") + " " + percent + "%");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (threadGroup.activeCount() > 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

