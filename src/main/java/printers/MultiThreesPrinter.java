package printers;

import creators.multiThrees.MultiThreesAppeared;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class MultiThreesPrinter {
    public void print(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        Set<MultiCombinationNumber> mapMulti = FileService.loadObject(properties.getProperty("threes"));
        for (MultiCombinationNumber m:mapMulti) {
            System.out.print(m);
            System.out.println(new MultiThreesAppeared(m,lotteryNumbers).getAppearedList().size());
        }
    }
}
