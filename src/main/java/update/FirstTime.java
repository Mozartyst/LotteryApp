package update;

import creators.AfterMultiCreator;
import creators.AlgorithmCreator;
import creators.NumberCreator;
import dataSupport.FileService;
import downloader.DownloadEuro;
import downloader.DownloadIrish;
import downloader.DownloadPolish;
import entity.OneDraw;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class FirstTime {
    public void run() throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        Properties irishProp = new Properties();
        irishProp.load(new FileInputStream("src/main/resources/IrishLotto"));
        Properties euroProp = new Properties();
        euroProp.load(new FileInputStream("src/main/resources/EuroLotto"));
        Properties polishProp = new Properties();
        polishProp.load(new FileInputStream("src/main/resources/PolishLotto"));
        if (!FileService.isFile(irishProp.getProperty("lotteryNumbers"))) {
            new DownloadIrish().getNumbers(irishProp, 2015, LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(euroProp.getProperty("lotteryNumbers"))) {
            new DownloadEuro().getNumbers(euroProp, 2016, LocalDateTime.now().getYear());
        }
        if (!FileService.isFile(polishProp.getProperty("lotteryNumbers"))) {
            new DownloadPolish(polishProp);
        }
        if (!FileService.isFile(irishProp.getProperty("afterMulti"))) {
            new AfterMultiCreator().run(irishProp);
        }
        if (!FileService.isFile(euroProp.getProperty("afterMulti"))) {
            new AfterMultiCreator().run(euroProp);
        }
        if (!FileService.isFile(polishProp.getProperty("afterMulti"))) {
            new AfterMultiCreator().run(polishProp);
        }
        if (!FileService.isFile(irishProp.getProperty("listOfNumbers"))) {
            try {
                new NumberCreator(irishProp).createNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!FileService.isFile(euroProp.getProperty("listOfNumbers"))) {
            try {
                new NumberCreator(euroProp).createNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!FileService.isFile(polishProp.getProperty("listOfNumbers"))) {
            try {
                new NumberCreator(polishProp).createNumbers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!FileService.isFile(irishProp.getProperty("algorithmFile"))){
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(irishProp.getProperty("lotteryNumbers"));
            new AlgorithmCreator(lotteryNumbers,irishProp).createAlgorithm();
        }
        if (!FileService.isFile(euroProp.getProperty("algorithmFile"))){
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(euroProp.getProperty("lotteryNumbers"));
            new AlgorithmCreator(lotteryNumbers,euroProp).createAlgorithm();
        }
        if (!FileService.isFile(polishProp.getProperty("algorithmFile"))){
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(polishProp.getProperty("lotteryNumbers"));
            new AlgorithmCreator(lotteryNumbers,polishProp).createAlgorithm();
        }
    }
}
