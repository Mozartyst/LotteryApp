
import creators.multiThrees.MultiThreesAppeared;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import printers.MultiThreesGapChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        TreeMap<Integer, Set<MultiCombinationNumber>> mapMulti = FileService.loadObject(properties.getProperty("threes"));
        Set<MultiCombinationNumber> multi = new TreeSet<>();


        mapMulti.forEach((x, y) -> y.forEach((z) -> {
            new Thread(() -> {
                List<Integer> appearedList = new MultiThreesAppeared(z, lotteryNumbers).getAppearedList();
                if (appearedList.contains(lotteryNumbers.size() - 1)
                        || appearedList.contains(lotteryNumbers.size() - 2)
                        || appearedList.contains(lotteryNumbers.size() - 3)) {
                    new MultiThreesGapChecker().print(z, lotteryNumbers);
                }
            }).start();
        }));
    }
}
// Wybiera liczbę 1 potem dobiera do niej najlepszą do pary . Na bazie tej pary dobiera trzecią