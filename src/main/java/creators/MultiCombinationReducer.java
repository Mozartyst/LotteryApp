package creators;

import dataSupport.FileService;
import entity.MultiCombinationNumber;

import java.io.IOException;
import java.util.*;

public class MultiCombinationReducer {
    private final Set<MultiCombinationNumber> multiCombinationNumbers;
    private final Properties properties;

    public MultiCombinationReducer(Set<MultiCombinationNumber> multiCombinationNumbers, Properties properties) {
        this.multiCombinationNumbers = multiCombinationNumbers;
        this.properties = properties;
    }

    public void reduceMultiFile() throws IOException {
        multiCombinationNumbers.removeIf(multi -> multi.getIndexesWhereAppeared().size() < 4);
        FileService.saveObject(multiCombinationNumbers, properties.getProperty("reducedAfterMulti"));
    }
}
