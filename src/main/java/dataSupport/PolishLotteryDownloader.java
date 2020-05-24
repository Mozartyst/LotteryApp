package dataSupport;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PolishLotteryDownloader {
    private ArrayList<ArrayList<Integer>> listOfNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> getNumbers(int from , int to) {
        for (int i = to; i >= from; i--) {

            Connection connection = Jsoup.connect("http://megalotto.pl/wyniki/lotto/losowania-z-roku-" + i);
            Document document = null;
            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements = null;
            if (document != null) {
                elements = document.getElementsByClass("numbers_in_list ");
            }

            if (elements != null) {
                int licznik = 0;
                for (Element span : elements) {
                    String temp = span.text();
                    numbers.add(Integer.valueOf(temp));
                    licznik++;
                    if (licznik == 6) {
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
