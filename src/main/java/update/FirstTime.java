package update;

import creators.AfterMultiCreator;
import creators.MultiCombinationReducer;
import creators.NumberCreator;
import dataSupport.FileService;
import downloader.DownloadEuro;
import downloader.DownloadIrish;
import downloader.DownloadPolish;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FirstTime {
    public void run() throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        Properties irishProp = new Properties();
        irishProp.load(new FileInputStream("src/main/resources/IrishLotto"));
        Properties euroProp = new Properties();
        euroProp.load(new FileInputStream("src/main/resources/EuroLotto"));
        Properties polishProp = new Properties();
        polishProp.load(new FileInputStream("src/main/resources/PolishLotto"));

        String irishPath = irishProp.getProperty("path");
        String euroPath = euroProp.getProperty("path");
        String polishPath = polishProp.getProperty("path");

        if (!FileService.isFile(irishProp.getProperty("lotteryNumbers"))) {
            new DownloadIrish().getNumbers(irishProp, Integer.parseInt(irishProp.getProperty("from")), LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(euroProp.getProperty("lotteryNumbers"))) {
            new DownloadEuro().getNumbers(euroProp, Integer.parseInt(euroProp.getProperty("from")), LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(polishProp.getProperty("lotteryNumbers"))) {
            new DownloadPolish(polishProp);
        }

        ArrayList<OneDraw> irishLotteryNumbers = FileService.loadObject(irishProp.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> euroLotteryNumbers = FileService.loadObject(euroProp.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> polishLotteryNumbers = FileService.loadObject(polishProp.getProperty("lotteryNumbers"));

        afterMultiCreator(irishLotteryNumbers, irishProp);
        afterMultiCreator(euroLotteryNumbers, euroProp);
        afterMultiCreator(polishLotteryNumbers, polishProp);
        numbersCreator(irishLotteryNumbers, irishProp, irishPath);
        numbersCreator(euroLotteryNumbers, euroProp, euroPath);
        numbersCreator(polishLotteryNumbers, polishProp, polishPath);
    }

    private void afterMultiCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        if (!FileService.isFile(properties.getProperty("afterMulti"))) {
            Set<MultiCombinationNumber> multiCombinationSet = new HashSet<>();
            new AfterMultiCreator().run(lotteryNumbers, properties, multiCombinationSet, 3, lotteryNumbers.size() - 50);
            FileService.saveObject(multiCombinationSet, properties.getProperty("afterMulti"));
            new MultiCombinationReducer(multiCombinationSet, properties).reduceMultiFile();
        }
    }

    private void numbersCreator(ArrayList<OneDraw> lotteryNumbers
            , Properties properties
            , String path) throws IOException {
        if (!FileService.isFile(properties.getProperty("listOfNumbers"))) {
            Map<Integer, Number> listOfNumbers = new TreeMap<>();
            for (int i = 1; i < (lotteryNumbers.size() - 50); i++) {
                new NumberCreator(listOfNumbers, lotteryNumbers, i).run();
                properties.setProperty("lastIndex", String.valueOf(i));
            }
            properties.store(new FileOutputStream(path), null);
            FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
        }
    }
}
