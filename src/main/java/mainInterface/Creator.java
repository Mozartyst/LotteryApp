package mainInterface;

import creators.*;
import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class Creator {
    private final Set<MultiCombinationNumber> multiCombinationNumbers = new TreeSet<>();
    private final ArrayList<MultiCombinationNumber> multiCombination = new ArrayList<>();

    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<CombinationNumbers> comList = new ArrayList<>();
        for (OneDraw weekNumbers : lotteryNumbers) {
            Set<CombinationNumbers> combinationNumbers = new CombinationCreator().getCombinationNumbers(weekNumbers.getDrawNumbers(), lotteryNumbers.indexOf(weekNumbers));
            for (CombinationNumbers com : combinationNumbers) {
                if (comList.contains(com)) {
                    for (Integer i : com.getIndexesWhereAppeared()) {
                        comList.get(comList.indexOf(com)).getIndexesWhereAppeared().add(i);
                    }
                } else {
                    comList.add(com);
                }
            }
        }
        ThreadGroup threadGroup = new ThreadGroup("MultiThrees");
        for (int i = 0; i < lotteryNumbers.size() - 4; i++) {
            Thread thread = new Thread(threadGroup
                    , new MultiCombinationCreator(multiCombinationNumbers, multiCombination, comList, lotteryNumbers, i)
                    , i + "");
            thread.start();
            while (threadGroup.activeCount() > 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadGroup.activeCount());
                System.out.println(multiCombinationNumbers.size());
            }
        }
        while (threadGroup.activeCount() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new MultiCombinationReducer(multiCombination, properties).reduceMultiFile();

        new DuetCreator(lotteryNumbers, properties).createDuets();

        try {
            new NumberCreator(lotteryNumbers, properties).createNumbers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        new AlgorithmCreator(lotteryNumbers, listOfNumbers, properties).createAlgorithm();
    }
}

