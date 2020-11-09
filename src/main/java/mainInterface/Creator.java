package mainInterface;

import creators.*;
import dataSupport.FileService;
import entity.MultiCombinationKeys;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.TreeMap;

public class Creator {
    private TreeMap<Integer, HashSet<MultiCombinationKeys>> multiCombinationMap = new TreeMap<>();
    private ArrayList<MultiCombinationKeys> multiCombinationList = new ArrayList<>();

    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> lotteryNumbersForAlgorithm = new ArrayList<>();
        for (int i = 0; i < lotteryNumbers.size() - 50; i++) {
            lotteryNumbersForAlgorithm.add(lotteryNumbers.get(i));
        }
        ThreadGroup threadGroup = new ThreadGroup("Multi");
        for (int i = 0; i < lotteryNumbersForAlgorithm.size() - 4; i++) {
            new MultiCombinationCreator(new CombinationCreator(lotteryNumbersForAlgorithm.get(i).getDrawNumbers(), i).getCombinationNumbers()
                    , new CombinationCreator(lotteryNumbersForAlgorithm.get(i + 1).getDrawNumbers(), i + 1).getCombinationNumbers()
                    , new CombinationCreator(lotteryNumbersForAlgorithm.get(i + 2).getDrawNumbers(), i + 2).getCombinationNumbers()
                    , i
                    , threadGroup
                    , multiCombinationMap);
        }
        while (threadGroup.activeCount() > 0) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadGroup.activeCount());
        }
        HashSet<MultiCombinationKeys> multiCombinationSet = new HashSet<>();
        multiCombinationMap.forEach((index, multiSet) -> {
            System.out.println(index);
            for (MultiCombinationKeys m : multiSet) {
                if (multiCombinationSet.contains(m)) {
                    if (m.getKeys().length == 1) {
                        multiCombinationList.get(multiCombinationList.indexOf(m)).addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 1).getDrawNumbers());
                    } else if (m.getKeys().length == 2) {
                        multiCombinationList.get(multiCombinationList.indexOf(m)).addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 2).getDrawNumbers());
                    } else if (m.getKeys().length == 3) {
                        multiCombinationList.get(multiCombinationList.indexOf(m)).addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 3).getDrawNumbers());
                    }

                } else {
                    if (m.getKeys().length == 1) {
                        m.addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 1).getDrawNumbers());
                    } else if (m.getKeys().length == 2) {
                        m.addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 2).getDrawNumbers());
                    } else if (m.getKeys().length == 3) {
                        m.addWhatNumbers(lotteryNumbersForAlgorithm.get(index + 3).getDrawNumbers());
                    }
                    multiCombinationList.add(m);
                    multiCombinationSet.add(m);
                }
            }
        });
        new MultiCombinationReducer(multiCombinationList,properties).reduceMultiFile();

        new DuetCreator(lotteryNumbers, properties).createDuets();

        try {
            new NumberCreator(lotteryNumbers, properties).createNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
//        new AlgorithmCreator(lotteryNumbersForAlgorithm, listOfNumbers, properties).createAlgorithm();

