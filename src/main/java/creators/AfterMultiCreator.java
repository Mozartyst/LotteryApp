package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.*;

public class AfterMultiCreator {
    private final Set<MultiCombinationNumber> multiCombinationNumbers = new TreeSet<>();
    private final ArrayList<MultiCombinationNumber> multiCombination = new ArrayList<>();

    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<CombinationNumbers> comList = new ArrayList<>();
        for (OneDraw weekNumbers : lotteryNumbers) {
            Set<CombinationNumbers> combinationsNumbers = new CombinationCreator().getCombinationNumbers(weekNumbers.getDrawNumbers(), lotteryNumbers.indexOf(weekNumbers));
            for (CombinationNumbers com : combinationsNumbers) {
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
            while (threadGroup.activeCount() > 7) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(threadGroup.activeCount());
//                System.out.println(multiCombinationNumbers.size());
            }
        }
        while (threadGroup.activeCount() > 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new MultiCombinationReducer(multiCombination, properties).reduceMultiFile();

//        new DuetCreator(lotteryNumbers, properties).createDuets();

//        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
//        new AlgorithmCreator(lotteryNumbers, listOfNumbers, properties).createAlgorithm();
    }
}

