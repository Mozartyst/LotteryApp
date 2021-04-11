
import creators.NumbersForOne;
import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        Set<MultiCombinationNumber> mapMulti = FileService.loadObject(properties.getProperty("threes"));
        new NumbersForOne().getNumbersForOne(42,lotteryNumbers);
    }
}
//1. Zrób 100 indeksów dla wzorca
//2. potem zrób predykcję i sprawdź wynik. Zobacz co w wyniku miało wzięcie
//3. Zapisuj co ma trafność.