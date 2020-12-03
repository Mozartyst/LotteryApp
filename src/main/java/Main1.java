
import dataSupport.FileService;
import entity.CombinationNumbers;
import threeHunter.ThreesCreator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        Set<CombinationNumbers> integerArrayListTreeMap = new ThreesCreator(FileService.loadObject(properties.getProperty("lotteryNumbers"))).get();
        CombinationNumbers combinationNumbers = new CombinationNumbers(2, 9, 42);
        if (integerArrayListTreeMap.contains(combinationNumbers)) {
        for (CombinationNumbers com : integerArrayListTreeMap) {
            if (com.equals(combinationNumbers)) {
//            if (com.getIndexesWhereAppeared().size() > 4) {
                System.out.println(com + " " + com.getIndexesWhereAppeared().size());
                }
            }
        }
    }
}
