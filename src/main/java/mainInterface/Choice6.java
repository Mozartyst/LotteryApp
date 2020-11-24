package mainInterface;

import dataSupport.FileService;
import creators.MultiCombinationReducer;
import entity.MultiCombinationNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class Choice6 {
    public void run(Properties properties) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationNumber> afterMultiCombinationKey = FileService.loadObject(properties.getProperty("afterMulti"));
        try {
            new MultiCombinationReducer(afterMultiCombinationKey, properties).reduceMultiFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
