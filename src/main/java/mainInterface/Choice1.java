package mainInterface;

import dataSupport.FileService;
import downloader.LotteryDownloader;

import java.io.IOException;
import java.util.Properties;

public class Choice1 {
    public void run(Properties properties) {
        try {
            FileService.saveObject(new LotteryDownloader().getNumbers(
                    Integer.parseInt(properties.getProperty("yearFrom"))
                    , Integer.parseInt(properties.getProperty("yearTo"))
                    , properties)
                    , properties.getProperty("numbersForAlgorithm"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
