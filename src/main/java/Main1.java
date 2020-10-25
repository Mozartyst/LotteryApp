
import creators.ComboKeyGenerator;
import dataSupport.FileService;
import downloader.*;
import entity.MultiCombinationKeys;
import entity.OneDraw;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main1 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException, ParseException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/EuroLotto"));

//        new LotteryDrawsXMLDownloader(properties);
//        new LotteryDrawsJSONDownloader(properties);
//        new DownEuro().getNumbers(2015,2020);
//        new DownPolish(properties);
//        new Down().getNumbers(2016,2020);

//        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
//        ArrayList<OneDraw> lotteryNumbersForAlgorithm = new ArrayList<>();
//        for (int i = 0; i < lotteryNumbers.size() - 50; i++) {
//            lotteryNumbersForAlgorithm.add(lotteryNumbers.get(i));
//        }
//        ArrayList<MultiCombinationKeys> afterMultiCombinationKey = new ArrayList<>();
//        Thread thread1 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 1, 120, properties));
//        Thread thread2 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 121, 240, properties));
//        Thread thread3 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 241, 360, properties));
//        Thread thread4 = new Thread(new ComboKeyGenerator(lotteryNumbersForAlgorithm, afterMultiCombinationKey, 361, lotteryNumbersForAlgorithm.size() - 4, properties));
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
    }
}
