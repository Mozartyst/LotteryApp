package checker;

import dataSupport.FileService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FilesChecker {
    public boolean areFiles(Properties lotteriesProp) throws IOException {
        boolean areFiles = true;
        for (Object o : lotteriesProp.keySet()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(lotteriesProp.getProperty(o.toString())));
            if (!FileService.isFile(properties.getProperty("lotteryNumbers"))
                    || !FileService.isFile(properties.getProperty("afterMulti"))
                    || !FileService.isFile(properties.getProperty("listOfNumbers"))) {
                areFiles = false;
                break;
            }
        }
        return areFiles;
    }
}
