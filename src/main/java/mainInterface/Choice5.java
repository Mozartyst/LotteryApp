package mainInterface;

import dataSupport.FileService;
import dataSupport.MultiCombinationReducer;
import entity.MultiCombinationKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Choice5 {
    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = FileService.loadObject(properties.getProperty("afterMulti"));
        try {
            new MultiCombinationReducer(afterMultiCombinationKey, properties).reduceMultiFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
