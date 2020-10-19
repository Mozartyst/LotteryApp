package mainInterface;

import downloader.DownloadEuro;
import downloader.DownloadIrish;
import downloader.DownloadPolish;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Choice1 {
    public void run() throws IOException {
        Properties irishProp = new Properties();
        irishProp.load(new FileInputStream("src/main/resources/IrishLotto"));
        Properties euroProp = new Properties();
        euroProp.load(new FileInputStream("src/main/resources/EuroLotto"));
        Properties polishProp = new Properties();
        polishProp.load(new FileInputStream("src/main/resources/PolishLotto"));
        new DownloadIrish().getNumbers(2016,2020);
        new DownloadEuro().getNumbers(2015,2020);
        new DownloadPolish(polishProp);
    }
}
