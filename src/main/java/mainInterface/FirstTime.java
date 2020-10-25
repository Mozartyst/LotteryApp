package mainInterface;

import downloader.DownloadEuro;
import downloader.DownloadIrish;
import downloader.DownloadPolish;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FirstTime {
    public void run() throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        Properties irishProp = new Properties();
        irishProp.load(new FileInputStream("src/main/resources/IrishLotto"));
        Properties euroProp = new Properties();
        euroProp.load(new FileInputStream("src/main/resources/EuroLotto"));
        Properties polishProp = new Properties();
        polishProp.load(new FileInputStream("src/main/resources/PolishLotto"));
        new DownloadIrish().getNumbers(irishProp, 2016, 2020);
        new DownloadEuro().getNumbers(euroProp, 2015, 2020);
        new DownloadPolish(polishProp);
        new Creator().run(irishProp);
        new Creator().run(euroProp);
        new Creator().run(polishProp);
    }
}
