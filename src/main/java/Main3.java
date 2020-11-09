import dataSupport.FileService;
import entity.MultiCombinationKeys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<MultiCombinationKeys> newMulti = new ArrayList<>();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<MultiCombinationKeys> multiCombinationKeys = FileService.loadObject(properties.getProperty("afterMulti"));
        for (MultiCombinationKeys m : multiCombinationKeys) {
            System.out.println(multiCombinationKeys.indexOf(m));
//            new Thread(() -> {
//                if (newMulti.contains(m)) {
//                    synchronized (newMulti) {
//                        newMulti.get(newMulti.indexOf(m)).addWhatNumbers(new ArrayList<>(m.getWhatNumbers().firstKey()));
//                    }
//                } else {
//                    synchronized (newMulti) {
//                        newMulti.add(m);
//                    }
//                }
//            });
        }
        System.out.println(newMulti);
    }
}
