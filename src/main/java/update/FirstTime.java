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
    public void run(Properties lotteriesProp) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        for (Object o : lotteriesProp.keySet()) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(lotteriesProp.getProperty(o.toString())));
            String path = properties.getProperty("path");
            if (!FileService.isFile(properties.getProperty("lotteryNumbers"))) {
                if (o.toString().equals("irishLotto")) {
                    new DownloadIrish().getNumbers(properties, Integer.parseInt(properties.getProperty("dateFrom")), LocalDateTime.now().getYear());
                }
                if (o.toString().equals("euroLotto")) {
                    new DownloadEuro().getNumbers(properties, Integer.parseInt(properties.getProperty("dateFrom")), LocalDateTime.now().getYear());
                }
                if (o.toString().equals("polishLotto")) {
                    new DownloadPolish(properties);
                }
                if (o.toString().equals("australianLotto")) {
                    new DownloadAustralian().getNumbers(properties, Integer.parseInt(properties.getProperty("dateFrom")), LocalDateTime.now().getYear());
                }
            }
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
            afterMultiCreator(lotteryNumbers, properties);
            numbersCreator(lotteryNumbers, properties, path);
        }
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
