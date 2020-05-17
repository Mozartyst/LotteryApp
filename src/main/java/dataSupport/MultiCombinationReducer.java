package dataSupport;

import entity.MultiCombinationKeys;
import entity.ObjectForFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class MultiCombinationReducer {
    private final ArrayList<MultiCombinationKeys> afterMultiCombinationKey =
            (ArrayList<MultiCombinationKeys>) FileService.loadObject("AfterMultiCombinationNumbersNew").getObject();

    public void reduceMultiFile() throws IOException {
        for (MultiCombinationKeys multi : afterMultiCombinationKey) {
            TreeMap<Integer, Integer> whatNumbers = new TreeMap<>();
            whatNumbers.putAll(multi.getWhatNumbers());
            Set<Integer> integers = whatNumbers.keySet();
            for (Integer key : integers) {
                if (whatNumbers.get(key) == 1) {
                    multi.getWhatNumbers().remove(key);
                }
            }
        }
        afterMultiCombinationKey.removeIf(multiCombinationKeys -> multiCombinationKeys.getWhatNumbers().size() == 0);

        ObjectForFileService<ArrayList<MultiCombinationKeys>> objectForFileService = new ObjectForFileService<>();
        objectForFileService.setObject(afterMultiCombinationKey);
        FileService.saveObject(objectForFileService, "ReducedMulti");
    }
}