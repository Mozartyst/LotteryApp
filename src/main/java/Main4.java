import creators.AlgorithmCreator;
import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        TreeMap<Integer, Number> listOfNumbers = FileService.loadObject(properties.getProperty("listOfNumbers"));
        new AlgorithmCreator(lotteryNumbers,listOfNumbers,properties).createAlgorithm();
    }
}
