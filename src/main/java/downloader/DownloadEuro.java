package downloader;

import dataSupport.FileService;
import entity.OneDraw;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import support.Auxiliary;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Properties;

public class DownloadEuro {
    private final ArrayList<OneDraw> listOfNumbers = new ArrayList<>();
    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Integer> bonus = new ArrayList<>();
    private OneDraw oneDraw = new OneDraw();

    public void getNumbers(Properties properties, int from, int to) throws IOException, ParserConfigurationException, SAXException {
        URL obj = new URL(properties.getProperty("url"));
        URLConnection urlConnection = obj.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        org.w3c.dom.Document doc = documentBuilder.parse(urlConnection.getInputStream());
        doc.getDocumentElement().normalize();

        NodeList drawResult = doc.getElementsByTagName("DrawResult");
        org.w3c.dom.Element el = (org.w3c.dom.Element) drawResult.item(0);
        int drawNumber = Integer.parseInt(el.getElementsByTagName("DrawNumber").item(0).getTextContent());

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
                int dateCount = 0;
                for (Element span : elements) {
                    numbers.add(Integer.valueOf(span.text()));
                    licznik++;
                    if (licznik == 5) {
                        oneDraw.setDrawNumbers(numbers);
                        bonus.add(Integer.parseInt(elements1.get(bonusCount).text()));
                        bonusCount++;
                        bonus.add(Integer.parseInt(elements1.get(bonusCount).text()));
                        bonusCount++;
                        oneDraw.setBonusBalls(bonus);
                        String[] s = elements2.get(dateCount).text().toUpperCase().split(" ");
                        oneDraw.setDrawDate(LocalDateTime.of(Integer.parseInt(s[3]), Month.valueOf(s[2]).getValue(), Integer.parseInt(s[1].replaceAll("RD", "").replaceAll("TH", "").replaceAll("ND", "").replaceAll("ST", "")), 19, 30));
                        oneDraw.setDrawNumber(drawNumber);
                        listOfNumbers.add(oneDraw);
                        numbers = new ArrayList<>();
                        bonus = new ArrayList<>();
                        oneDraw = new OneDraw();
                        licznik = 0;
                        dateCount++;
                        drawNumber--;
                    }
                }
            }
        }
        try {
            FileService.saveObject(Auxiliary.returnReversedOneDraws(listOfNumbers), "EuroLottery/FullEuroDraws");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
