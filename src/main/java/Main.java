import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;
import update.FirstTime;
import org.xml.sax.SAXException;
import update.LotteryUpdate;
import mainInterface.LotteryChoice;
import mainInterface.MainMenu;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException, InterruptedException {
        Boolean isRun = true;
        Scanner scanner = new Scanner(System.in);
        if (FileService.isFile("IrishLottery/FullDrawsList")
                && FileService.isFile("IrishLottery/NumbersAfterMulti")
                && FileService.isFile("IrishLottery/ListOfNumbers")
                && FileService.isFile("EuroLottery/FullDrawsList")
                && FileService.isFile("EuroLottery/NumbersAfterMulti")
                && FileService.isFile("EuroLottery/ListOfNumbers")
                && FileService.isFile("PolishLottery/NumbersAfterMulti")
                && FileService.isFile("PolishLottery/ListOfNumbers")
                && FileService.isFile("PolishLottery/FullDrawsList")) {
            new LotteryUpdate().run();
            Properties properties = new LotteryChoice().run(scanner);
            ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
            Set<MultiCombinationNumber> multiCombinationNumbers = FileService.loadObject(properties.getProperty("reducedAfterMulti"));
            while (isRun) {
                new MainMenu().showMenu(lotteryNumbers,scanner, properties);
            }
        } else {
            System.out.println("The installation is in progress");
            new FirstTime().run();
            System.out.println("Installation Finished. Run program again");
        }
    }
}

