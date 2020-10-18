
import dataSupport.FileService;
import downloader.*;
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
        properties.load(new FileInputStream("src/main/resources/PolishLotto"));
//        new LotteryDrawsXMLDownloader(properties);
//        new DownPolish(properties);
        new LotteryDrawsJSONDownloader(properties);
    }
}
