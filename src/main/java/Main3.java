import dataSupport.FileService;
import entity.MultiCombinationNumber;
import entity.OneDraw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
//        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        Set<MultiCombinationNumber> multi = FileService.loadObject(properties.getProperty("afterMulti"));
        Set<MultiCombinationNumber> collect = multi.stream().filter((x) -> x.getComplexNumber().length == 2).filter((y) -> y.getIndexesWhereAppeared().size()>4).collect(Collectors.toSet());
        System.out.println(collect);
    }
}
