package downloader;

import dataSupport.FileService;
import entity.OneDraw;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class LotteryDrawsXMLDownloader {
    public LotteryDrawsXMLDownloader(Properties properties) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        ArrayList<OneDraw> lotteryNumbers = FileService.loadObject(properties.getProperty("lotteryNumbers"));
        URL obj = new URL(properties.getProperty("url"));
        URLConnection urlConnection = obj.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(urlConnection.getInputStream());
        document.getDocumentElement().normalize();

        NodeList drawResult = document.getElementsByTagName("DrawResult");
        Element el = (Element) drawResult.item(0);
        int drawNumber = Integer.parseInt(el.getElementsByTagName("DrawNumber").item(0).getTextContent());
        int lastDraw = lotteryNumbers.get(lotteryNumbers.size() - 1).getDrawNumber();
        int missedDraws = drawNumber - lastDraw;
        if (missedDraws > 0) {
            for (int i = -1 + missedDraws; i >= 0; i--) {
                Element element = (Element) drawResult.item(i);
                OneDraw oneDraw = new OneDraw();
                oneDraw.setDrawNumber(Integer.parseInt(element.getElementsByTagName("DrawNumber").item(0).getTextContent()));
                oneDraw.setDrawDate(LocalDateTime.parse(element.getElementsByTagName("DrawDate").item(0).getTextContent().substring(0, 19)));
                NodeList number = element.getElementsByTagName("Number");
                ArrayList<Integer> drawnNumbers = new ArrayList<>();
                ArrayList<Integer> bonusBall = new ArrayList<>();

                for (int j = 0; j < number.getLength(); j++) {
                    if (j < Integer.parseInt(properties.getProperty("drawNumbers"))) {
                        drawnNumbers.add(Integer.parseInt(number.item(j).getTextContent()));
                    } else if (j == Integer.parseInt(properties.getProperty("drawNumbers"))
                            || j == Integer.parseInt(properties.getProperty("drawNumbers")) + 1) {
                        bonusBall.add(Integer.parseInt(number.item(j).getTextContent()));
                    }
                }
                oneDraw.setDrawNumbers(drawnNumbers);
                oneDraw.setBonusBalls(bonusBall);
                lotteryNumbers.add(oneDraw);
            }
            FileService.saveObject(lotteryNumbers, properties.getProperty("lotteryNumbers"));
        }
    }
}
