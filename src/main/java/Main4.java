import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import threeHunter.GapChecker;
import threeHunter.MultiThreesChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        ArrayList<MultiCombinationNumber> listOfCombinations = FileService.loadObject(properties.getProperty("threes"));
        Collections.sort(listOfCombinations);
        listOfCombinations.forEach((m)->{
            int i = new MultiThreesChecker().howManyAppeared(m, lotteryNumbers);
            if (i == 39) {
                new GapChecker().print(m,lotteryNumbers);
            }
        });
    }
}
