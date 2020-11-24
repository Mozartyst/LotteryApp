
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
        properties.load(new FileInputStream("src/main/resources/EuroLotto"));
        Set<CombinationNumbers> integerArrayListTreeMap = new ThreesCreator(FileService.loadObject(properties.getProperty("lotteryNumbers"))).get();
//        CombinationNumbers combinationNumbers = new CombinationNumbers(10, 27, 49);
//        if (integerArrayListTreeMap.contains(combinationNumbers)) {
        for (CombinationNumbers com : integerArrayListTreeMap) {
//            if (com.equals(combinationNumbers)) {
            System.out.println(com + " " + com.getIndexesWhereAppeared().size());
//                }
//            }
        }
    }
}
