package creators;

import entity.CombinationNumbers;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class AllThreesCreator {
    public Set<CombinationNumbers> get(Properties properties) {
        Set<CombinationNumbers> combinationNumbers = new TreeSet<>();
        for (int i = 1; i < Integer.parseInt(properties.getProperty("range")); i++) {
            for (int j = i + 1; j < Integer.parseInt(properties.getProperty("range")); j++) {
                for (int k = j + 1; k < Integer.parseInt(properties.getProperty("range")); k++) {
                    combinationNumbers.add(new CombinationNumbers(i, j, k));
                }
            }
        }
        return combinationNumbers;
    }
}
