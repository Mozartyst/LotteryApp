package printers;

import creators.ThreesCreatorFromDrawsHistory;
import dataSupport.FileService;
import entity.CombinationNumbers;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class ThreesChecker {
    public void check(Properties properties, Integer first, Integer second, Integer third) throws IOException, ClassNotFoundException {
        Set<CombinationNumbers> integerArrayListTreeMap = new ThreesCreatorFromDrawsHistory(FileService.loadObject(properties.getProperty("lotteryNumbers"))).get();
        CombinationNumbers combinationNumbers = new CombinationNumbers(first, second, third);
        if (integerArrayListTreeMap.contains(combinationNumbers)) {
            for (CombinationNumbers com : integerArrayListTreeMap) {
                if (com.equals(combinationNumbers)) {
                    System.out.println(com + " existed " + com.getIndexesWhereAppeared().size());
                }
            }
        }else {
            System.out.println(combinationNumbers + " didn't exist.");
        }
    }
}
