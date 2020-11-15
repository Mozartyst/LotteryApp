import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationKeys;
import entity.OneDraw;
import threeHunter.ThreesChecker;
import threeHunter.ThreesCreator;
import threeHunter.ThreesFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject("IrishLottery/FullIrishDraws");
        ArrayList<MultiCombinationKeys> multiCombinationList = new ArrayList<>();
        TreeMap<CombinationNumbers, Integer> allThreesCombination = new ThreesCreator(FileService.loadObject("IrishLottery/FullIrishDraws")).get();
        Set<CombinationNumbers> combinationNumbers = allThreesCombination.keySet();
        ThreadGroup threadGroup = new ThreadGroup("Multi");
        new ThreesFinder(allThreesCombination, combinationNumbers, multiCombinationList, threadGroup);
        while (threadGroup.activeCount() > 0) {
            try {
                System.out.println(threadGroup.activeCount());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("***********************************************");
        for (MultiCombinationKeys m : multiCombinationList) {
            int i = new ThreesChecker().howManyAppeared(m, lotteryNumbers);
            if (i > 40) {
                System.out.println(m + " " + i);
            }
        }
    }
}
