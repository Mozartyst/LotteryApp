package update;

import creators.AfterMultiCreator;
import creators.MultiCombinationReducer;
import creators.NumberCreator;
import creators.weightSystem.WeightCreator;
import dataSupport.FileService;
import downloader.DownloadAustralian;
import downloader.DownloadEuro;
import downloader.DownloadIrish;
import downloader.DownloadPolish;
import entity.MultiCombinationNumber;
import entity.Number;
import entity.OneDraw;
import lottoPropositions.NumbersFromGaps;
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
        Properties australianProp = new Properties();
        australianProp.load(new FileInputStream("src/main/resources/AustralianLotto"));

        String irishPath = irishProp.getProperty("path");
        String euroPath = euroProp.getProperty("path");
        String polishPath = polishProp.getProperty("path");
        String australianPath = australianProp.getProperty("path");

        if (!FileService.isFile(irishProp.getProperty("lotteryNumbers"))) {
            new DownloadIrish().getNumbers(irishProp, Integer.parseInt(irishProp.getProperty("dateFrom")), LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(euroProp.getProperty("lotteryNumbers"))) {
            new DownloadEuro().getNumbers(euroProp, Integer.parseInt(euroProp.getProperty("dateFrom")), LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(polishProp.getProperty("lotteryNumbers"))) {
            new DownloadPolish(polishProp);
        }
        if (!FileService.isFile(australianProp.getProperty("lotteryNumbers"))) {
            new DownloadAustralian().getNumbers(australianProp, Integer.parseInt(australianProp.getProperty("dateFrom")), LocalDateTime.now().getYear());
        }

        ArrayList<OneDraw> irishLotteryNumbers = FileService.loadObject(irishProp.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> euroLotteryNumbers = FileService.loadObject(euroProp.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> polishLotteryNumbers = FileService.loadObject(polishProp.getProperty("lotteryNumbers"));
        ArrayList<OneDraw> australianLotteryNumbers = FileService.loadObject(australianProp.getProperty("lotteryNumbers"));

        afterMultiCreator(irishLotteryNumbers, irishProp);
        afterMultiCreator(euroLotteryNumbers, euroProp);
        afterMultiCreator(polishLotteryNumbers, polishProp);
        afterMultiCreator(australianLotteryNumbers, australianProp);
        numbersCreator(irishLotteryNumbers, irishProp, irishPath);
        numbersCreator(euroLotteryNumbers, euroProp, euroPath);
        numbersCreator(polishLotteryNumbers, polishProp, polishPath);
        numbersCreator(australianLotteryNumbers, australianProp, australianPath);
    }

    private void afterMultiCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        if (!FileService.isFile(properties.getProperty("afterMulti"))) {
            Set<MultiCombinationNumber> multiCombinationSet = new HashSet<>();
            new AfterMultiCreator().run(lotteryNumbers, properties, multiCombinationSet, 3, lotteryNumbers.size() - 20);
            FileService.saveObject(multiCombinationSet, properties.getProperty("afterMulti"));
            new MultiCombinationReducer(multiCombinationSet, properties).reduceMultiFile();
        }
    }

    private void numbersCreator(ArrayList<OneDraw> lotteryNumbers
            , Properties properties
            , String path) throws IOException {
        if (!FileService.isFile(properties.getProperty("listOfNumbers"))) {
            Map<Integer, Number> listOfNumbers = new TreeMap<>();
            Map<Integer, Set<Integer>> numbersFromGaps = new TreeMap<>();
            for (int index = 1; index < (lotteryNumbers.size() - 20); index++) {
                new NumberCreator(listOfNumbers, lotteryNumbers, index).run();
                properties.setProperty("lastIndex", String.valueOf(index));
                if (index > lotteryNumbers.size() - 150) {
                    new WeightCreator(lotteryNumbers, listOfNumbers, index).run();
                }
                if (index >= lotteryNumbers.size() - 28) {
                    Set<Integer> integers = new NumbersFromGaps().get(lotteryNumbers, listOfNumbers, index);
                    numbersFromGaps.put(index, integers);
                }
            }
            properties.store(new FileOutputStream(path), null);
            FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
            FileService.saveObject(numbersFromGaps, properties.getProperty("gaps"));
        }
    }
}
