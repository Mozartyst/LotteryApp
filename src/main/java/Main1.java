
import mainInterface.Creator;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main1 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException, ParseException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        new Creator().run(properties);



//        new LotteryDrawsXMLDownloader(properties);
//        new LotteryDrawsJSONDownloader(properties);
//        new DownEuro().getNumbers(2015,2020);
//        new DownPolish(properties);
//        new Down().getNumbers(2016,2020);

    }
}
