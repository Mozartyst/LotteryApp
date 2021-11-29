import checker.FilesChecker;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import update.FirstTime;
import org.xml.sax.SAXException;
import update.LotteryUpdate;
import mainInterface.LotteryChoice;
import mainInterface.MainMenu;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException, InterruptedException {
        Boolean isRun = true;
        Scanner scanner = new Scanner(System.in);
        Properties lotteriesProp = new Properties();
        lotteriesProp.load(new FileInputStream("src/main/resources/LotteryPropertiesNames.properties"));
        while (isRun) {
            if (new FilesChecker().areFiles(lotteriesProp)) {
                new LotteryUpdate().run(lotteriesProp);
                Properties properties = new LotteryChoice().run(scanner,lotteriesProp);
                ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
                Set<MultiCombinationNumber> multiCombinationNumbers = FileService.loadObject(properties.getProperty("reducedAfterMulti"));

                new MainMenu().showMenu(lotteryNumbers, scanner, properties);

            } else {
                System.out.println("The installation is in progress");
                new FirstTime().run(lotteriesProp);
                System.out.println("Installation Finished.");
            }
        }
    }
}

