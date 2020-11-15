
import dataSupport.FileService;
import entity.CombinationNumbers;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import threeHunter.ThreesCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeMap;

public class Main1 {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException, ParseException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        TreeMap<CombinationNumbers, Integer> integerArrayListTreeMap = new ThreesCreator(FileService.loadObject("IrishLottery/FullIrishDraws")).get();
        CombinationNumbers combinationNumbers = new CombinationNumbers(1,15,42);
        if (integerArrayListTreeMap.containsKey(combinationNumbers)) {
            System.out.println(combinationNumbers + " " + integerArrayListTreeMap.get(combinationNumbers));
        }
    }
}
