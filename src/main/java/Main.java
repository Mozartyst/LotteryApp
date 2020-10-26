import dataSupport.FileService;
import mainInterface.FirstTime;
import org.xml.sax.SAXException;
import update.LotteryUpdate;
import mainInterface.LotteryChoice;
import mainInterface.MainMenu;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        Scanner scanner = new Scanner(System.in);
        if (FileService.isFile("IrishLottery/FullIrishDraws")) {
            new LotteryUpdate().run();

            Properties properties = new LotteryChoice().run(scanner);
            new MainMenu().showMenu(scanner, properties);
        } else {
            System.out.println("The installation is in progress");
            new FirstTime().run();
            System.out.println("Installation Finished. Run program again");
        }
    }
}

