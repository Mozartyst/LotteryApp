package dataSupport;

import entity.MultiCombinationKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class MultiCombinationReducer {
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject("AfterMultiCombinationNumbers");

    public MultiCombinationReducer() throws IOException, ClassNotFoundException {
    }

    public void reduceMultiFile() throws IOException {
        for (MultiCombinationKeys multi : afterMultiCombinationKey) {
            if (multi != null) {
                TreeMap<Integer, Integer> whatNumbers = new TreeMap<>(multi.getWhatNumbers());
                Set<Integer> integers = whatNumbers.keySet();
                for (Integer key : integers) {
                    if (whatNumbers.get(key) == 1) {
                        multi.getWhatNumbers().remove(key);
                    }
                }
            }
        }
        afterMultiCombinationKey.removeIf(Objects::isNull);
        afterMultiCombinationKey.removeIf(multiCombinationKeys -> multiCombinationKeys.getWhatNumbers().size() == 0);

        FileService.saveObject(afterMultiCombinationKey, "IrishLottery/ReducedMulti");
    }
}
