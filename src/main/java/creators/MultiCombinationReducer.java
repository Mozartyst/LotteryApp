package creators;

import dataSupport.FileService;
import entity.MultiCombinationNumber;

import java.io.IOException;
import java.util.*;

public class MultiCombinationReducer {
    private final ArrayList<MultiCombinationNumber> multiCombinationNumbers;
    private final Properties properties;

    public MultiCombinationReducer(ArrayList<MultiCombinationNumber> multiCombinationNumbers, Properties properties) {
        this.multiCombinationNumbers = multiCombinationNumbers;
        this.properties = properties;
    }

    public void reduceMultiFile() throws IOException {
        for (MultiCombinationNumber multi : multiCombinationNumbers) {
            Map<Integer, Integer> whatNumbers = new TreeMap<>(multi.getNumbersAfter());
            Set<Integer> integers = whatNumbers.keySet();
            for (Integer key : integers) {
                if (key != null) {
                    if (whatNumbers.get(key) == 1) {
                        multi.getNumbersAfter().remove(key);
                    }
                }
            }
        }
        multiCombinationNumbers.removeIf(multiCombinationNumber -> multiCombinationNumber.getNumbersAfter().size() == 0);

        Collections.sort(multiCombinationNumbers);
        FileService.saveObject(multiCombinationNumbers, properties.getProperty("afterMulti"));
    }
}
