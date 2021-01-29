
import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import threeHunter.MultiThreesFinder;
import creators.ThreesCreatorFromDrawsHistory;
import threeHunter.ThreesLastDrawsFinder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        Set<CombinationNumbers> allCombinationNumbers = new ThreesCreatorFromDrawsHistory(lotteryNumbers).get();
        Set<CombinationNumbers> combinationNumbersForFirst = new ThreesLastDrawsFinder().get(lotteryNumbers, 6, 0);
        Set<CombinationNumbers> combinationNumbers = new ThreesLastDrawsFinder().get(lotteryNumbers, 10, 0);
        ArrayList<MultiCombinationNumber> multiCombinationList = new ArrayList<>();
        ThreadGroup threadGroup = new ThreadGroup("Multi");
        for (CombinationNumbers com : combinationNumbersForFirst) {
            new MultiThreesFinder(com
                    , combinationNumbers
                    , allCombinationNumbers
                    , multiCombinationList
                    , properties
                    , threadGroup);
            while (threadGroup.activeCount() > 3) {
                try {
                    System.out.println(threadGroup.activeCount());
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (threadGroup.activeCount() > 0) {
            try {
                System.out.println(threadGroup.activeCount());
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(multiCombinationList);
    }
}
