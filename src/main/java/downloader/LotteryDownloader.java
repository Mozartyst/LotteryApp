package downloader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class LotteryDownloader {
    private final ArrayList<ArrayList<Integer>> listOfNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> getNumbers(int from , int to, Properties properties) {
        int drawNumbers = Integer.parseInt(properties.getProperty("drawNumbers"));
        for (int i = to; i >= from; i--) {

            Connection connection = Jsoup.connect(properties.getProperty("url") + i);
            Document document = null;
            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = null;
            if (document != null) {
                elements = document.getElementsByClass(properties.getProperty("className"));
            }

            if (elements != null) {
                int licznik = 0;
                for (Element span : elements) {
                    String temp = span.text();
                    numbers.add(Integer.valueOf(temp));
                    licznik++;
                    if (licznik == drawNumbers) {
                        listOfNumbers.add(numbers);
                        numbers = new ArrayList<>();
                        licznik = 0;
                    }
                }
            }
        }
        return listOfNumbers;
    }

}
