package downloader;

import dataSupport.FileService;
import entity.OneDraw;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import support.Auxiliary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class DownEuro {
    private final ArrayList<OneDraw> listOfNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Integer> bonus = new ArrayList<>();
    private OneDraw oneDraw = new OneDraw();

    public void getNumbers(int from , int to) {
        for (int i = to; i >= from; i--) {

            Connection connection = Jsoup.connect("https://irish.national-lottery.com/euromillions/results-archive-" + i);
            Document document = null;
            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = null;
            Elements elements1 = null;
            Elements elements2 = null;
            if (document != null) {
                elements = document.getElementsByClass("medium ball euromillions-ball");
                elements1 = document.getElementsByClass("medium ball euromillions-lucky-star");
                elements2 = document.getElementsByClass("noBefore colour");
            }

            if (elements != null) {
                int licznik = 0;
                int bonusCount = 0;
                int drawNumber = 1328;
                for (Element span : elements) {
                    numbers.add(Integer.valueOf(span.text()));
                    licznik++;
                    if (licznik == 6) {
                        oneDraw.setDrawNumbers(numbers);
                        bonus.add(Integer.parseInt(elements1.get(bonusCount).text()));
                        oneDraw.setBonusBalls(bonus);
                        String[] s = elements2.get(bonusCount).text().toUpperCase().split(" ");
                        oneDraw.setDrawDate(LocalDateTime.of(Integer.parseInt(s[3]), Month.valueOf(s[2]).getValue(), Integer.parseInt(s[1].replaceAll("RD","").replaceAll("TH","").replaceAll("ND","").replaceAll("ST","")),19,30));
                        oneDraw.setDrawNumber(drawNumber);
                        listOfNumbers.add(oneDraw);
                        numbers = new ArrayList<>();
                        bonus = new ArrayList<>();
                        oneDraw = new OneDraw();
                        licznik = 0;
                        bonusCount++;
                        drawNumber--;
                    }
                }
            }
        }
        try {
            FileService.saveObject(Auxiliary.returnReversedOneDraws(listOfNumbers),"EuroLottery/FullEuroDraws");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
