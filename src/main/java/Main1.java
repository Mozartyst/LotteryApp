
import dataSupport.FileService;
import downloader.DownloadEuro;
import downloader.DownloadIrish;
import entity.OneDraw;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/EuroLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        System.out.println(lotteryNumbers);
    }
}
