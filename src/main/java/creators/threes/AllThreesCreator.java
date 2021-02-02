package creators.threes;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class AllThreesCreator {
    public Set<CombinationNumbers> get(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        Set<CombinationNumbers> combinationNumbers = new TreeSet<>();
        for (int i = 1; i < Integer.parseInt(properties.getProperty("range")); i++) {
            for (int j = i + 1; j < Integer.parseInt(properties.getProperty("range")); j++) {
                for (int k = j + 1; k < Integer.parseInt(properties.getProperty("range")); k++) {
                    CombinationNumbers com = new CombinationNumbers(i, j, k);
                    for (OneDraw oneDraw : lotteryNumbers) {
                        ArrayList<Integer> drawNumbers = oneDraw.getDrawNumbers();
                        if (drawNumbers.contains(i) && drawNumbers.contains(j) && drawNumbers.contains(k)) {
                            com.addIndexToList(lotteryNumbers.indexOf(oneDraw));
                        }
                    }
                    combinationNumbers.add(com);
                }
            }
        }
        return combinationNumbers;
    }
}
