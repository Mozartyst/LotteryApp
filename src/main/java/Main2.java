import dataSupport.FileService;
import entity.OneDraw;
import threeHunter.AllThreesCreatorInRange;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        new AllThreesCreatorInRange().get(lotteryNumbers,properties);
//        new ThreesGraphAnglePrinter().get(lotteryNumbers);
    }
}
