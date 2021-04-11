
import dataSupport.FileService;
import entity.Number;
import entity.OneDraw;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/IrishLotto"));
        List<OneDraw> fullLotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        Map<Integer, Number> listOfNumbers = new TreeMap<>();
        List<OneDraw> lotteryNumbers = new ArrayList<>();
        for (int i = 1; i < Integer.parseInt(properties.getProperty("range")); i++) {
            Number number = new Number(1);
            listOfNumbers.put(i, number);
        }
        for (int i = 0; i < fullLotteryNumbers.size(); i++) {
            lotteryNumbers.add(fullLotteryNumbers.get(i));
            for (OneDraw weekDraw : lotteryNumbers) {
                for (Integer number : weekDraw.getDrawNumbers()) {
                    Number number1 = listOfNumbers.get(number);
                    number1.addIndex(fullLotteryNumbers.indexOf(weekDraw));
                }
            }
        }
    }
}
//wypada liczba znajdź dlaczego. Co się wydarzyło? Jakie były wskaźniki?
// zapisuj: occurred, lastOccurred do distance, ossurred do innych, po jakich after, czy tworzył nowe trójki czy dokładał do starych,